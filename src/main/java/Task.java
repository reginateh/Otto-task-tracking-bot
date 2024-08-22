public class Task {
    private static int numOfTasks = 0;
    private int id;
    private String description;

    public Task(String description) {
        this.description = description;
        Task.numOfTasks++;
        this.id = Task.numOfTasks;
    }


    @Override
    public String toString() {
        return String.format("\t%d. %s", this.id, this.description);
    }
}
