package algorithms;

import graphs.Graph;

import java.util.HashMap;
import java.util.Set;

public class FordBellman<VertexType, EdgeType extends Number & Comparable<EdgeType>> {

	private Number[] weight;
	private VertexType[] predecessor;
	private Graph<VertexType, EdgeType> graph;
	private HashMap<VertexType, Integer> verticies;
	private int vertexNumber;
	
	@SuppressWarnings("unchecked")
	public FordBellman(Graph<VertexType, EdgeType> graph) {
		super();
		this.graph = graph;
		int index = 0;
		this.verticies = new HashMap<VertexType, Integer>();
		for (VertexType vertex : this.graph.getVerticies()){
			this.verticies.put(vertex, index);
			index++;
		}
		this.vertexNumber = graph.vertexNumber();
		this.predecessor = (VertexType[]) new Object[this.vertexNumber];
	}

	public Number[] getWeight() {
		return weight;
	}

	public VertexType[] getPredecessor() {
		return predecessor;
	}

	public void execute(VertexType source){
		Set<VertexType> vertexSet = this.verticies.keySet();
		for (VertexType vertex : vertexSet){
			if (vertex == source) this.weight[this.verticies.get(vertex)] = 0d;
			else this.weight[this.verticies.get(vertex)] = Double.MAX_VALUE;
			this.predecessor[this.verticies.get(vertex)] = null;
		}
		for (int i = 1; i < this.vertexNumber - 1; i++){
			for (VertexType inVertex : vertexSet){
				for (VertexType outVertex : vertexSet){
					EdgeType edge = this.graph.getEdge(inVertex, outVertex);
					if (edge != null){
						int inIndex = this.verticies.get(inVertex);
						int outIndex = this.verticies.get(outVertex);
						if (this.weight[inIndex].doubleValue() + edge.doubleValue() < this.weight[outIndex].doubleValue()){
							this.weight[outIndex] = this.weight[inIndex].doubleValue() + edge.doubleValue();
							this.predecessor[outIndex] = outVertex;
						}
					}
				}
			}
		}
	}
	
}
