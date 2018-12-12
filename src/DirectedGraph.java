
import java.util.*;

public class DirectedGraph<E extends Edge> {

	private ArrayList[] edgeList;
	private int noOfNodes;


	public DirectedGraph(int noOfNodes) {
		this.noOfNodes = noOfNodes;
		this.edgeList = new ArrayList[noOfNodes];
	}

	public void addEdge(E e) {
		if (edgeList[e.from] == null){
			edgeList[e.from] = new ArrayList<Neighbours>();
		}
		Neighbours n = new Neighbours(e.to, e.getWeight());
		edgeList[e.from].add(n);

	}

	public Iterator<E> shortestPath(int from, int to) {
		PriorityQueue<CompDijkstraPath> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[noOfNodes];
		int inf = 1000;

		pq.add(new CompDijkstraPath(from, 0, null));
		while (!pq.isEmpty()){
			CompDijkstraPath current = pq.remove();
			if (visited[current.getTo()] == false){
				if (current.getTo() == to){
					return current.getPath().iterator();
				}else {
					visited[current.getTo()] = true;
					for(int i = 0; i < edgeList[from].size(); i++){
						int node = edgeList[from].get(i);
						double cost = current.getCost() + edgeList[from].get(i)[1];
						List path = current.getPath();
						path.add(current.getTo());

						ArrayList test = edgeList[from];
						Integer[] test2 = edgeList[from].get(i);

						pq.add(new CompDijkstraPath(node, cost, path);
					}
				}
			}
		}
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {

		return null;
	}

}
  
