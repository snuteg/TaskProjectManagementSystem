import java.time.LocalDate;

public class AddTaskCommand implements Command {
    private final TaskService taskService;
    private final InputValidator validator;

    public AddTaskCommand(TaskService taskService, InputValidator validator) {
        this.taskService = taskService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        String name = validator.readString("Task name: ");
        LocalDate deadline = validator.readDate("Enter deadline [Format: dd.MM.yyyy]: ");

        taskService.addTask(new Task(name, deadline));
        System.out.println("Task added");
    }
}
