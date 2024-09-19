package otto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseTodoTask_Valid() throws OttoException {
        // Arrange
        String task = "todo buy milk";

        // Act
        String[] result = Parser.parseTask("todo", task);

        // Assert
        assertArrayEquals(new String[]{"todo", "buy milk", ""}, result);
    }

    @Test
    public void testParseTodoTask_Invalid() {
        // Arrange
        String task = "todo ";

        // Act & Assert
        OttoException exception = assertThrows(OttoException.class, () -> Parser.parseTask("todo", task));
        assertEquals(OttoResponses.TODO_ERROR, exception.getMessage());
    }

    @Test
    public void testParseTodoTask_WithTags() throws OttoException {
        // Arrange
        String task = "todo buy milk #groceries #urgent";

        // Act
        String[] result = Parser.parseTask("todo", task);

        // Assert
        assertArrayEquals(new String[]{"todo", "buy milk", "#groceries #urgent"}, result);
    }

    @Test
    public void testParseDeadlineTask_Valid() throws OttoException {
        // Arrange
        String task = "deadline submit assignment /by tomorrow";

        // Act
        String[] result = Parser.parseTask("deadline", task);

        // Assert
        assertArrayEquals(new String[]{"deadline", "submit assignment", "tomorrow", ""}, result);
    }

    @Test
    public void testParseDeadlineTask_WithTags() throws OttoException {
        // Arrange
        String task = "deadline submit assignment /by tomorrow #school #urgent";

        // Act
        String[] result = Parser.parseTask("deadline", task);

        // Assert
        assertArrayEquals(new String[]{"deadline", "submit assignment", "tomorrow", "#school #urgent"}, result);
    }

    @Test
    public void testParseDeadlineTask_Invalid() {
        // Arrange
        String task = "deadline submit assignment";

        // Act & Assert
        OttoException exception = assertThrows(OttoException.class, () -> Parser.parseTask("deadline", task));
        assertEquals(OttoResponses.DEADLINE_ERROR, exception.getMessage());
    }

    @Test
    public void testParseEventTask_Valid() throws OttoException {
        // Arrange
        String task = "event team meeting /from Monday /to Tuesday";

        // Act
        String[] result = Parser.parseTask("event", task);

        // Assert
        assertArrayEquals(new String[]{"event", "team meeting", "Monday", "Tuesday", ""}, result);
    }

    @Test
    public void testParseEventTask_WithTags() throws OttoException {
        // Arrange
        String task = "event team meeting /from Monday /to Tuesday #work #meeting";

        // Act
        String[] result = Parser.parseTask("event", task);

        // Assert
        assertArrayEquals(new String[]{"event", "team meeting", "Monday", "Tuesday", "#work #meeting"}, result);
    }

    @Test
    public void testParseEventTask_Invalid() {
        // Arrange
        String task = "event team meeting /from Monday";

        // Act & Assert
        OttoException exception = assertThrows(OttoException.class, () -> Parser.parseTask("event", task));
        assertEquals(OttoResponses.EVENT_ERROR, exception.getMessage());
    }

    @Test
    public void testParseMarkComplete_Valid() throws OttoException {
        // Arrange
        String[] command = {"mark", "1"};

        // Act
        int result = Parser.parseMarkComplete(command);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void testParseMarkComplete_Invalid() {
        // Arrange
        String[] command = {"mark"};

        // Act & Assert
        OttoException exception = assertThrows(OttoException.class, () -> Parser.parseMarkComplete(command));
        assertEquals(OttoResponses.MARK_ERROR, exception.getMessage());
    }

    @Test
    public void testParseMarkComplete_InvalidNumber() {
        // Arrange
        String[] command = {"mark", "abc"};

        // Act & Assert
        OttoException exception = assertThrows(OttoException.class, () -> Parser.parseMarkComplete(command));
        assertEquals(OttoResponses.INDEX_ERROR, exception.getMessage());
    }

    @Test
    public void testParseDeleteTask_Valid() throws OttoException {
        // Arrange
        String[] command = {"delete", "2"};

        // Act
        int result = Parser.parseDeleteTask(command);

        // Assert
        assertEquals(2, result);
    }

    @Test
    public void testParseDeleteTask_Invalid() {
        // Arrange
        String[] command = {"delete"};

        // Act & Assert
        OttoException exception = assertThrows(OttoException.class, () -> Parser.parseDeleteTask(command));
        assertEquals(OttoResponses.DELETE_ERROR, exception.getMessage());
    }

    @Test
    public void testParseDeleteTask_InvalidNumber() {
        // Arrange
        String[] command = {"delete", "abc"};

        // Act & Assert
        OttoException exception = assertThrows(OttoException.class, () -> Parser.parseDeleteTask(command));
        assertEquals(OttoResponses.INDEX_ERROR, exception.getMessage());
    }

    @Test
    public void testParseFindTask_ValidInput() throws OttoException {
        String input = "find important meeting";
        String expected = "important meeting";
        String result = Parser.parseFindTask(input);
        assertEquals(expected, result, "The parsed keyword should match the expected value.");
    }

    @Test
    public void testParseFindTask_EmptyKeyword() {
        String input = "find";
        OttoException thrown = assertThrows(OttoException.class, () -> {
            Parser.parseFindTask(input);
        }, "Expected parseFindTask() to throw an exception, but it didn't.");
        assertEquals(OttoResponses.FIND_ERROR, thrown.getMessage());
    }

    @Test
    public void testParseFindTag_ValidInput() throws OttoException {
        String input = "tag urgent";
        String expected = "urgent";
        String result = Parser.parseFindTag(input);
        assertEquals(expected, result, "The parsed tag should match the expected value.");
    }

    @Test
    public void testParseFindTag_EmptyTag() {
        String input = "tag";
        OttoException thrown = assertThrows(OttoException.class, () -> {
            Parser.parseFindTag(input);
        }, "Expected parseFindTag() to throw an exception, but it didn't.");
        assertEquals(OttoResponses.FIND_ERROR, thrown.getMessage());
    }

    @Test
    public void testParseTasksFromStorage_Todo() {
        // Arrange
        String taskStr = "[T][X] buy milk";

        // Act
        Task result = Parser.parseTasksFromStorage(taskStr);

        // Assert
        assertNotNull(result);
        assertInstanceOf(Todo.class, result);
        assertEquals("buy milk", result.getDescription());
        assertTrue(result.isComplete());
    }

    @Test
    public void testParseTasksFromStorage_TodoWithTags() {
        // Arrange
        String taskStr = "[T][X] buy milk #errands #groceries";

        // Act
        Task result = Parser.parseTasksFromStorage(taskStr);

        // Assert
        assertNotNull(result);
        assertInstanceOf(Todo.class, result);
        assertEquals("buy milk", result.getDescription());
        assertTrue(result.isComplete());
        assertEquals("#errands #groceries ", result.getTags());
    }

    @Test
    public void testParseTasksFromStorage_Deadline() {
        // Arrange
        String taskStr = "[D][ ] submit assignment (by: tomorrow)";

        // Act
        Task result = Parser.parseTasksFromStorage(taskStr);

        // Assert
        assertNotNull(result);
        assertInstanceOf(Deadline.class, result);
        assertEquals("submit assignment", result.getDescription());
        assertEquals("tomorrow", ((Deadline) result).getDeadline());
        assertFalse(result.isComplete());
    }

    @Test
    public void testParseTasksFromStorage_DeadlineWithDate() {
        // Arrange
        String taskStr = "[D][ ] submit assignment (by: 2024-11-11)";

        // Act
        Task result = Parser.parseTasksFromStorage(taskStr);

        // Assert
        assertNotNull(result);
        assertInstanceOf(Deadline.class, result);
        assertEquals("submit assignment", result.getDescription());
        assertEquals("2024-11-11", ((Deadline) result).getDeadline());
        assertFalse(result.isComplete());
    }

    @Test
    public void testParseTasksFromStorage_DeadlineWithTags() {
        // Arrange
        String taskStr = "[D][ ] submit assignment #school #cs (by: tomorrow)";

        // Act
        Task result = Parser.parseTasksFromStorage(taskStr);

        // Assert
        assertNotNull(result);
        assertInstanceOf(Deadline.class, result);
        assertEquals("submit assignment", result.getDescription());
        assertEquals("tomorrow", ((Deadline) result).getDeadline());
        assertFalse(result.isComplete());
        assertEquals("#school #cs ", result.getTags());
    }

    @Test
    public void testParseTasksFromStorage_Event() {
        // Arrange
        String taskStr = "[E][ ] team meeting (from: Monday, to: Tuesday)";

        // Act
        Task result = Parser.parseTasksFromStorage(taskStr);

        // Assert
        assertNotNull(result);
        assertInstanceOf(Event.class, result);
        assertEquals("team meeting", result.getDescription());
        assertEquals("Monday", ((Event) result).getStartTime());
        assertEquals("Tuesday", ((Event) result).getEndTime());
        assertFalse(result.isComplete());
    }

    @Test
    public void testParseTasksFromStorage_EventWithTags() {
        // Arrange
        String taskStr = "[E][ ] team meeting #meeting #team (from: Monday, to: Tuesday)";

        // Act
        Task result = Parser.parseTasksFromStorage(taskStr);

        // Assert
        assertNotNull(result);
        assertInstanceOf(Event.class, result);
        assertEquals("team meeting", result.getDescription());
        assertEquals("Monday", ((Event) result).getStartTime());
        assertEquals("Tuesday", ((Event) result).getEndTime());
        assertFalse(result.isComplete());
        assertEquals("#meeting #team ", result.getTags());
    }

    @Test
    public void testParseTasksFromStorage_Invalid() {
        // Arrange: Invalid task strings that do not match the expected pattern
        String invalidTask1 = "[A][ ] Buy groceries"; // Invalid type "A"
        String invalidTask2 = "[T][ ] "; // Missing task description
        String invalidTask3 = "[D][X] Buy milk (by: )"; // Empty deadline
        String invalidTask4 = "[E][ ] Concert (from: , to: )"; // Empty event time

        // Act & Assert: Each invalid task should return null
        assertNull(Parser.parseTasksFromStorage(invalidTask1));
        assertNull(Parser.parseTasksFromStorage(invalidTask2));
        assertNull(Parser.parseTasksFromStorage(invalidTask3));
        assertNull(Parser.parseTasksFromStorage(invalidTask4));
    }

    @Test
    public void testParseTasksFromStorage_InvalidWithTags() {
        // Arrange: Invalid task strings that do not match the expected pattern
        String invalidTask1 = "[A][ ] Buy groceries #tag"; // Invalid type "A"
        String invalidTask2 = "[D][X] Buy milk #tag (by: )"; // Empty deadline
        String invalidTask3 = "[E][ ] Concert #tag (from: , to: )"; // Empty event time

        // Act & Assert: Each invalid task should return null
        assertNull(Parser.parseTasksFromStorage(invalidTask1));
        assertNull(Parser.parseTasksFromStorage(invalidTask2));
        assertNull(Parser.parseTasksFromStorage(invalidTask3));
    }
}
