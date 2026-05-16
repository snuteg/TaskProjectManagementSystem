import java.util.List;

public interface TaskRepository {
    void save(Task task);
    void delete(Task task);
    List<Task> findAll();
    void setAll(List<Task> loadedTasks);
    void saveToFile();
    void loadFromFile();
}
