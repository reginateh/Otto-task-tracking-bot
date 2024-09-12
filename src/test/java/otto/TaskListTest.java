package otto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTodoTask() {
        String[] info = {"todo", "Buy milk"};
        Task task = taskList.addTask(info);

        assertNotNull(task, "Task should be added");
        assertInstanceOf(Todo.class, task, "Task should be of type Todo");
        assertEquals("Buy milk", task.getDescription(), "Description should match");
        assertEquals(1, taskList.getNumOfTasks(), "TaskList size should be 1 after adding");
    }

    @Test
    public void testAddDeadlineTask() {
        String[] info = {"deadline", "Submit assignment", "tomorrow"};
        Task task = taskList.addTask(info);

        assertNotNull(task, "Task should be added");
        assertInstanceOf(Deadline.class, task, "Task should be of type Deadline");
        assertEquals("Submit assignment", task.getDescription(), "Description should match");
        assertEquals("tomorrow", ((Deadline) task).getDeadline(), "Deadline should match");
        assertEquals(1, taskList.getNumOfTasks(), "TaskList size should be 1 after adding");
    }

    @Test
    public void testAddEventTask() {
        String[] info = {"event", "Party", "12pm", "2pm"};
        Task task = taskList.addTask(info);

        assertNotNull(task, "Task should be added");
        assertInstanceOf(Event.class, task, "Task should be of type Event");
        assertEquals("Party", task.getDescription(), "Description should match");
        assertEquals("12pm", ((Event) task).getStartTime(), "Event start time should match");
        assertEquals("2pm", ((Event) task).getEndTime(), "Event end time should match");
        assertEquals(1, taskList.getNumOfTasks(), "TaskList size should be 1 after adding");
    }

    @Test
    public void testDeleteTask() {
        String[] info = {"todo", "Buy milk"};
        taskList.addTask(info);

        Task deletedTask = taskList.deleteTask(0);

        assertNotNull(deletedTask, "Task should be deleted");
        assertEquals("Buy milk", deletedTask.getDescription(), "Deleted task description should match");
        assertEquals(0, taskList.getNumOfTasks(), "TaskList size should be 0 after deletion");
    }

    @Test
    public void testMarkTaskComplete() {
        String[] info = {"todo", "Buy milk"};
        Task task = taskList.addTask(info);

        Task markedTask = taskList.markComplete(0, true);

        assertTrue(markedTask.isComplete(), "Task should be marked complete");
    }

    @Test
    public void testMarkTaskIncomplete() {
        String[] info = {"todo", "Buy milk"};
        Task task = taskList.addTask(info);

        taskList.markComplete(0, true);  // First mark complete
        Task markedTask = taskList.markComplete(0, false);  // Then mark incomplete

        assertFalse(markedTask.isComplete(), "Task should be marked incomplete");
    }

    @Test
    public void testDeleteTaskWithInvalidIndex() {
        String[] info = {"todo", "Buy milk"};
        taskList.addTask(info);

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(2), "Should throw IndexOutOfBoundsException when deleting non-existing task");
    }

    @Test
    public void testMarkTaskCompleteWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markComplete(0, true), "Should throw IndexOutOfBoundsException when marking non-existing task");
    }

    @Test
    public void testTaskListToString() {
        String[] info1 = {"todo", "Buy milk"};
        String[] info2 = {"deadline", "Submit assignment", "tomorrow"};
        taskList.addTask(info1);
        taskList.addTask(info2);

        String expected = "\n1. [T][ ] Buy milk" +
                "\n2. [D][ ] Submit assignment (by: tomorrow)";
        assertEquals(expected, taskList.toString(), "TaskList string representation should match");
    }
}

