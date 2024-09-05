package otto;

import java.util.Objects;
import java.util.Scanner;

/**
 * Main class for the Otto program.
 * Handles user input and executes the corresponding command.
 */
public class Otto {
    private static Otto instance;
    private final TaskList taskList;
    private Ui ui = new Ui();

    /**
     * Constructor for Otto.
     * Reads tasks from storage and loads them into the TaskList.
     */
    private Otto() {
        this.taskList = new TaskList(Storage.loadTasks());
    }

    /**
     * Returns the instance of Otto.
     * If the instance does not exist, create a new instance.
     *
     * @return Instance of Otto.
     */
    public static Otto getInstance() {
        if (instance == null) {
            instance = new Otto();
        }
        return instance;
    }

    /**
     * Returns the UI object.
     *
     * @return UI object.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Runs the program.
     * Displays the welcome message and takes in user input.
     */
    private void run() {
        ui.intro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else {
                handleInput(userInput);
            }
        }
        ui.exit();
    }

    /**
     * Adds a task to the task list.
     *
     * @param info Array of strings containing task information.
     */
    private void addTask(String[] info) {
        Task newTask = this.taskList.addTask(info);
        ui.displayAddedTask(newTask, taskList.getNumOfTasks());
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the task to be deleted.
     * @throws OttoException If the index is out of bounds.
     */
    private void deleteTask(int index) throws OttoException {
        try {
            Task deletedTask = this.taskList.deleteTask(index - 1);
            ui.displayDeletedTask(deletedTask, taskList.getNumOfTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new OttoException(OttoResponses.indexError);
        }
    }

    /**
     * Marks a task as complete or incomplete.
     *
     * @param index Index of the task to be marked.
     * @param status True if task is to be marked as complete, false if incomplete.
     * @throws OttoException If the index is out of bounds.
     */
    private void markComplete(int index, boolean status) throws OttoException {
        try {
            Task task = this.taskList.markComplete(index - 1, status);
            ui.displayMarkedTask(status, task);
        } catch (IndexOutOfBoundsException e) {
            throw new OttoException(OttoResponses.indexError);
        }
    }

    /**
     * Handles user input and executes the corresponding command.
     * Actions include listing tasks, adding tasks, deleting tasks, marking tasks as complete or incomplete.
     *
     * @param userInput User input.
     */
    private void handleInput(String userInput) {
        if (Objects.equals(userInput, "")) {
            return;
        }
        String[] command = userInput.split("\\s+", 2);
        try {
            switch (command[0].toLowerCase()) {
                case "list":
                    ui.displayTaskList(this.taskList.toString());
                    break;
                case "mark":
                    this.markComplete(Parser.parseMarkComplete(command), true);
                    break;
                case "unmark":
                    this.markComplete(Parser.parseMarkComplete(command), false);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    this.addTask(Parser.parseTask(command[0].toLowerCase(), userInput));
                    break;
                case "delete":
                    this.deleteTask(Parser.parseDeleteTask(command));
                    break;
                default:
                    throw new OttoException(OttoResponses.unknownCommandError);
            }
        } catch (OttoException e) {
            ui.displayErrorMsg(e);
        }
    }

    /**
     * Main method to run the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Otto instance = new Otto();
        instance.run();
    }
}
