import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileProjectRepository implements ProjectRepository {
    private final String FILE_NAME = "projects.dat";
    private final List<Project> projects = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void save(Project project) {
        project.setId(nextId++);
        projects.add(project);
    }

    @Override
    public void delete(Project project) {
        projects.remove(project);
    }

    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projects);
    }

    public void setAll(List<Project> loadedProjects) {
        projects.clear();
        projects.addAll(loadedProjects);

        int maxId = 0;
        for (Project project : projects) {
            if (project.getId() > maxId) {
                maxId = project.getId();
            }
        }

        nextId = maxId + 1;
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(projects);
        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }

    @Override
    public void loadFromFile() {
        if (!Files.exists(Path.of(FILE_NAME))) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Project> loadedProjects = (List<Project>) ois.readObject();

            if (!loadedProjects.isEmpty()) {
                setAll(loadedProjects);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data");
        }
    }
}
