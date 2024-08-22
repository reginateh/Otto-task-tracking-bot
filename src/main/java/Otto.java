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
        printLine();
        System.out.println("\tOtto is signing off now. Don’t wake him up again.");
        printLine();
    }

    private void displayMsg(String msg) {
        printLine();
        System.out.println(msg.indent(4));
        printLine();
    }

    private void displayTaskList() {
        displayMsg(taskList.toString());
    }

    private void addTask(String description) {
        this.taskList.addTask(description);
        displayMsg("Hmph. More work? Otto will note it down, but he’d much rather be sleeping.\nAdded: "
                + description);
    }

    private void markComplete(String rawIndex, boolean status) {
        try {
            int index = Integer.parseInt(rawIndex);
            Task task = this.taskList.markComplete(index - 1, status);
            this.displayMsg((status
                    ? "Well, finally. You finished something.\n"
                    : "So you didn’t finish that task. Try to get it done so Otto can rest easy.\n")
                    + task.toString());
        } catch (NumberFormatException e) {
            this.displayMsg("Error: expect an integer but get: " + rawIndex);
        } catch (IndexOutOfBoundsException e) {
            this.displayMsg("Error: task index out of range: " + rawIndex);
        }
    }

    private void handleInput(String userInput) {
        if (Objects.equals(userInput, "")) {
            return;
        }
        String[] command = userInput.split("\\s+", 2);
        switch (command[0].toLowerCase()) {
            case "list":
                this.displayTaskList();
                break;
            case "mark":
                if (command.length > 1) {
                    this.markComplete(command[1], true);
                }
                break;
            case "unmark":
                if (command.length > 1) {
                    this.markComplete(command[1], false);
                }
                break;
            default:
                this.addTask(userInput);
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
