import java.util.Objects;
import java.util.Scanner;

public class Otto {
    private final TaskList taskList;

    public Otto() {
        this.taskList = new TaskList();
    }

    private void intro() {
        String owl = "\t            z\n"
                + "\t          z\n"
                + "\t   ^_^  z\n"
                + "\t  (-,-)  \n"
                + "\t  { \" }  \n"
                + "\t---\"-\"---\n";
        String intro = "\n\tOtto would rather be napping, \n\tbut he suppose he can help you with your tasks.\n";
        System.out.println(owl + intro);
        printLine();
    }

    private void printLine() {
        System.out.println("\t____________________________________________________________\n");
    }

    private void exit() {
        this.displayMsg("Otto is signing off now. Don't wake him up again.");
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
        displayMsg("More work? Otto has noted it down, but he'd much rather be sleeping.\n"
                + newTask.toString()
                + "\nNow you have " + this.taskList.getNumOfTasks() + " task(s) in the list.");
    }

    private void deleteTask(int index) throws OttoException {
        try {
            Task deletedTask = this.taskList.deleteTask(index - 1);
            this.displayMsg("Finally, something Otto can get behind—deleting a task. It's gone now, just like Otto wishes he could be... back to his nap.\n"
                    + deletedTask.toString()
                    + "\nNow you have " + this.taskList.getNumOfTasks() + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new OttoException("You need to give Otto an index that is in the list. Can't you even count?");
        }
    }

    private void markComplete(int index, boolean status) throws OttoException {
        try {
            Task task = this.taskList.markComplete(index - 1, status);
            this.displayMsg((status
                    ? "Well, finally. You finished something.\n"
                    : "So you didn't finish that task. Try to get it done so Otto can rest easy.\n")
                    + task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new OttoException("You need to give Otto an index that is in the list. Can't you even count?");
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
                    throw new OttoException("Otto doesn't recognize this command. Speak English.");
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
