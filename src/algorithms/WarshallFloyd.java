package algorithms;

import java.util.HashMap;
import java.util.Set;

import graphs.Graph;

public class WarshallFloyd<VertexType, EdgeType> {

	private VertexType[][] predecessors;
	private Number[][] path;
	private Graph<VertexType, EdgeType> graph;
	private HashMap<VertexType, Integer> verticies;
	private int vertexNumber;
	
	@SuppressWarnings("unchecked")
	public WarshallFloyd(Graph<VertexType, EdgeType> graph) {
		this.graph = graph;
		int index = 0;
		for (VertexType vertex : this.graph.getVerticies()){
			this.verticies.put(vertex, index);
			index++;
		}
		this.vertexNumber = graph.vertexNumber();
		this.predecessors = (VertexType[][]) new Object[this.vertexNumber][this.vertexNumber];
		this.path = new Number[this.vertexNumber][this.vertexNumber];;
	}
	
	public Number[][] execute(){
		Set<VertexType> vertexSet = this.verticies.keySet();
		for (VertexType inVertex : vertexSet){
			for (VertexType outVertex : vertexSet){
				int inIndex = this.verticies.get(inVertex);
				int outIndex = this.verticies.get(outVertex);
				EdgeType edge = this.graph.getEdge(inVertex, outVertex);
				if (inVertex == outVertex){
					this.path[inIndex][outIndex] = 0;
				}
				else if (edge != null){
					this.path[inIndex][outIndex] = (Number) edge;
					this.predecessors[inIndex][outIndex] = inVertex;
				}
				else {
					this.path[inIndex][outIndex] = null;
					this.predecessors[inIndex][outIndex] = null;
				}
			}
		}
		for (VertexType midVertex : vertexSet){
			for (VertexType inVertex : vertexSet){
				for (VertexType outVertex : vertexSet){
					int midIndex = this.verticies.get(midVertex);
					int inIndex = this.verticies.get(inVertex);
					int outIndex = this.verticies.get(outVertex);
					EdgeType edge = this.graph.getEdge(inVertex, outVertex);
//					if (path[inIndex][outIndex] > path[inIndex][midIndex] + path[midIndex][outIndex]){
//						TODO
//					}
				}				
			}
		}
		return this.path;
	}
	
}
