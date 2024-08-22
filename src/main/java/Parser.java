import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String[] parseTask(String task) {
        if (task.startsWith("todo")) {
            return parseTodoTask(task);
        } else if (task.startsWith("deadline")) {
            return parseDeadlineTask(task);
        } else if (task.startsWith("event")) {
            return parseEventTask(task);
        } else {
            System.out.println("Unknown task type: " + task);
        }
        return new String[0];
    }

    private static String[] parseTodoTask(String task) {
        Pattern pattern = Pattern.compile("todo (.+)");
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            return new String[]{"todo", description};
        } else {
            System.out.println("Todo must contain a description: " + task);
        }
        return new String[0];
    }

    private static String[] parseDeadlineTask(String task) {
        Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadline = matcher.group(2);
            return new String[]{"deadline", description, deadline};
        } else {
            System.out.println("Invalid deadline format: " + task);
        }
        return new String[0];
    }

    private static String[] parseEventTask(String task) {
        Pattern pattern = Pattern.compile("event (.+) /from (.+) /to (.+)");
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new String[]{"event", description, from, to};
        } else {
            System.out.println("Invalid event format: " + task);
        }
        return new String[0];
    }
}
