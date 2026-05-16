import java.util.Comparator;
import java.util.List;

public class PrioritySortStrategy implements SortStrategy {
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getPriority() == null && o2.getPriority() == null) return 0;
                if (o2.getPriority() == null) return -1;
                if (o1.getPriority() == null) return 1;

                return o2.getPriority().compareTo(o1.getPriority());
            }
        });
    }
}
