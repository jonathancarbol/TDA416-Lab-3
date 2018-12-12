import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CompDijkstraPath<E extends Edge> implements Comparable<E> {

    private final int to;
    private int cost;
    private List<E> path;

    public CompDijkstraPath(int to, int cost, List<E> path) {
        this.to = to;
        this.cost = cost;
        this.path = path;
    }


    @Override
    public int compareTo(E e) {
        if (this.cost <= e.getWeight()){
            return -1;
        }else {
            return 1;
        }
    }

    public int getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    public List<E> getPath() {
        return path;
    }
}
