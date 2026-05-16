import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileTaskRepository implements TaskRepository {
    private final String FILE_NAME = "tasks.dat";
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void save(Task task) {
        task.setId(nextId++);
        tasks.add(task);
    }

    @Override
    public void delete(Task task) {
        tasks.remove(task);
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public void setAll(List<Task> loadedTasks) {
        tasks.clear();
        tasks.addAll(loadedTasks);

        int maxId = 0;
        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }

        nextId = maxId + 1;
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }

    @Override
    public void loadFromFile() {
        if (!Files.exists(Path.of(FILE_NAME))) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Task> loadedTasks = (List<Task>) ois.readObject();

            if (!loadedTasks.isEmpty()) {
                setAll(loadedTasks);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data");
        }
    }
}
