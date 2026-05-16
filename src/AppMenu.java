import java.time.LocalDate;
import java.util.Scanner;

public class AppMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator validator = new InputValidator(scanner);
    private final TaskService taskService;
    private final ProjectService projectService;
    private final ReportService reportService;

    public AppMenu(TaskService taskService, ProjectService projectService, ReportService reportService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.reportService = reportService;
    }

    public void start() {
        projectService.load();
        taskService.load();

        boolean runnable = true;
        while (runnable) {
            printMenu();
            int choice = validator.readInt("Select menu item: ");

            switch (choice) {
                case 1 -> addProject();
                case 2 -> addTask();
                case 3 -> addTaskToProject();
                case 4 -> setTaskPriority();
                case 5 -> changeTaskStatus();
                case 6 -> reportService.showOverdueTasks();
                case 7 -> showTasksByProject();
                case 8 -> sortTasks();
                case 0 -> {
                    projectService.save();
                    taskService.save();
                    System.out.println("Data saved, exiting");
                    runnable = false;
                }
                default -> System.out.println("You have selected an incorrect menu item");
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

    public void addProject() {
        String name = validator.readString("Project name: ");
        projectService.addProject(new Project(name));
        System.out.println("Project added");
    }

    public void addTask() {
        String name = validator.readString("Task name: ");
        LocalDate deadline = validator.readDate("Enter deadline [Format: 01.01.2000]: ");
        taskService.addTask(new Task(name, deadline));
        System.out.println("Task added");
    }

    public void addTaskToProject() {
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

    public void setTaskPriority() {
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

    public void changeTaskStatus() {
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

    public void showTasksByProject() {
        reportService.showAllProjects();
        int projectId = validator.readInt("Select Project ID: ");

        reportService.showTasksByProject(projectId);
    }

    public void sortTasks() {
        SortStrategy sortStrategy = setSortStrategy();
        reportService.generateAndPrint(sortStrategy);
    }

    public SortStrategy setSortStrategy() {
        System.out.println("Sorting strategies:");
        System.out.println("1. Sort by Deadline");
        System.out.println("2. Sort by Priority");
        System.out.println("3. Sort by Status");

        while (true) {
            int choice = validator.readInt("Select an item: ");

            switch (choice) {
                case 1 -> {
                    return new DeadlineSortStrategy();
                }
                case 2 -> {
                    return new PrioritySortStrategy();
                }
                case 3 -> {
                    return new StatusSortStrategy();
                }
                default -> System.out.println("You have selected an incorrect status item");
            }
        }
    }
}
