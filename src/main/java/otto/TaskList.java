package otto;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = Storage.loadTasks();
    }

    /**
     * Creates a new task and adds it to the task list.
     * @param info Array of strings containing task information.
     */
    public Task addTask(String[] info) {
        String type = info[0];
        Task newTask = switch (type) {
            case "todo" -> new Todo(info[1]);
            case "deadline" -> new Deadline(info[1], info[2]);
            case "event" -> new Event(info[1], info[2], info[3]);
            default -> null;
        };
        this.taskList.add(newTask);
        Storage.saveTasks(this.taskList);
        return newTask;
    }

    /**
     * Deletes a task from the task list.
     * @param taskIndex Index of the task to be deleted.
     * @return otto.Task that was deleted.
     */
    public Task deleteTask(int taskIndex) {
        Task deleted = this.taskList.remove(taskIndex);
        Storage.saveTasks(this.taskList);
        return deleted;
    }

    /**
     * Marks a task as complete or incomplete.
     * @param taskIndex Index of the task to be marked.
     * @param status True if task is to be marked as complete, false if incomplete.
     * @return otto.Task that was marked.
     */
    public Task markComplete(int taskIndex, boolean status) {
        if (taskIndex >= this.taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task marked = this.taskList.get(taskIndex).setComplete(status);
        Storage.saveTasks(this.taskList);
        return marked;
    }

    /**
     * Returns the number of tasks in the task list.
     */
    public int getNumOfTasks() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(OttoResponses.showList);
        int idx = 1;
        for (Task task : taskList) {
            res.append(String.format("\n%d. %s", idx++, task.toString()));
        }
        return res.toString();
    }
}
