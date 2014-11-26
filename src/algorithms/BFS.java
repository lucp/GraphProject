package algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import graphs.Graph;

public class BFS<VertexType, EdgeType> {

	private Graph<VertexType, EdgeType> graph;
	
	public BFS(Graph<VertexType, EdgeType> graph){
		this.graph = graph;
	}
	
	public LinkedList<VertexType> findPath(VertexType source, VertexType destination){
		LinkedList<VertexType> path = new LinkedList<VertexType>();
		LinkedList<VertexType> queue = new LinkedList<VertexType>();
		HashSet<VertexType> visited = new HashSet<VertexType>();
		path.add(source);	
		visited.add(source);
		for (VertexType neighbour : this.graph.getNeighbours(source)){
			if (!visited.contains(neighbour)){
				queue.add(neighbour);
				visited.add(neighbour);
			}
		}
		VertexType point = source;
		while (point != destination && !queue.isEmpty()){
			point = queue.getFirst();
			queue.removeFirst();
			for (VertexType neighbour : this.graph.getNeighbours(point)){
				if (!visited.contains(neighbour)){
					queue.add(neighbour);
					visited.add(neighbour);
				}
			}
			while (!this.graph.areNeighbours(path.getLast(), point)){ //TODO may be wrong - to rethink - wrong
				path.removeLast();
			}
			path.add(point);
		}
		return path;
	}
	
}
