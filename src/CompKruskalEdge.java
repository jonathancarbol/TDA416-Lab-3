/**
 * Class for holding elements for computing minimum spanning tree,
 * using Kruskals algorithm. It has a compareTo method for comparing
 * elements.
 * @param <E> e extends Edge
 */
public class CompKruskalEdge<E extends Edge> implements Comparable<CompKruskalEdge<E>>  {

    private int from;
    private int to;
    private double weight;
    private E bedge;

    /**
     * Constructor for CompKruskalEdge.
     * @param edge An edge between to nodes.
     */
    public CompKruskalEdge(E edge){
        this.from = edge.from;
        this.to = edge.to;
        this.weight = edge.getWeight();
        this.bedge = edge;
    }

    /**
     * The compareTo method compares the objects, looking at
     * the weight.
     * @param cke CompKruskalEdge
     * @return -1 or 1 depending on what object is largest.
     */
    @Override
    public int compareTo(CompKruskalEdge<E> cke) {
        if(this.getWeight() <= cke.getWeight()){
            return -1;
        }
        return 1;
    }

    public double getWeight() {
        return weight;
    }

    public E getBedge() {
        return bedge;
    }
}
