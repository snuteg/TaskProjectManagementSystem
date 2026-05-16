import java.util.Comparator;
import java.util.List;

public class DeadlineSortStrategy implements SortStrategy {
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getDeadline() == null && o2.getDeadline() == null) return 0;
                if (o1.getDeadline() == null) return -1;
                if (o2.getDeadline() == null) return 1;

                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });
    }
}
