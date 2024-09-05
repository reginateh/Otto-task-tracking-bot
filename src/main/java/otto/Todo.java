package otto;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
