import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String[] parseTask(String type, String task) throws OttoException {
        return switch (type) {
            case "todo" -> parseTodoTask(task);
            case "deadline" -> parseDeadlineTask(task);
            case "event" -> parseEventTask(task);
            default -> throw new OttoException(OttoResponses.unknown);
        };
    }

    private static String[] parseTodoTask(String task) throws OttoException {
        Pattern pattern = Pattern.compile("todo (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            return new String[]{"todo", description};
        } else {
            throw new OttoException(OttoResponses.todoError);
        }
    }

    private static String[] parseDeadlineTask(String task) throws OttoException {
        Pattern pattern = Pattern.compile("deadline (\\S.*) /by (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadline = matcher.group(2);
            return new String[]{"deadline", description, deadline};
        } else {
            throw new OttoException(OttoResponses.deadlineError);
        }
    }

    private static String[] parseEventTask(String task) throws OttoException {
        Pattern pattern = Pattern.compile("event (\\S.*) /from (\\S.*) /to (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new String[]{"event", description, from, to};
        } else {
            throw new OttoException(OttoResponses.eventError);
        }
    }

    public static int parseMarkComplete(String[] command) throws OttoException {
        if (command.length <= 1) {
            throw new OttoException(OttoResponses.markError);
        }
        try {
            return Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new OttoException(OttoResponses.indexError);
        }
    }

    public static int parseDeleteTask(String[] command) throws OttoException {
        if (command.length <= 1) {
            throw new OttoException(OttoResponses.deleteError);
        }
        try {
            return Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new OttoException(OttoResponses.indexError);
        }
    }
}
