package otto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
    private static final String DATE_PATTERN = "(\\d{4}-\\d{2}-\\d{2})";
    private static final String DATE_FORMAT = "MMM d yyyy";
    /**
     * Extracts the date from the deadline string.
     *
     * @param input Deadline string.
     * @return LocalDate object if the deadline contains a valid date, null otherwise.
     */
    public static LocalDate extractDate(String input) {
        // Regex pattern for yyyy-mm-dd
        Pattern datePattern = Pattern.compile(DATE_PATTERN);

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
     * Formats the date to a certain format.
     *
     * @param date Date to be formatted.
     * @return Formatted date string.
     */
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
