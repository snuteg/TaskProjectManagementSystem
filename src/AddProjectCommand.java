public class AddProjectCommand implements Command {
    private final ProjectService projectService;
    private final InputValidator validator;

    public AddProjectCommand(ProjectService projectService, InputValidator validator) {
        this.projectService = projectService;
        this.validator = validator;
    }

    @Override
    public void execute() {
        String name = validator.readString("Project name: ");
        projectService.addProject(new Project(name));
        System.out.println("Project added");
    }
}
