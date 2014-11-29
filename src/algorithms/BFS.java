package algorithms;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedList;

import graphs.Graph;
import graphs.ListElement;

public class BFS<VertexType, EdgeType> {

	private Graph<VertexType, EdgeType> graph;
	
	public Graph<VertexType, EdgeType> getGraph() {
		return graph;
	}

	public void setGraph(Graph<VertexType, EdgeType> graph) {
		this.graph = graph;
	}

	public BFS(Graph<VertexType, EdgeType> graph){
		this.graph = graph;
	}
	
	public LinkedList<ListElement<VertexType, EdgeType>> findPathAsListElements(VertexType source, VertexType destination){
		LinkedList<ListElement<VertexType, EdgeType>> path = new LinkedList<ListElement<VertexType, EdgeType>>();
		LinkedList<ListElement<VertexType, EdgeType>> queue = new LinkedList<ListElement<VertexType, EdgeType>>();
		HashSet<VertexType> visited = new HashSet<VertexType>();
		path.add(new ListElement<VertexType, EdgeType>(source, null));	
		visited.add(source);
		for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(source)){
			if (!visited.contains(neighbour.inVertex)){
				queue.add(neighbour);
				visited.add(neighbour.inVertex);
			}
		}
		ListElement<VertexType, EdgeType> point = new ListElement<VertexType, EdgeType>(source, null);
		while (point.inVertex != destination && !queue.isEmpty()){
			point = queue.getFirst();
			queue.removeFirst();
			for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(point.inVertex)){
				if (!visited.contains(neighbour.inVertex)){
					queue.add(neighbour);
					visited.add(neighbour.inVertex);
				}
			}
			path.add(point);
		}
		if (path.getLast().inVertex != destination) return null;
		else{
			int i = path.size() - 2;
			while (point.inVertex != source){
				while (!this.graph.areNeighbours(path.get(i).inVertex, point.inVertex) || this.graph.getEdge(path.get(i).inVertex, point.inVertex) != point.inEdge){
					path.remove(i);
					i--;
				}
				point = path.get(i);
				i--;
			}
			return path;
		}	
	}
	
	public LinkedList<ListElement<VertexType, EdgeType>> findPathAsListElements(VertexType source, VertexType destination, EdgeType forbiddenValue){
		LinkedList<ListElement<VertexType, EdgeType>> path = new LinkedList<ListElement<VertexType, EdgeType>>();
		LinkedList<ListElement<VertexType, EdgeType>> queue = new LinkedList<ListElement<VertexType, EdgeType>>();
		HashSet<VertexType> visited = new HashSet<VertexType>();
		path.add(new ListElement<VertexType, EdgeType>(source, null));	
		visited.add(source);
		for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(source)){
			if (!visited.contains(neighbour.inVertex) && !neighbour.inEdge.equals(forbiddenValue)){
				queue.add(neighbour);
				visited.add(neighbour.inVertex);
			}
		}
		ListElement<VertexType, EdgeType> point = new ListElement<VertexType, EdgeType>(source, null);
		while (point.inVertex != destination && !queue.isEmpty()){
			point = queue.getFirst();
			queue.removeFirst();
			for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(point.inVertex)){
				if (!visited.contains(neighbour.inVertex) && !neighbour.inEdge.equals(forbiddenValue)){
					queue.add(neighbour);
					visited.add(neighbour.inVertex);
				}
			}
			path.add(point);
		}
		if (path.getLast().inVertex != destination) return null;
		else{
			int i = path.size() - 2;
			while (point.inVertex != source){
				while (!this.graph.areNeighbours(path.get(i).inVertex, point.inVertex) || this.graph.getEdge(path.get(i).inVertex, point.inVertex) != point.inEdge){
					path.remove(i);
					i--;
				}
				point = path.get(i);
				i--;
			}
			return path;
		}	
	}
	
	public LinkedList<ListElement<VertexType, EdgeType>> findPathAsListElements(VertexType source, VertexType destination, HashSet<EdgeType> forbiddenEdges){
		LinkedList<ListElement<VertexType, EdgeType>> path = new LinkedList<ListElement<VertexType, EdgeType>>();
		LinkedList<ListElement<VertexType, EdgeType>> queue = new LinkedList<ListElement<VertexType, EdgeType>>();
		HashSet<VertexType> visited = new HashSet<VertexType>();
		path.add(new ListElement<VertexType, EdgeType>(source, null));	
		visited.add(source);
		for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(source)){
			if (!visited.contains(neighbour.inVertex) && !forbiddenEdges.contains(neighbour.inEdge)){
				queue.add(neighbour);
				visited.add(neighbour.inVertex);
			}
		}
		ListElement<VertexType, EdgeType> point = new ListElement<VertexType, EdgeType>(source, null);
		while (point.inVertex != destination && !queue.isEmpty()){
			point = queue.getFirst();
			queue.removeFirst();
			for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(point.inVertex)){
				if (!visited.contains(neighbour.inVertex) && !forbiddenEdges.contains(neighbour.inEdge)){
					queue.add(neighbour);
					visited.add(neighbour.inVertex);
				}
			}
			path.add(point);
		}
		if (path.getLast().inVertex != destination) return null;
		else{
			int i = path.size() - 2;
			while (point.inVertex != source){
				while (!this.graph.areNeighbours(path.get(i).inVertex, point.inVertex) || this.graph.getEdge(path.get(i).inVertex, point.inVertex) != point.inEdge){
					path.remove(i);
					i--;
				}
				point = path.get(i);
				i--;
			}
			return path;
		}	
	}
	
	public LinkedList<ListElement<VertexType, EdgeType>> findPathAsListElements(VertexType source, VertexType destination, LinkedList<EdgeType> forbiddenEdges){
		LinkedList<ListElement<VertexType, EdgeType>> path = new LinkedList<ListElement<VertexType, EdgeType>>();
		LinkedList<ListElement<VertexType, EdgeType>> queue = new LinkedList<ListElement<VertexType, EdgeType>>();
		HashSet<VertexType> visited = new HashSet<VertexType>();
		path.add(new ListElement<VertexType, EdgeType>(source, null));	
		visited.add(source);
		for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(source)){
			boolean forbidden = false;
			for (EdgeType edge : forbiddenEdges){
				if (neighbour.inEdge == edge){
					forbidden = true;
					break;
				}
			}
			if (!visited.contains(neighbour.inVertex) && !forbidden){
				queue.add(neighbour);
				visited.add(neighbour.inVertex);
			}
		}
		ListElement<VertexType, EdgeType> point = new ListElement<VertexType, EdgeType>(source, null);
		while (point.inVertex != destination && !queue.isEmpty()){
			point = queue.getFirst();
			queue.removeFirst();
			for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(point.inVertex)){
				boolean forbidden = false;
				for (EdgeType edge : forbiddenEdges){
					if (neighbour.inEdge == edge){
						forbidden = true;
						break;
					}
				}
				if (!visited.contains(neighbour.inVertex) && !forbidden){
					queue.add(neighbour);
					visited.add(neighbour.inVertex);
				}
			}
			path.add(point);
		}
		if (path.getLast().inVertex != destination) return null;
		else{
			int i = path.size() - 2;
			while (point.inVertex != source){
				while (!this.graph.areNeighbours(path.get(i).inVertex, point.inVertex) || this.graph.getEdge(path.get(i).inVertex, point.inVertex) != point.inEdge){
					path.remove(i);
					i--;
				}
				point = path.get(i);
				i--;
			}
			return path;
		}	
	}
	
	public LinkedList<ListElement<VertexType, EdgeType>> findPathAsListElements(VertexType source, VertexType destination, IdentityHashMap<EdgeType, Boolean> forbiddenEdges){
		LinkedList<ListElement<VertexType, EdgeType>> path = new LinkedList<ListElement<VertexType, EdgeType>>();
		LinkedList<ListElement<VertexType, EdgeType>> queue = new LinkedList<ListElement<VertexType, EdgeType>>();
		HashSet<VertexType> visited = new HashSet<VertexType>();
		path.add(new ListElement<VertexType, EdgeType>(source, null));	
		visited.add(source);
		for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(source)){
			if (!visited.contains(neighbour.inVertex) && !forbiddenEdges.get(neighbour.inEdge)){
				queue.add(neighbour);
				visited.add(neighbour.inVertex);
			}
		}
		ListElement<VertexType, EdgeType> point = new ListElement<VertexType, EdgeType>(source, null);
		while (point.inVertex != destination && !queue.isEmpty()){
			point = queue.getFirst();
			queue.removeFirst();
			for (ListElement<VertexType, EdgeType> neighbour : this.graph.getNeighboursAsListElements(point.inVertex)){
				if (!visited.contains(neighbour.inVertex) && !forbiddenEdges.get(neighbour.inEdge)){
					queue.add(neighbour);
					visited.add(neighbour.inVertex);
				}
			}
			path.add(point);
		}
		if (path.getLast().inVertex != destination) return null;
		else{
			int i = path.size() - 2;
			while (point.inVertex != source){
				while (!this.graph.areNeighbours(path.get(i).inVertex, point.inVertex) || this.graph.getEdge(path.get(i).inVertex, point.inVertex) != point.inEdge){
					path.remove(i);
					i--;
				}
				point = path.get(i);
				i--;
			}
			return path;
		}	
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
			path.add(point);
		}
		if (path.getLast() != destination) return null;
		else{
			int i = path.size() - 2;
			while (point != source){
				while (!this.graph.areNeighbours(path.get(i), point)){
					path.remove(i);
					i--;
				}
				point = path.get(i);
				i--;
			}
			return path;
		}	
	}
	
}
