package otto;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

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
