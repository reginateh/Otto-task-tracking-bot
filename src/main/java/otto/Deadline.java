package otto;

import java.time.LocalDate;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private String by;
    /** Date of the deadline. Only exists when the deadline contains a valid date format. */
    private LocalDate date;

    public Deadline(String description, String by, String tags) {
        this(description, by, false, Parser.parseTags(tags));
    }

    public Deadline(String description, String by, boolean isComplete) {
        this(description, by, isComplete, new TagList());
    }

    /**
     * Creates a deadline task.
     * @param description Description of the deadline.
     * @param by Deadline of the task.
     * @param isComplete True if the task is complete, false otherwise.
     * @param tags Tags of the task.
     */
    public Deadline(String description, String by, boolean isComplete, TagList tags) {
        super(description, isComplete, tags);
        this.by = by;
        this.date = DateUtils.extractDate(by);
    }

    public String getDeadline() {
        return by;
    }

    /**
     * Returns the deadline string with data replaced by given pattern.
     */
    private String getFormattedDeadline() {
        if (this.date != null) {
            return by.replaceFirst(date.toString(), DateUtils.formatDate(date));
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
        return "[D]" + super.toString() + "(by: " + getFormattedDeadline() + ")";
    }
}
