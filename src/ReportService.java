public class ReportService {
    private ProjectService projectService;
    private TaskService taskService;

    public ReportService(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public void showTasksByProject(int projectId) {
        Project project = projectService.findById(projectId);

        if (project == null) {
            System.out.println("The project does not exist");
            return;
        }

        System.out.println("Project: " + project.getName());

        System.out.println("List of tasks:");
        for (Integer taskId : project.getTaskIds()) {
            Task task = taskService.findById(taskId);

            if (task != null) {
                System.out.println(
                        "Task: " + task.getName()
                        + " | Priority: " + task.getPriority()
                        + " | Deadline: " + task.getDeadline()
                        + " | Status: " + task.getStatus()
                );
            }
        }
    }

    public void generateAndPrint(SortStrategy sortStrategy) {
        if (taskService.getAllTasks().isEmpty()) {
            System.out.println("The list is empty");
            return;
        }

        sortStrategy.sort(taskService.getAllTasks());

        for (Task task : taskService.getAllTasks()) {
            System.out.println(task);
        }
    }

    public void showOverdueTasks() {
        if (taskService.getAllTasks().isEmpty()) {
            System.out.println("The list is empty");
            return;
        }

        System.out.println("Overdue tasks list:");
        for (Task task : taskService.getAllTasks()) {
            if (task.getStatus() == TaskStatus.OVERDUE) {
                System.out.println(task);
            }
        }
    }

    public void showAllProjects() {
        if (projectService.getAllProjects().isEmpty()) {
            System.out.println("The list is empty");
            return;
        }

        System.out.println("List of projects:");
        for (Project project : projectService.getAllProjects()) {
            System.out.println(project);
        }
    }

    public void showAllTasks() {
        if (taskService.getAllTasks().isEmpty()) {
            System.out.println("The list is empty");
            return;
        }

        System.out.println("List of tasks:");
        for (Task task : taskService.getAllTasks()) {
            System.out.println(task);
        }
    }
}
