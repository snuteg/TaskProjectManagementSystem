import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator validator = new InputValidator(scanner);

    private final Map<Integer, Command> commands = new HashMap<>();

    private final ProjectService projectService;
    private final TaskService taskService;

    public AppMenu(TaskService taskService, ProjectService projectService, ReportService reportService) {
        this.taskService = taskService;
        this.projectService = projectService;

        commands.put(1, new AddProjectCommand(projectService, validator));
        commands.put(2, new AddTaskCommand(taskService, validator));
        commands.put(3, new AddTaskToProjectCommand(reportService, projectService, validator));
        commands.put(4, new SetTaskPriorityCommand(reportService, taskService, validator));
        commands.put(5, new ChangeTaskStatusCommand(reportService, taskService, validator));
        commands.put(6, new ShowOverdueTasksCommand(reportService));
        commands.put(7, new ShowTasksByProjectCommand(reportService, validator));
        commands.put(8, new SortTasksCommand(reportService, validator));
    }

    public void start() {
        projectService.load();
        taskService.load();

        boolean runnable = true;

        while (runnable) {
            printMenu();

            int choice = validator.readInt("Select menu item: ");

            if (choice == 0) {
                projectService.save();
                taskService.save();
                System.out.println("Data saved, exiting");
                runnable = false;
                continue;
            }

            Command command = commands.get(choice);

            if (command != null) {
                command.execute();
            } else {
                System.out.println("Incorrect menu item");
            }
        }
    }

    public void printMenu() {
        System.out.println("=====Task / Project Management System=====");
        System.out.println("1. Add project");
        System.out.println("2. Add task");
        System.out.println("3. Add a task to a project");
        System.out.println("4. Set priority");
        System.out.println("5. Change task status");
        System.out.println("6. Show overdue tasks");
        System.out.println("7. Show tasks by Project");
        System.out.println("8. Sort tasks");
        System.out.println("0. Save and exit");
    }
}
