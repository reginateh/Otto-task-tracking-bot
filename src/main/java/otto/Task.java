package otto;

/**
 * Represents a task.
 */
abstract public class Task {
    private String description;
    private boolean isComplete;
    private TagList tags;

    /**
     * Creates a new task.
     * Default is incomplete.
     * @param description Description of the task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Creates a new task.
     * @param description Description of the task.
     * @param isComplete True if task is complete, false if incomplete.
     */
    public Task(String description, boolean isComplete) {
        this(description, isComplete, new TagList());
    }

    /**
     * Creates a new task.
     * @param description Description of the task.
     * @param isComplete True if task is complete, false if incomplete.
     * @param tags Tags associated with the task.
     */
    public Task(String description, boolean isComplete, TagList tags) {
        this.description = description;
        this.isComplete = isComplete;
        this.tags = tags;
    }

    /**
     * Returns true if the task has the specified tag.
     *
     * @param tagName Name of the tag. Doesn't contain prefix #.
     */
    public boolean hasTag(String tagName) {
        return this.tags.findTag(tagName);
    }

    public Task setComplete(boolean status) {
        this.isComplete = status;
        return this;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTags() {
        return this.tags.toString();
    }

    public String getTaskStringForStorage() {
        return this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s", this.isComplete ? "X" : " ", this.description, this.tags);
    }
}
