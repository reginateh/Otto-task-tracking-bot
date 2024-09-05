import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/tasks.txt";

    private static void createDataDirectory() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Directory " + DIRECTORY_PATH + " created.");
            } else {
                System.out.println("Failed to create directory " + DIRECTORY_PATH);
            }
        }
    }

    private static void createDataFile() {
        File file = new File(FILE_PATH);
        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("File " + FILE_PATH + " created.");
            } else {
                System.out.println("File " + FILE_PATH + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("Failed to create file " + FILE_PATH);
        }
    }

    private static void checkAndCreateFile() {
        File directory = new File(DIRECTORY_PATH);
        File file = new File(FILE_PATH);

        try {
            // Check if the directory exists, if not create it
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory " + DIRECTORY_PATH + " created.");
                } else {
                    System.out.println("Failed to create directory " + DIRECTORY_PATH);
                    return;
                }
            }

            // Check if the file exists, if not create it
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File " + FILE_PATH + " created.");
                } else {
                    System.out.println("Failed to create file " + FILE_PATH);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

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
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            checkAndCreateFile();
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.getTaskStringForStorage() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
