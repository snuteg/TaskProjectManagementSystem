import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    private String name;
    private List<Integer> tasksId;
    private int id;

    public Project(String name) {
        this.name = name;
        this.tasksId = new ArrayList<>();
    }

    public int getId() {return id;}
    public String getName() {return name;}

    public int getTaskId(int taskId) {
        for (Integer task : tasksId) {
            if (task == taskId) {
                return task;
            }
        }
        return 0;
    }

    public List<Integer> getTaskIds() {
        return tasksId;
    }

    public void setId(int id) {this.id = id;}
    public void setTaskId(int task) {tasksId.add(task);}

    @Override
    public String toString() {
        return "ID: " + id + ". Name: " + name;
    }
}
