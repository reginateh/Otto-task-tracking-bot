import java.util.Scanner;

public class Otto {
    private static void intro() {
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

    private static void printLine() {
        System.out.println("\t____________________________________________________________\n");
    }

    private static void exit() {
        printLine();
        System.out.println("\tOtto is signing off now. Don’t wake him up again.");
        printLine();
    }

    private static void displayMsg(String msg) {
        printLine();
        System.out.println("\t" + msg);
        printLine();
    }

    public static void main(String[] args) {
        intro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("bye")) {
                break;
            }
            displayMsg(userInput);
        }
        exit();
    }
}
