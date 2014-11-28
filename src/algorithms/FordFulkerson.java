package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import graphs.Graph;
import graphs.ListElement;

public class FordFulkerson<VertexType, EdgeType extends Number & Comparable<EdgeType>> {

	private Graph<VertexType, EdgeType> graph;
	private BFS<VertexType, EdgeType> bfs;
	
	
	public FordFulkerson(Graph<VertexType, EdgeType> graph){
		this.graph = graph;
		this.bfs = new BFS<VertexType, EdgeType>(this.graph);
	}
	
	public Number execute(VertexType source, VertexType destination){
		HashMap<EdgeType, Number> flow = new HashMap<EdgeType, Number>();
		HashMap<EdgeType, Number> capacity = new HashMap<EdgeType, Number>();
		HashSet<EdgeType> forbiddenEdges = new HashSet<EdgeType>();
		HashSet<EdgeType> inEdges = new HashSet<EdgeType>();
		for (EdgeType edge : this.graph.getEdges()){
			flow.put(edge, 0d);
			capacity.put(edge, edge);
			if (edge.doubleValue() <= 0) forbiddenEdges.add(edge);
		}
		LinkedList<ListElement<VertexType, EdgeType>> path = bfs.findPathAsListElements(source, destination, forbiddenEdges);
		System.out.println(path);
		while (path != null){
			inEdges.add(path.getLast().inEdge);
			Number minEdge = capacity.get(path.getLast().inEdge);
			for (ListElement<VertexType, EdgeType> listElement : path){ //TODO check if null
				if (listElement.inEdge != null){
					if (minEdge.doubleValue() > capacity.get(listElement.inEdge).doubleValue()){
						minEdge = capacity.get(listElement.inEdge).doubleValue();
					}
				}
			}
			for (ListElement<VertexType, EdgeType> listElement : path){
				if (listElement.inEdge != null){
					System.out.print("[ F: " + minEdge + ";" + flow.get(listElement.inEdge) + ";" + capacity.get(listElement.inEdge) + ";" + forbiddenEdges.size());
					flow.put(listElement.inEdge, flow.get(listElement.inEdge).doubleValue() + minEdge.doubleValue());
					capacity.put(listElement.inEdge, capacity.get(listElement.inEdge).doubleValue() - minEdge.doubleValue());
					if (capacity.get(listElement.inEdge).doubleValue() == 0d) forbiddenEdges.add(listElement.inEdge);
					System.out.print(" T: " + minEdge + ";" + flow.get(listElement.inEdge) + ";" + capacity.get(listElement.inEdge) + ";" + forbiddenEdges.size() + "]");
				}
			}
			System.out.println();
			path = bfs.findPathAsListElements(source, destination, forbiddenEdges);
			System.out.println(path);
		}
		Number finalFlow = 0;
		for (EdgeType inEdge : inEdges){
			finalFlow = finalFlow.doubleValue() + flow.get(inEdge).doubleValue();
		}
		return finalFlow;
	}
	
}
