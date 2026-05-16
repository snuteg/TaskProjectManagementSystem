import java.util.List;

public interface TaskRepository {
    void save(Task task);
    void delete(Task task);
    List<Task> findAll();
    void saveToFile();
    void loadFromFile();
}
