package algorithms;

import java.util.LinkedList;

import graphs.Graph;

public class WarshallFloyd<VertexType, EdgeType> {

	private VertexType[][] predecessors;
	private Number[][] path;
	private Graph<VertexType, EdgeType> graph;
	private LinkedList<VertexType> verticies;
	private int vertexNumber;
	
	@SuppressWarnings("unchecked")
	public WarshallFloyd(Graph<VertexType, EdgeType> graph) {
		this.graph = graph;
		this.verticies = this.graph.getVerticies();
		this.vertexNumber = graph.vertexNumber();
		this.predecessors = (VertexType[][]) new Object[this.vertexNumber][this.vertexNumber];
		this.path = new Number[this.vertexNumber][this.vertexNumber];;
	}
	
	public Number[][] execute(){
		for (int i = 0; i < this.vertexNumber; i++){
			for (int j = 0; j < this.vertexNumber; j++){
				if (i == j) this.path[i][j] = 0;
				else this.path[i][j] = null;
				this.predecessors[i][j] = null;
			}
		}
		return path;
	}
	
}
