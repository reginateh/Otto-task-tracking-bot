import java.util.Scanner;

public class Otto {
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

    public static void main(String[] args) {
        Otto instance = new Otto();
        instance.intro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("bye")) {
                break;
            }
            instance.displayMsg(userInput);
        }
        instance.exit();
    }
}
