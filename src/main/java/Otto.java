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
        System.out.println("\t" + msg);
        printLine();
    }

    private void displayTaskList() {
        displayMsg(taskList.toString());
    }

    private void addTask(String description) {
        this.taskList.addTask(description);
        displayMsg("Hmph. More work? Otto will note it down, but he’d much rather be sleeping.\n\tAdded: " + description);
    }

    public static void main(String[] args) {
        Otto instance = new Otto();
        instance.intro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                instance.displayTaskList();
            } else {
                instance.addTask(userInput);
            }
        }
        instance.exit();
    }
}
