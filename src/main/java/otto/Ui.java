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
        return OttoResponses.SHOW_LIST + taskList.toString();
    }

    /**
     * Displays the list of tasks that match the search query.
     *
     * @param findList List of tasks that match the search query.
     */
    public String displayFindResult(TaskList findList) {
        return OttoResponses.SHOW_FIND_RESULTS + findList.toString();
    }

    /**
     * Displays the list of tasks that match the tag.
     *
     * @param findList List of tasks that match the tag.
     */
    public String displayFindTagResult(TaskList findList) {
        return OttoResponses.SHOW_FIND_RESULTS + findList.toString();
    }
    /**
     * Displays the message when the user adds a task.
     *
     * @param newTask Task that is added.
     * @param numOfTasks Number of tasks in the list.
     */
    public String displayAddedTask(Task newTask, int numOfTasks) {
        return OttoResponses.ADD_TASK + newTask.toString()
                + String.format(OttoResponses.NUM_OF_TASKS, numOfTasks);
    }

    /**
     * Displays the message when the user deletes a task.
     *
     * @param deletedTask Task that is deleted.
     * @param numOfTasks Number of tasks left in the list.
     */
    public String displayDeletedTask(Task deletedTask, int numOfTasks) {
        return OttoResponses.DELETE_TASK
                + deletedTask.toString()
                + String.format(OttoResponses.NUM_OF_TASKS, numOfTasks);
    }

    /**
     * Displays the message when the user marks a task as complete or incomplete.
     *
     * @param status True if the task is marked as complete, false otherwise.
     * @param task Task that is marked.
     */
    public String displayMarkedTask(boolean status, Task task) {
        return (status
                ? OttoResponses.COMPLETE
                : OttoResponses.INCOMPLETE)
                + task.toString();
    }

    public String displayErrorMsg(Exception e) {
        return e.getMessage();
    }
}
