public class ShowOverdueTasksCommand implements Command {
    private final ReportService reportService;

    public ShowOverdueTasksCommand(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public void execute() {
        reportService.showOverdueTasks();
    }
}
