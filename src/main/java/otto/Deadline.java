package otto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private String by;
    private LocalDate date;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isComplete) {
        super(description, isComplete);
        this.by = by;
        this.date = extractDate(by);
    }

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

    private String getFormattedDate() {
        if (this.date != null) {
            return by.replaceFirst(date.toString(), date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
        return by;
    }

    @Override
    public String getTaskStringForStorage() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDate() + ")";
    }
}
