package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
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
		IdentityHashMap<EdgeType, Number> flow = new IdentityHashMap<EdgeType, Number>();
		IdentityHashMap<EdgeType, Number> capacity = new IdentityHashMap<EdgeType, Number>();
		IdentityHashMap<EdgeType, Boolean> forbiddenEdges = new IdentityHashMap<EdgeType, Boolean>();
		LinkedList<EdgeType> inEdges = new LinkedList<EdgeType>();
		for (EdgeType edge : this.graph.getEdges()){
			flow.put(edge, 0d);
			capacity.put(edge, edge.doubleValue());
			if (edge.doubleValue() <= 0) forbiddenEdges.put(edge, true);
			else forbiddenEdges.put(edge, false);
		}
		LinkedList<ListElement<VertexType, EdgeType>> path = bfs.findPathAsListElements(source, destination, forbiddenEdges);
//		System.out.println(path);
		int i=0;
		while (path != null){
			inEdges.add(path.getLast().inEdge);
			Number minEdge = capacity.get(path.getLast().inEdge);
			for (ListElement<VertexType, EdgeType> listElement : path){
				if (listElement.inEdge != null){
					if (minEdge.doubleValue() > capacity.get(listElement.inEdge).doubleValue()){
						minEdge = capacity.get(listElement.inEdge).doubleValue();
					}
				}
			}
			for (ListElement<VertexType, EdgeType> listElement : path){
				if (listElement.inEdge != null){
//					System.out.print("[ F: " + minEdge + ";" + flow.get(listElement.inEdge) + ";" + capacity.get(listElement.inEdge) + ";" + forbiddenEdges.size());
					flow.put(listElement.inEdge, flow.get(listElement.inEdge).doubleValue() + minEdge.doubleValue());
					capacity.put(listElement.inEdge, capacity.get(listElement.inEdge).doubleValue() - minEdge.doubleValue());
					if (capacity.get(listElement.inEdge).doubleValue() == 0d) forbiddenEdges.put(listElement.inEdge, true);
//					System.out.print(" T: " + minEdge + ";" + flow.get(listElement.inEdge) + ";" + capacity.get(listElement.inEdge) + ";" + forbiddenEdges.size() + "]");
				}
			}
			path = bfs.findPathAsListElements(source, destination, forbiddenEdges);
//			System.out.println(i++);
		}
		Number finalFlow = 0;
		for (EdgeType inEdge : inEdges){
			finalFlow = finalFlow.doubleValue() + flow.get(inEdge).doubleValue();
		}
		return finalFlow;
	}
	
}
