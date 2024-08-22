import java.util.Objects;
import java.util.Scanner;

public class Otto {
    private final TaskList taskList;

    public Otto() {
        this.taskList = new TaskList();
    }

    private void intro() {
        System.out.println((OttoResponses.owl + OttoResponses.intro).indent(4));
        printLine();
    }

    private void printLine() {
        System.out.println(OttoResponses.line.indent(4));
    }

    private void exit() {
        this.displayMsg(OttoResponses.bye);
    }

    private void displayMsg(String msg) {
        printLine();
        System.out.println(msg.indent(4));
        printLine();
    }

    private void displayTaskList() {
        displayMsg(taskList.toString());
    }

    private void addTask(String[] info) {
        Task newTask = this.taskList.addTask(info);
        displayMsg(OttoResponses.addTask + newTask.toString()
                + String.format(OttoResponses.numOfTasks, this.taskList.getNumOfTasks()));
    }

    private void deleteTask(int index) throws OttoException {
        try {
            Task deletedTask = this.taskList.deleteTask(index - 1);
            this.displayMsg(OttoResponses.deleteTask
                    + deletedTask.toString()
                    + String.format(OttoResponses.numOfTasks, this.taskList.getNumOfTasks()));
        } catch (IndexOutOfBoundsException e) {
            throw new OttoException(OttoResponses.indexError);
        }
    }

    private void markComplete(int index, boolean status) throws OttoException {
        try {
            Task task = this.taskList.markComplete(index - 1, status);
            this.displayMsg((status
                    ? OttoResponses.complete
                    : OttoResponses.incomplete)
                    + task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new OttoException(OttoResponses.indexError);
        }
    }

    private void handleInput(String userInput) {
        if (Objects.equals(userInput, "")) {
            return;
        }
        String[] command = userInput.split("\\s+", 2);
        try {
            switch (command[0].toLowerCase()) {
                case "list":
                    this.displayTaskList();
                    break;
                case "mark":
                    this.markComplete(Parser.parseMarkComplete(command), true);
                    break;
                case "unmark":
                    this.markComplete(Parser.parseMarkComplete(command), false);
                    break;
                case "todo":
                    this.addTask(Parser.parseTask("todo", userInput));
                    break;
                case "deadline":
                    this.addTask(Parser.parseTask("deadline", userInput));
                    break;
                case "event":
                    this.addTask(Parser.parseTask("event", userInput));
                    break;
                case "delete":
                    this.deleteTask(Parser.parseDeleteTask(command));
                    break;
                default:
                    throw new OttoException(OttoResponses.unknown);
            }
        } catch (OttoException e) {
            this.displayMsg(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Otto instance = new Otto();
        instance.intro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else {
                instance.handleInput(userInput);
            }
        }
        instance.exit();
    }
}
