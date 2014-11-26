package algorithms;

import java.util.LinkedList;

import graphs.Graph;

public class BFS<VertexType, EdgeType> {

	private Graph<VertexType, EdgeType> graph;
	private LinkedList<VertexType> queue;
	
	public BFS(Graph<VertexType, EdgeType> graph){
		this.graph = graph;
		this.queue = new LinkedList<VertexType>();
	}
	
	public LinkedList<VertexType> findPath(VertexType source, VertexType destination){
		LinkedList<VertexType> path = new LinkedList<VertexType>();
		path.add(source);
		for (VertexType neighbours : this.graph.getNeighbours(source)){
			this.queue.add(neighbours);
		}
		VertexType point = source;
		while (point != destination && !this.queue.isEmpty()){
			point = this.queue.getFirst();
			this.queue.removeFirst();
			for (VertexType neighbours : this.graph.getNeighbours(point)){
				this.queue.add(neighbours);
			}
			path.add(point); //TODO handle returns
		}
		return path;
	}
	
}
