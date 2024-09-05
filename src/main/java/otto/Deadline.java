package otto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private String by;
    /** Date of the deadline. Only exists when the deadline contains a valid date format. */
    private LocalDate date;

    /**
     * Creates a deadline task.
     * Default is incomplete.
     * @param description Description of the deadline.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        this(description, by, false);
    }

    /**
     * Creates a deadline task.
     * @param description Description of the deadline.
     * @param by Deadline of the task.
     * @param isComplete True if the task is complete, false otherwise.
     */
    public Deadline(String description, String by, boolean isComplete) {
        super(description, isComplete);
        this.by = by;
        this.date = extractDate(by);
    }

    public String getDeadline() {
        return by;
    }

    /**
     * Extracts the date from the deadline string.
     * @param input Deadline string.
     * @return LocalDate object if the deadline contains a valid date, null otherwise.
     */
    private static LocalDate extractDate(String input) {
        // Regex pattern for yyyy-mm-dd
        Pattern datePattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");

        // Match the pattern in the input string
        Matcher matcher = datePattern.matcher(input);

        if (matcher.find()) {
            String dateString = matcher.group(1);
            // Convert the string to a LocalDate
            return LocalDate.parse(dateString);
        }

        return null;
    }

    /**
     * Returns the deadline string with data replaced by given pattern.
     */
    private String getFormattedDeadline() {
        if (this.date != null) {
            return by.replaceFirst(date.toString(), date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
        return by;
    }

    /**
     * Returns the string representation of the task for storage.
     */
    @Override
    public String getTaskStringForStorage() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
