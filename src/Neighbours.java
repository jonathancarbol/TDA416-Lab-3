public class Neighbours {

    private int nodeNr;
    private double weight;

    public Neighbours(int nodeNr, double weight) {
        this.nodeNr = nodeNr;
        this.weight = weight;
    }

    public int getNodeNr() {
        return nodeNr;
    }

    public double getWeight() {
        return weight;
    }
}
