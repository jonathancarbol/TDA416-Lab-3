public class CompKruskalEdge<E extends Edge> implements Comparable<CompKruskalEdge<E>>  {

    private int from;
    private int to;
    private double weight;
    private E bedge;


    public CompKruskalEdge(E edge){
        this.from = edge.from;
        this.to = edge.to;
        this.weight = edge.getWeight();
        this.bedge = edge;
    }


    @Override
    public int compareTo(CompKruskalEdge<E> cke) {
        if(this.getWeight() <= cke.getWeight()){
            return -1;
        }
        return 1;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    public E getBedge() {
        return bedge;
    }
}
