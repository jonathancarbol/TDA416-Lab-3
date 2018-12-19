
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Directed graph - A class used to determine the shortest path between two stops as well as the minimum spanning tree.
 * En klass för bestämning av den kortarste vägen melland två hållplatser och det minsta uppspännande träd.
 *
 * @param <E> which is a generic that extends Edge.
 *
 * @author Jonathan Carbol & Maria Fornmark
 * @version 1.0
 */
public class DirectedGraph<E extends Edge> {

    private List<E>[] edgeList;
    private int noOfNodes;


    /**
     * The constructor for the klass with number of nodes as input. It creates an empty list of the same size as
     * number of nodes.
     * Konstruktorn för klassen som har antal hållplatser som input. Den skapar samtidigt en tom list av samma storlek
     * som antal hållplatser.
     *
     * @param noOfNodes - Number of nodes or buss stops.
     */
    public DirectedGraph(int noOfNodes) {
        this.noOfNodes = noOfNodes;
        this.edgeList = new List[noOfNodes];
    }

    /**
     * Method used to add the edges to the DirectedGraph. The edges can beused to calculate the shortest path and the
     * MST.
     * Metod för tillägning av bågar till DirectedGraph. Tar in alla bågar som används för bestämning av kortaste vägen
     * och MST.
     *
     * @param e - The edge which is to be added.
     */
    public void addEdge(E e) {
        if (edgeList[e.from] == null) {
            edgeList[e.from] = new ArrayList<E>();
        }
        edgeList[e.from].add(e);

    }

    /**
     * Method used to determine the shortest path between two stops. It uses Dujkstras algorithm to determine this.
     * Metod för bestämning av kortaste vägen mellan två hållplatser. Använder Dijkstras algoritm för att bestämma detta.
     *
     * @param from - The edges starting point.
     * @param to - The edges end point.
     * @return An iterator with the shortest path.
     */
    public Iterator<E> shortestPath(int from, int to) {
        PriorityQueue<CompDijkstraPath> pq = new PriorityQueue<>(); // A priority queue which is sorted according to CompDijkstraPath.
        boolean[] visited = new boolean[noOfNodes]; // A list of all the nodes with a boolean to determine if the path for it already been calculated.

        pq.add(new CompDijkstraPath(from, 0, null)); // Starting point added to the priority queue.
        while (!pq.isEmpty()) {
            CompDijkstraPath current = pq.remove();
            if (visited[current.getTo()] == false) { // Check to see if the nodes has already been calculated.
                if (current.getTo() == to) {
                    return current.getPath().iterator(); // Returns the path is when it has been determined.
                } else {
                    visited[current.getTo()] = true;

                    for (int i = 0; i < edgeList[current.getTo()].size(); i++) { // Loops through all nodes connected to the current node.
                        E edge = edgeList[current.getTo()].get(i);
                        double cost = current.getCost() + edge.getWeight(); // Calculates the new cost of the edge.

                        List<E> path = new ArrayList();
                        if (current.getPath() != null) { // Changes the path of the edges.
                            List<E>  tmp = current.getPath();
                            for(E e : tmp){
                                path.add(e);
                            }
                        }
                        path.add(edge);

                        CompDijkstraPath cdp = new CompDijkstraPath(edge.to, cost, path); // Creates a new CompDijkstraPath.
                        pq.add(cdp); // Adds the CDP to the priority queue.
                    }
                }
            }
        }
        return null;
    }

    /**
     * A method used to determine the minimum spanning tree for all buss stops. It uses Kruskals algorithm with lists.
     * En metod för bestämning av minsta uppspända trädet för alla hållplatser. Använder Kruskals algoritm med listor.
     *
     * @return A list with all the nodes in the MST.
     */
    public Iterator<E> minimumSpanningTree() {
        List[] cc = new List[noOfNodes]; // Creates an array with number of nodes as size.
        for (int i = 0; i < cc.length; i++){ // Creates an empty list for every node.
            cc[i] = new ArrayList<BusEdge>();
        }

        PriorityQueue<CompKruskalEdge> pq = new PriorityQueue<>(); // New priority queue sorted according to CompKruskalEdge.
        for (int i = 0; i < edgeList.length; i++){ // Adds all edges to the priority queue.
            for (int j = 0; j < edgeList[i].size(); j++){
                E be = edgeList[i].get(j);
                pq.add(new CompKruskalEdge(be));
            }
        }

        int large;
        int small;
        while (!pq.isEmpty() && cc.length > 0){
            Edge e = pq.remove().getBedge(); // Remove first item from priority queue.

            if ( cc[e.from] != cc[e.to]){ // Check to see that from and to does not refer to the same list.
                if (cc[e.from].size() >= cc[e.to].size()){ // Check to see which of the lists are smallest.
                    large = e.from;
                    small = e.to;
                }else {
                    large = e.to;
                    small = e.from;
                }
                if(cc[small].size() > 0) { // Adds the smaller list to the larger list.
                    for (int i = 0; i < cc[small].size(); i++) {
                        cc[large].add(cc[small].get(i));
                    }
                }
                cc[large].add(e); // Adds the item removed from the priority queue.

                for (int i = 0; i < cc[large].size(); i++){ // Goes through the new larger lists and repoint all the other lists to that list.
                    BusEdge b = (BusEdge) cc[large].get(i);
                    cc[b.from] = cc[large];
                    cc[b.to] = cc[large];
                }
            }
        }
        return cc[0].iterator(); // Returns the list with all nodes in a MST.
    }
}
  
