package otto;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description, String tags) {
        this(description, false, Parser.parseTags(tags));
    }

    public Todo(String description, boolean isComplete) {
        this(description, isComplete, new TagList());
    }

    public Todo(String description, boolean isComplete, TagList tags) {
        super(description, isComplete, tags);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
