abstract public class Task {
    private String description;
    private boolean isComplete;

    public Task(String description) {
        this(description, false);
    }
    public Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    public Task setComplete(boolean status) {
        this.isComplete = status;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isComplete ? "X" : " ", this.description);
    }
}
