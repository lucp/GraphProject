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
	
	public Number execute(VertexType source, VertexType destination){
		for (EdgeType edge : this.graph.getEdges()){
			this.flow.put(edge, 0d);
			this.capacity.put(edge, edge); //TODO check if not null with that
		}
		HashSet<VertexType> inVerticies = new HashSet<VertexType>();
		LinkedList<ListElement<VertexType, EdgeType>> path = bfs.findPathAsListElements(source, destination, this.forbiddenEdges);
		while (path != null){
			inVerticies.add(path.get(path.size() - 2).inVertex);
			Number minEdge = this.capacity.get(path.getFirst().inEdge);
			for (ListElement<VertexType, EdgeType> listElement : path){ //TODO check if null
				if (minEdge.doubleValue() < this.capacity.get(listElement.inEdge).doubleValue()){
					minEdge = this.capacity.get(listElement.inEdge).doubleValue();
				}
			}
			for (ListElement<VertexType, EdgeType> listElement : path){
				this.flow.replace(listElement.inEdge, this.flow.get(listElement.inEdge).doubleValue() + minEdge.doubleValue());
				this.capacity.replace(listElement.inEdge, this.flow.get(listElement.inEdge).doubleValue() - minEdge.doubleValue());
				if (this.capacity.get(listElement.inEdge).doubleValue() == 0d) this.forbiddenEdges.add(listElement.inEdge);
			}
		}
		Number finalFlow = 0;
		for (VertexType inVertex : inVerticies){
			finalFlow = finalFlow.doubleValue() + this.graph.getEdge(inVertex, destination).doubleValue();
		}
		return finalFlow;
	}
	
}
