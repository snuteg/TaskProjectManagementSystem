public class AddTaskToProjectCommand implements Command {
    private final ProjectService projectService;
    private final ReportService reportService;
    private final InputValidator validator;

    public AddTaskToProjectCommand(ReportService reportService, ProjectService projectService, InputValidator validator) {
        this.reportService = reportService;
        this.projectService = projectService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        reportService.showAllProjects();
        int projectId = validator.readInt("Select Project ID: ");

        reportService.showAllTasks();
        int taskId = validator.readInt("Select Task ID: ");

        Priority priority = setPriority();

        projectService.addTaskToProject(projectId, taskId, priority);
        System.out.println("Task added to a project");
    }

    public Priority setPriority() {
        System.out.println("Priority list:");
        System.out.println("1. Low");
        System.out.println("2. Medium");
        System.out.println("3. High");

        while (true) {
            int choice = validator.readInt("Select an item: ");

            switch (choice) {
                case 1 -> {
                    return Priority.LOW;
                }
                case 2 -> {
                    return Priority.MEDIUM;
                }
                case 3 -> {
                    return Priority.HIGH;
                }
                default -> System.out.println("You have selected an incorrect priority item");
            }
        }
    }
}
