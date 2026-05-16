import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private int id;
    private String name;
    private LocalDate deadline;
    private TaskStatus status;
    private Priority priority;

    public Task(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
        this.status = TaskStatus.NEW;
        this.priority = Priority.LOW;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public LocalDate getDeadline() {return deadline;}
    public TaskStatus getStatus() {return status;}
    public Priority getPriority() {return priority;}

    public void setPriority(Priority priority) {this.priority = priority;}
    public void setId(int id) {this.id = id;}
    public void setDeadline(LocalDate deadline) {this.deadline = deadline;}
    public void setStatus(TaskStatus status) {this.status = status;}

    @Override
    public String toString() {
        return "ID: " + id
                + ". Name: " + name
                + " | Status: " + status
                + " | Deadline: " + deadline
                + " | Priority: " + priority;
    }
}
