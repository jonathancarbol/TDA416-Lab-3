
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class DirectedGraph<E extends Edge> {

    private List<E>[] edgeList;
    private int noOfNodes;


    public DirectedGraph(int noOfNodes) {
        this.noOfNodes = noOfNodes;
        this.edgeList = new List[noOfNodes];
    }

    public void addEdge(E e) {
        if (edgeList[e.from] == null) {
            edgeList[e.from] = new ArrayList<E>();
        }
        edgeList[e.from].add(e);

    }

    public Iterator<E> shortestPath(int from, int to) {
        PriorityQueue<CompDijkstraPath> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[noOfNodes];
        int inf = 1000;

        pq.add(new CompDijkstraPath(from, 0, null));
        while (!pq.isEmpty()) {
            CompDijkstraPath current = pq.remove();
            if (visited[current.getTo()] == false) {
                if (current.getTo() == to) {
                    return current.getPath().iterator();
                } else {
                    visited[current.getTo()] = true;

                    for (int i = 0; i < edgeList[current.getTo()].size(); i++) {
                        E edge = edgeList[current.getTo()].get(i); //x.get(i) är en busedge;
                        double cost = current.getCost() + edge.getWeight();


                        List<E> path = new ArrayList();
                        if (current.getPath() != null) {
                            List<E>  tmp = current.getPath();
                            for(E e : tmp){
                                path.add(e);
                            }
                            //path = current.getPath();
                        }
                        path.add(edge); //Vad ska läggas till? HUr när och varför?


                        CompDijkstraPath cdp = new CompDijkstraPath(edge.to, cost, path); //Är det edge.to?
                        pq.add(cdp);
                    }
                }
            }
        }
        return null;
    }

    public Iterator<E> minimumSpanningTree() {
        List[] cc = new List[noOfNodes];
        for (int i = 0; i < cc.length; i++){
            cc[i] = new ArrayList<CompKruskalEdge>();
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


            if ( cc[e.from]!= cc[e.to]){
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
                cc[small] = cc[large];
            }
        }

        return cc[0].iterator();
    }

}
  
