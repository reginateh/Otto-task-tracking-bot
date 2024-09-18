package otto;

/**
 * Contains methods to interact with the user.
 */
public class Ui {

    /**
     * Displays the list of tasks.
     *
     * @param taskList List of tasks.
     */
    public String displayTaskList(TaskList taskList) {
        return OttoResponses.showList + taskList.toString();
    }

    /**
     * Displays the list of tasks that match the search query.
     *
     * @param findList List of tasks that match the search query.
     */
    public String displayFindResult(TaskList findList) {
        return OttoResponses.showFindResults + findList.toString();
    }

    /**
     * Displays the message when the user adds a task.
     *
     * @param newTask Task that is added.
     * @param numOfTasks Number of tasks in the list.
     */
    public String displayAddedTask(Task newTask, int numOfTasks) {
        return OttoResponses.addTask + newTask.toString()
                + String.format(OttoResponses.numOfTasks, numOfTasks);
    }

    /**
     * Displays the message when the user deletes a task.
     *
     * @param deletedTask Task that is deleted.
     * @param numOfTasks Number of tasks left in the list.
     */
    public String displayDeletedTask(Task deletedTask, int numOfTasks) {
        return OttoResponses.deleteTask
                + deletedTask.toString()
                + String.format(OttoResponses.numOfTasks, numOfTasks);
    }

    /**
     * Displays the message when the user marks a task as complete or incomplete.
     *
     * @param status True if the task is marked as complete, false otherwise.
     * @param task Task that is marked.
     */
    public String displayMarkedTask(boolean status, Task task) {
        return (status
                ? OttoResponses.complete
                : OttoResponses.incomplete)
                + task.toString();
    }

    public String displayErrorMsg(Exception e) {
        return e.getMessage();
    }
}
