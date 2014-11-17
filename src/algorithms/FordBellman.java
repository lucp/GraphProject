package algorithms;

import graphs.Entry;
import graphs.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class FordBellman<VertexType, EdgeType extends Number & Comparable<EdgeType>> {

	private Number[] weight;
	private VertexType[] predecessor;
	private Graph<VertexType, EdgeType> graph;
	private HashMap<VertexType, Integer> verticies;
	private LinkedList<EdgeType> edges;
	private LinkedList<Entry<VertexType, EdgeType>> entries;
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
		this.edges = this.graph.getEdges();
		this.entries = this.graph.getAllEntries();
		this.vertexNumber = graph.vertexNumber();
		this.weight = new Number[this.vertexNumber];
		this.predecessor = (VertexType[]) new Object[this.vertexNumber];
	}

	public Number getWeight(VertexType vertex) {
		return this.weight[this.verticies.get(vertex)];
	}

	public VertexType getPredecessor(VertexType vertex) {
		return this.predecessor[this.verticies.get(vertex)];
	}

	public void execute(VertexType source){
		Set<VertexType> vertexSet = this.verticies.keySet();
		for (VertexType vertex : vertexSet){
			if (vertex == source) this.weight[this.verticies.get(vertex)] = 0d;
			else this.weight[this.verticies.get(vertex)] = Double.MAX_VALUE;
			this.predecessor[this.verticies.get(vertex)] = null;
		}
		for (int i = 1; i < this.vertexNumber - 1; i++){
			for (Entry<VertexType, EdgeType> entry : this.entries){
				EdgeType edge = entry.midEdge;
				VertexType inVertex = entry.inVertex;
				VertexType outVertex = entry.outVertex;
				if (edge != null){
					int inIndex = this.verticies.get(inVertex);
					int outIndex = this.verticies.get(outVertex);
					if (this.weight[inIndex].doubleValue() + edge.doubleValue() < this.weight[outIndex].doubleValue()){
						this.weight[outIndex] = this.weight[inIndex].doubleValue() + edge.doubleValue();
						this.predecessor[outIndex] = inVertex;
					}
				}
			}
		}
	}
	
	public void execute2(VertexType source){
		Set<VertexType> vertexSet = this.verticies.keySet();
		for (VertexType vertex : vertexSet){
			if (vertex == source) this.weight[this.verticies.get(vertex)] = 0d;
			else this.weight[this.verticies.get(vertex)] = Double.MAX_VALUE;
			this.predecessor[this.verticies.get(vertex)] = null;
		}
		for (int i = 1; i < this.vertexNumber - 1; i++){
			for (EdgeType edge : this.edges){
				Entry<VertexType, EdgeType> entry = this.graph.getVertexPair(edge);
				VertexType inVertex = entry.inVertex;
				VertexType outVertex = entry.outVertex;
				if (edge != null){
					int inIndex = this.verticies.get(inVertex);
					int outIndex = this.verticies.get(outVertex);
					if (this.weight[inIndex].doubleValue() + edge.doubleValue() < this.weight[outIndex].doubleValue()){
						this.weight[outIndex] = this.weight[inIndex].doubleValue() + edge.doubleValue();
						this.predecessor[outIndex] = inVertex;
					}
				}
			}
		}
	}
	
	public void execute3(VertexType source){
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
							this.predecessor[outIndex] = inVertex;
						}
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		String fordBellmanString = new String("predecessor: [");
		for (int i = 0; i < this.vertexNumber; i++){
			fordBellmanString += this.predecessor[i] + "\t";
		}
		fordBellmanString += "]\nweight: [";
		for (int i = 0; i < this.vertexNumber; i++){
			if (this.weight[i].doubleValue() == Double.MAX_VALUE) fordBellmanString += "Inf\t";
			else fordBellmanString += this.weight[i] + "\t";
		}
		fordBellmanString += "]\n";
		return fordBellmanString;
	}
	
}
