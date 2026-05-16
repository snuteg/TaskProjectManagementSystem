import java.util.List;

public interface ProjectRepository {
    void save(Project project);
    void delete(Project project);
    List<Project> findAll();
    void saveToFile();
    void loadFromFile();
}
