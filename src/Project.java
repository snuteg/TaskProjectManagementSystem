import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    private String name;
    private List<Integer> taskIds;
    private int id;

    public Project(String name) {
        this.name = name;
        this.taskIds = new ArrayList<>();
    }

    public int getId() {return id;}
    public String getName() {return name;}

    public List<Integer> getTaskIds() {
        return taskIds;
    }

    public void setId(int id) {this.id = id;}
    public void setTaskId(int task) {taskIds.add(task);}

    @Override
    public String toString() {
        return "ID: " + id + ". Name: " + name;
    }
}
