import java.util.List;

/**
 * CompDijkstraPath is a class for holding elements for
 * computing shortest path with Dijkstras algorithm. It holds a
 * method for comparing elements.
 * @param <E> E extends Edge
 */
public class CompDijkstraPath<E extends Edge> implements Comparable<CompDijkstraPath<E>> {

    private final int to;
    private double cost;
    private List<E> path;

    /**
     * @param to the number of the node reached
     * @param cost the cost of the path from the start node
     * @param path the previous path from the start node
     */
    public CompDijkstraPath(int to, double cost, List<E> path) {
        this.to = to;
        this.cost = cost;
        this.path = path;
    }

    /**
     * The compareTo method compares the objects, looking at
     * the cost and length of the path.
     * @param e CompDijkstraPath
     * @return 1 or -1 depending on what object is the largest.
     */
    @Override
    public int compareTo(CompDijkstraPath e) {
        if (this.cost <= e.getCost()) {
            if (this.getPath().size() <= e.getPath().size()) {
                return -1;
            }
        }
        return 1;
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
