package otto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods to load and save tasks from storage.
 */
public class Storage {
    /**
     * Paths of directory and file.
     */
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/tasks.txt";

    /**
     * Checks if the target directory and file exist.
     * If not, create them.
     */
    private static void checkAndCreateFile() {
        File directory = new File(DIRECTORY_PATH);
        File file = new File(FILE_PATH);

        try {
            // Check if the directory exists, if not create it
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    return;
                }
            }

            // Check if the file exists, if not create it
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            Otto.getInstance().getUi()
                    .displayErrorMsg(new OttoException(OttoResponses.CREATE_FILE_ERROR));
        }
    }

    /**
     * Loads tasks from storage.
     * If file does not exist or any error occurs, return an empty ArrayList.
     * @return ArrayList of tasks loaded from storage.
     */
    public static ArrayList<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                Task newTask = Parser.parseTasksFromStorage(s);
                if (newTask != null) {
                    tasks.add(newTask);
                }
            }
            sc.close();
            return tasks;
        } catch (IOException e) {
            Otto.getInstance().getUi()
                    .displayErrorMsg(new OttoException(OttoResponses.LOAD_FILE_ERROR));
            return new ArrayList<>();
        }
    }

    /**
     * Saves tasks to storage.
     * @param tasks ArrayList of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            checkAndCreateFile();
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.getTaskStringForStorage() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Otto.getInstance().getUi()
                    .displayErrorMsg(new OttoException(OttoResponses.SAVE_FILE_ERROR));
        }
    }
}
