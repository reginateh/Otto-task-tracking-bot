package otto;

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

    public void displayAddedTask(Task newTask, int numOfTasks) {
        displayMsg(OttoResponses.addTask + newTask.toString()
                + String.format(OttoResponses.numOfTasks, numOfTasks));
    }

    public void displayDeletedTask(Task deletedTask, int numOfTasks) {
        this.displayMsg(OttoResponses.deleteTask
                + deletedTask.toString()
                + String.format(OttoResponses.numOfTasks, numOfTasks));
    }

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
