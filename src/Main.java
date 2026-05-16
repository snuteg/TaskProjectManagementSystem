public class Main {
    public static void main(String[] args) {
        TaskRepository taskRepository = new FileTaskRepository();
        TaskService taskService = new TaskService(taskRepository);
        ProjectRepository projectRepository = new FileProjectRepository();
        ProjectService projectService = new ProjectService(projectRepository, taskService);
        ReportService reportService = new ReportService(projectService, taskService);

        AppMenu app = new AppMenu(taskService, projectService, reportService);
        app.start();
    }
}
