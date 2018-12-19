
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Directed graph - En klass för bestämning av den kortarste vägen melland två hållplatser och det minsta uppspännande
 * träd.
 * @param <E> som är en generic som extendar Edge.
 *
 * @author Jonathan Carbol & Maria Fornmark
 * @version 1.0
 */
public class DirectedGraph<E extends Edge> {

    private List<E>[] edgeList;
    private int noOfNodes;


    /**
     * Konstruktorn för klassen som har antal hållplatser som input. Den skapar samtidigt en tom list av samma storlek
     * som antal hållplatser.
     * @param noOfNodes - antal hållplatser eller noder i inputen.
     */
    public DirectedGraph(int noOfNodes) {
        this.noOfNodes = noOfNodes;
        this.edgeList = new List[noOfNodes];
    }

    /**
     * Metod för tillägning av bågar till DirectedGraph. Tar in alla bågar som används för bestämning av kortaste vägen
     * och MST.
     * @param e - bågen som ska läggas till.
     */
    public void addEdge(E e) {
        if (edgeList[e.from] == null) {
            edgeList[e.from] = new ArrayList<E>();
        }
        edgeList[e.from].add(e);

    }

    /**
     * Metod för bestämning av kortaste vägen mellan två hållplatser. Använder Dijkstras algoritm för att bestämma detta.
     * @param from - Bågens starthållplats.
     * @param to - Bågens ändhållplats.
     * @return En iterator med kortaste vägen.
     */
    public Iterator<E> shortestPath(int from, int to) {
        PriorityQueue<CompDijkstraPath> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[noOfNodes];

        pq.add(new CompDijkstraPath(from, 0, null));
        while (!pq.isEmpty()) {
            CompDijkstraPath current = pq.remove();
            if (visited[current.getTo()] == false) {
                if (current.getTo() == to) {
                    return current.getPath().iterator();
                } else {
                    visited[current.getTo()] = true;

                    for (int i = 0; i < edgeList[current.getTo()].size(); i++) {
                        E edge = edgeList[current.getTo()].get(i);
                        double cost = current.getCost() + edge.getWeight();

                        List<E> path = new ArrayList();
                        if (current.getPath() != null) {
                            List<E>  tmp = current.getPath();
                            for(E e : tmp){
                                path.add(e);
                            }
                        }
                        path.add(edge);

                        CompDijkstraPath cdp = new CompDijkstraPath(edge.to, cost, path);
                        pq.add(cdp);
                    }
                }
            }
        }
        return null;
    }

    /**
     * En metod för bestämning av minsta uppspända trädet för alla hållplatser. Använder Kruskals algoritm med listor.
     * @return En lista med alla noder i MST:n.
     */
    public Iterator<E> minimumSpanningTree() {
        List[] cc = new List[noOfNodes];
        for (int i = 0; i < cc.length; i++){
            cc[i] = new ArrayList<BusEdge>();
        }

        PriorityQueue<CompKruskalEdge> pq = new PriorityQueue<>();
        for (int i = 0; i < edgeList.length; i++){
            for (int j = 0; j < edgeList[i].size(); j++){
                E be = edgeList[i].get(j);
                pq.add(new CompKruskalEdge(be));
            }
        }

        int large;
        int small;
        while (!pq.isEmpty() && cc.length > 0){
            Edge e = pq.remove().getBedge();

            if ( cc[e.from] != cc[e.to]){
                if (cc[e.from].size() >= cc[e.to].size()){
                    large = e.from;
                    small = e.to;
                }else {
                    large = e.to;
                    small = e.from;
                }
                if(cc[small].size() > 0) {
                    for (int i = 0; i < cc[small].size(); i++) {
                        cc[large].add(cc[small].get(i));
                    }
                }
                cc[large].add(e);

                for (int i = 0; i < cc[large].size(); i++){
                    BusEdge b = (BusEdge) cc[large].get(i);
                    cc[b.from] = cc[large];
                    cc[b.to] = cc[large];
                }
            }
        }
        return cc[0].iterator();
    }
}
  
