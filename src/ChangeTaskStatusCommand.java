public class ChangeTaskStatusCommand implements Command {
    private final ReportService reportService;
    private final TaskService taskService;
    private final InputValidator validator;

    public ChangeTaskStatusCommand(ReportService reportService, TaskService taskService, InputValidator validator) {
        this.reportService = reportService;
        this.taskService = taskService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        reportService.showAllTasks();
        int taskId = validator.readInt("Select Task ID: ");

        Task task = taskService.findById(taskId);

        if (task == null) {
            System.out.println("You have selected a non-existent task");
            return;
        }

        TaskStatus status = setStatus();

        task.setStatus(status);
    }

    public TaskStatus setStatus() {
        System.out.println("Status list:");
        System.out.println("1. New");
        System.out.println("2. Done");
        System.out.println("3. In progress");
        System.out.println("4. Overdue");

        while (true) {
            int choice = validator.readInt("Select an item: ");

            switch (choice) {
                case 1 -> {
                    return TaskStatus.NEW;
                }
                case 2 -> {
                    return TaskStatus.DONE;
                }
                case 3 -> {
                    return TaskStatus.IN_PROGRESS;
                }
                case 4 -> {
                    return TaskStatus.OVERDUE;
                }
                default -> System.out.println("You have selected an incorrect status item");
            }
        }
    }
}
