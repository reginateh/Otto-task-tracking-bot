package otto;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to, String tags) {
        this(description, from, to, false, Parser.parseTags(tags));
    }

    public Event(String description, String from, String to, boolean isComplete) {
        this(description, from, to, isComplete, new TagList());
    }

    /**
     * Creates an event task.
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     * @param isComplete True if the event is complete, false otherwise.
     * @param tags Tags associated with the event.
     */
    public Event(String description, String from, String to, boolean isComplete, TagList tags) {
        super(description, isComplete, tags);
        this.from = from;
        this.to = to;
    }

    public String getStartTime() {
        return this.from;
    }

    public String getEndTime() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + ", to: " + to + ")";
    }
}
