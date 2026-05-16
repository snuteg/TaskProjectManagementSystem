public class SortTasksCommand implements Command {
    private final ReportService reportService;
    private final InputValidator validator;

    public SortTasksCommand(ReportService reportService, InputValidator validator) {
        this.reportService = reportService;
        this.validator = validator;
    }

    @Override
    public void execute() {
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
