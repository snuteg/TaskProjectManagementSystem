import java.util.Comparator;
import java.util.List;

public class StatusSortStrategy implements SortStrategy {
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getStatus() == null && o2.getStatus() == null) return 0;
                if (o1.getStatus() == null) return -1;
                if (o2.getStatus() == null) return 1;

                return o1.getStatus().compareTo(o2.getStatus());
            }
        });
    }
}
