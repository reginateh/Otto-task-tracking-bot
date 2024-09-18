package otto;

import java.time.LocalDate;

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
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
