package otto;

/**
 * Contains methods to interact with the user.
 */
public class Ui {
    private void printLine() {
        System.out.println(OttoResponses.line.indent(4));
    }

    private void displayMsg(String msg) {
        printLine();
        System.out.println(msg.indent(4));
        printLine();
    }

    public void intro() {
        System.out.println((OttoResponses.owl + OttoResponses.intro).indent(4));
        printLine();
    }

    public void exit() {
        this.displayMsg(OttoResponses.bye);
    }

    public void displayTaskList(String taskList) {
        displayMsg(taskList);
    }

    /**
     * Displays the message when the user adds a task.
     * @param newTask Task that is added.
     * @param numOfTasks Number of tasks in the list.
     */
    public void displayAddedTask(Task newTask, int numOfTasks) {
        displayMsg(OttoResponses.addTask + newTask.toString()
                + String.format(OttoResponses.numOfTasks, numOfTasks));
    }

    /**
     * Displays the message when the user deletes a task.
     * @param deletedTask Task that is deleted.
     * @param numOfTasks Number of tasks left in the list.
     */
    public void displayDeletedTask(Task deletedTask, int numOfTasks) {
        this.displayMsg(OttoResponses.deleteTask
                + deletedTask.toString()
                + String.format(OttoResponses.numOfTasks, numOfTasks));
    }

    /**
     * Displays the message when the user marks a task as complete or incomplete.
     * @param status True if the task is marked as complete, false otherwise.
     * @param task Task that is marked.
     */
    public void displayMarkedTask(boolean status, Task task) {
        this.displayMsg((status
                ? OttoResponses.complete
                : OttoResponses.incomplete)
                + task.toString());
    }

    public void displayErrorMsg(Exception e) {
        this.displayMsg(e.getMessage());
    }
}
