package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import factories.GraphObjectFactory;
import graphs.Graph;
import graphs.ListElement;

public class FordFulkerson<VertexType, EdgeType extends Number & Comparable<EdgeType>> {

	private Graph<VertexType, EdgeType> graph;
	private HashMap<EdgeType, Number> flow;
	private HashMap<EdgeType, Number> capacity;
	private BFS<VertexType, EdgeType> bfs;
	private HashSet<EdgeType> forbiddenEdges;
	
	public FordFulkerson(Graph<VertexType, EdgeType> graph){
		this.graph = graph;
		this.capacity = new HashMap<EdgeType, Number>();
		this.flow = new HashMap<EdgeType, Number>();
		this.bfs = new BFS<VertexType, EdgeType>(this.graph);
		this.forbiddenEdges = new HashSet<EdgeType>();
	}
	
	public EdgeType execute(VertexType source, VertexType destination){
		
		for (EdgeType edge : this.graph.getEdges()){
			this.flow.put(edge, 0d);
			this.capacity.put(edge, edge);
		}
		LinkedList<ListElement<VertexType, EdgeType>> path = bfs.findPathAsListElements(source, destination, this.forbiddenEdges);
		while (path != null){
			EdgeType minEdge = path.getFirst().inEdge;
			for (ListElement<VertexType, EdgeType> listElement : path){
				if (minEdge.compareTo(listElement.inEdge) < 0){
					minEdge = listElement.inEdge;
				}
			}
			for (ListElement<VertexType, EdgeType> listElement : path){
				this.flow.replace(listElement.inEdge, this.flow.get(listElement.inEdge).doubleValue() + minEdge.doubleValue());
				this.capacity.replace(listElement.inEdge, this.flow.get(listElement.inEdge).doubleValue() - minEdge.doubleValue());
				if (this.capacity.get(listElement.inEdge).doubleValue() == 0d) this.forbiddenEdges.add(listElement.inEdge);
			}
		}
		return null;
	}
	
}
