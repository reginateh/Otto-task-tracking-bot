import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task addTask(String[] info) {
        String type = info[0];
        Task newTask = switch (type) {
            case "todo" -> new Todo(info[1]);
            case "deadline" -> new Deadline(info[1], info[2]);
            case "event" -> new Event(info[1], info[2], info[3]);
            default -> null;
        };
        this.taskList.add(newTask);
        return newTask;
    }

    public Task deleteTask(int taskIndex) {
        return this.taskList.remove(taskIndex);
    }

    public Task markComplete(int taskIndex, boolean status) {
        if (taskIndex >= this.taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.taskList.get(taskIndex).setComplete(status);
    }

    public int getNumOfTasks() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Here are the tasks in your list. Otto would rather be asleep than dealing with all this.");
        int idx = 1;
        for (Task task : taskList) {
            res.append(String.format("\n%d. %s", idx++, task.toString()));
        }
        return res.toString();
    }
}
