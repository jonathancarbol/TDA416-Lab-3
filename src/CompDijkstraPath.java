import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CompDijkstraPath<E extends Edge> implements Comparable<CompDijkstraPath<E>> {

    private final int to;
    private double cost;
    private List<E> path;

    public CompDijkstraPath(int to, double cost, List<E> path) {
        this.to = to;
        this.cost = cost;
        this.path = path;
    }


    @Override
    public int compareTo(CompDijkstraPath e) {
        if (this.cost <= e.getCost()){
            return -1;
        }else {
            return 1;
        }
    }

    public int getTo() {
        return to;
    }

    public double getCost() {
        return cost;
    }

    public List<E> getPath() {
        return path;
    }
}
