import java.util.List;

public class TaskService {
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(Task task) {
        repository.save(task);
    }

    public void removeTask(Task task) {
        repository.delete(task);
    }

    public void save() {
        repository.saveToFile();
    }

    public void load() {
        repository.loadFromFile();
    }

    public Task findById(int id) {
        for (Task task : repository.findAll()) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }
}
