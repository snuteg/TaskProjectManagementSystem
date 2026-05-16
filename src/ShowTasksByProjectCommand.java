public class ShowTasksByProjectCommand implements Command {
    private final ReportService reportService;
    private final InputValidator validator;

    public ShowTasksByProjectCommand(ReportService reportService, InputValidator validator) {
        this.reportService = reportService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        reportService.showAllProjects();
        int projectId = validator.readInt("Select Project ID: ");

        reportService.showTasksByProject(projectId);
    }
}
