
import java.util.*;

public class DirectedGraph<E extends Edge> {

	private List<ArrayList> edgeList;
	private int noOfNodes;


	public DirectedGraph(int noOfNodes) {
		this.noOfNodes = noOfNodes;
		this.edgeList = new ArrayList<>(noOfNodes);
	}

	public void addEdge(E e) {
		edgeList.get(e.from).add(e.to);
	}

	public Iterator<E> shortestPath(int from, int to) {
		PriorityQueue<ArrayList> pq ;
		ArrayList<Boolean> visited;

	}
		
	public Iterator<E> minimumSpanningTree() {

		return null;
	}

}
  
