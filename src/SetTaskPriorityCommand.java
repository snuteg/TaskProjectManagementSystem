public class SetTaskPriorityCommand implements Command {
    private final ReportService reportService;
    private final TaskService taskService;
    private final InputValidator validator;

    public SetTaskPriorityCommand(ReportService reportService, TaskService taskService, InputValidator validator) {
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

        Priority priority = setPriority();

        task.setPriority(priority);
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
