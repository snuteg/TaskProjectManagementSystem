import java.util.List;

public class ProjectService {
    private ProjectRepository repository;
    private TaskService service;

    public ProjectService(ProjectRepository repository, TaskService service) {
        this.repository = repository;
        this.service = service;
    }

    public void addProject(Project project) {
        repository.save(project);
    }

    public void removeProject(Project project) {
        repository.delete(project);
    }

    public void save() {
        repository.saveToFile();
    }

    public void load() {
        repository.loadFromFile();
    }

    public void addTaskToProject(int projectId, int taskId, Priority priority) {
        Task task = service.findById(taskId);
        Project project = findById(projectId);

        if (task == null) {
            System.out.println("Task not found");
            return;
        }

        if (project == null) {
            System.out.println("Project not found");
            return;
        }

        project.setTaskId(taskId);
        task.setPriority(priority);
    }

    public Project findById(int id) {
        for (Project project : repository.findAll()) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }
}
