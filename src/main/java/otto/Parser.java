package otto;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input and tasks from storage.
 */
public class Parser {
    public static final String STORAGE_TASK_PATTERN = "^\\[(T|D|E)\\]\\[(X| )\\]\\s*(.+?)\\s*((?:#\\w+\\s*)*)\\s*(?:\\(by: (\\d{4}-\\d{2}-\\d{2}|[\\w\\s]+)\\)|\\(from: ([\\w\\s]+), to: ([\\w\\s]+)\\))?$";

    /**
     * Parses the task input by user and returns the task information.
     *
     * @param type The type of the task. Can be "todo", "deadline" or "event".
     * @param task The task string input by user.
     * @return Array of strings containing the command type and task information.
     * @throws OttoException If the type doesn't match any of the 3 options.
     */
    public static String[] parseTask(String type, String task) throws OttoException {
        return switch (type) {
            case "todo" -> parseTodoTask(task);
            case "deadline" -> parseDeadlineTask(task);
            case "event" -> parseEventTask(task);
            default -> throw new OttoException(OttoResponses.UNKNOWN_COMMAND_ERROR);
        };
    }

    /**
     * Parses Todo task.
     *
     * @param task The task string input by user.
     * @return Array of strings containing the command type and task information.
     * @throws OttoException If the task doesn't match the format of a todo task.
     */
    private static String[] parseTodoTask(String task) throws OttoException {
        String[] extracted = extractTags(task);
        String taskWithoutTags = extracted[0]; // Remove tags from the description
        String tags = extracted[1];
        Pattern pattern = Pattern.compile("todo (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(taskWithoutTags);
        if (matcher.matches()) {
            String description = matcher.group(1);
            return new String[]{"todo", description, tags};
        } else {
            throw new OttoException(OttoResponses.TODO_ERROR);
        }
    }

    /**
     * Parses Deadline task.
     *
     * @param task The task string input by user.
     * @return Array of strings containing the command type and task information.
     * @throws OttoException If the task doesn't match the format of a deadline task.
     */
    private static String[] parseDeadlineTask(String task) throws OttoException {
        String[] extracted = extractTags(task);
        String taskWithoutTags = extracted[0]; // Remove tags from the description
        String tags = extracted[1];
        Pattern pattern = Pattern.compile("deadline (\\S.*) /by (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(taskWithoutTags);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadline = matcher.group(2);
            return new String[]{"deadline", description, deadline, tags};
        } else {
            throw new OttoException(OttoResponses.DEADLINE_ERROR);
        }
    }

    /**
     * Parses Event task.
     *
     * @param task The task string input by user.
     * @return Array of strings containing the command type and task information.
     * @throws OttoException If the task doesn't match the format of an event task.
     */
    private static String[] parseEventTask(String task) throws OttoException {
        String[] extracted = extractTags(task);
        String taskWithoutTags = extracted[0]; // Remove tags from the description
        String tags = extracted[1];
        Pattern pattern = Pattern.compile("event (\\S.*) /from (\\S.*) /to (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(taskWithoutTags);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new String[]{"event", description, from, to, tags};
        } else {
            throw new OttoException(OttoResponses.EVENT_ERROR);
        }
    }

    /**
     * Extracts tags from the input string.
     * Returns an empty string if there is no tag.
     *
     * @param input The task string containing description and tags.
     * @return A String array containing the description (without tags) and tags.
     */
    public static String[] extractTags(String input) {
        // Pattern to separate the description from the tags
        Pattern pattern = Pattern.compile("^(\\w+.+?)\\s+(#.+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);  // Extracts description
            String tags = matcher.group(2);         // Extracts tags (with the # symbols)
            return new String[]{description, tags};
        } else {
            // Handle the case where no tags are present
            return new String[]{input, ""};
        }
    }

    /**
     * Parses tags from the tag list and returns them without the `#`.
     * Returns an empty list if there are no tags.
     *
     * @param tags The string containing tags.
     * @return A list of tags without the `#` prefix.
     */
    public static TagList parseTags(String tags) {
        if (tags.isEmpty()) {
            return new TagList();
        }
        Pattern tagPattern = Pattern.compile("#([\\w-]+)");
        Matcher matcher = tagPattern.matcher(tags);
        ArrayList<Tag> tagList = new ArrayList<>();

        while (matcher.find()) {
            tagList.add(new Tag(matcher.group(1)));  // Add the tag without the #
        }

        return new TagList(tagList);
    }

    /**
     * Parses the index of mark and unmark commands.
     *
     * @param command Array of strings containing the command and index.
     * @return Index of the task to be marked.
     * @throws OttoException If the command contains a non-integer index or doesn't contain an index.
     */
    public static int parseMarkComplete(String[] command) throws OttoException {
        if (command.length <= 1) {
            throw new OttoException(OttoResponses.MARK_ERROR);
        }
        try {
            return Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new OttoException(OttoResponses.INDEX_ERROR);
        }
    }

    /**
     * Parses the index of delete command.
     *
     * @param command Array of strings containing the command and index.
     * @return Index of the task to be deleted.
     * @throws OttoException If the command contains a non-integer index or doesn't contain an index.
     */
    public static int parseDeleteTask(String[] command) throws OttoException {
        if (command.length <= 1) {
            throw new OttoException(OttoResponses.DELETE_ERROR);
        }
        try {
            return Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new OttoException(OttoResponses.INDEX_ERROR);
        }
    }

    /**
     * Parses the keyword of find command.
     *
     * @param task The task string input by user.
     * @return Keyword to be searched.
     * @throws OttoException If the task doesn't match the format of a find task.
     */
    public static String parseFindTask(String task) throws OttoException {
        Pattern pattern = Pattern.compile("find (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new OttoException(OttoResponses.FIND_ERROR);
    }

    /**
     * Parses the command of finding a tag.
     *
     * @param command The string input by user.
     * @return Tag to be searched.
     * @throws OttoException If the tag doesn't match the format of a find tag.
     */
    public static String parseFindTag(String command) throws OttoException {
        Pattern pattern = Pattern.compile("tag (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new OttoException(OttoResponses.FIND_ERROR);
    }

    /**
     * Parses task stored in the storage file.
     * Return null if the task doesn't match the format.
     *
     * @param taskStr Raw string of the task.
     * @return A task.
     */
    public static Task parseTasksFromStorage(String taskStr) {
        Pattern pattern = Pattern.compile(STORAGE_TASK_PATTERN);
        Matcher matcher = pattern.matcher(taskStr);

        if (matcher.matches()) {
            String type = matcher.group(1);
            boolean isComplete = matcher.group(2).equals("X");
            String description = matcher.group(3).trim();
            String tags = matcher.group(4);
            String by = matcher.group(5);
            String from = matcher.group(6);
            String to = matcher.group(7);
            if (description.trim().isEmpty()) {
                return null;
            }
            switch (type) {
            case "T":
                return tags == null
                        ? new Todo(description, isComplete)
                        : new Todo(description, isComplete, parseTags(tags));
            case "D":
                return by == null
                        ? null
                        : tags == null
                        ? new Deadline(description, by, isComplete)
                        : new Deadline(description, by, isComplete, parseTags(tags));
            case "E":
                return (from == null || to == null)
                        ? null
                        : tags == null
                        ? new Event(description, from, to, isComplete)
                        : new Event(description, from, to, isComplete, parseTags(tags));
            }
        }
        return null;
    }
}
