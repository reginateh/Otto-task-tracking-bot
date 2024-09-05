package otto;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates an event task.
     * Default is incomplete.
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

    /**
     * Creates an event task.
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     * @param isComplete True if the event is complete, false otherwise.
     */
    public Event(String description, String from, String to, boolean isComplete) {
        super(description, isComplete);
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
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}
