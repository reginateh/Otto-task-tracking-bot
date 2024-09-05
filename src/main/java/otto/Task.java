package otto;

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

    public String getDescription() {
        return this.description;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public String getTaskStringForStorage() {
        return this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isComplete ? "X" : " ", this.description);
    }
}
