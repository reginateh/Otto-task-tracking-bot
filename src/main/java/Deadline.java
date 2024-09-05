import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String by;
    private LocalDate date;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isComplete) {
        super(description, isComplete);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.date = null;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (date != null ? date : by) + ")";
    }
}
