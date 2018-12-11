import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CompDijkstraPath<E extends Edge> implements Comparable{

    private final int start;
    private final int end;
    private List<Integer> global;

    public CompDijkstraPath(int start, int to, int noOfNodes) {
        this.start = start;
        this.end = to;
        this.global = new ArrayList<>();
    }

    public int compareTo(E e) {
        if ()
    }
}
