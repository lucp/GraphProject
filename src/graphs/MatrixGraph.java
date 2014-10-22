package graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import exceptions.VertexAlreadyExistsException;
import reader.GraphFileReader;

public class MatrixGraph<VertexType, EdgeType> implements Graph<VertexType, EdgeType> {

	private int matrixSize;
	
	private EdgeType[][] edges;
	
	private HashMap<VertexType, Integer> verticies;
	
	public MatrixGraph(int matrixSize){
		this.matrixSize = matrixSize;
		this.edges = (EdgeType[][]) new Object[matrixSize][matrixSize];
		this.verticies = new HashMap<VertexType, Integer>();
	}
	
	public MatrixGraph(LinkedList<Entry<VertexType, EdgeType>> fileEntries){
		Integer index = 0;
		this.verticies = new HashMap<VertexType, Integer>();
		for (Entry<VertexType, EdgeType> entry : fileEntries){
			if (!this.verticies.containsValue(entry.inVertex)){
				this.verticies.put(entry.inVertex,index);
				index++;
			}
			if (!this.verticies.containsValue(entry.outVertex)){
				this.verticies.put(entry.outVertex,index);
				index++;
			}
		}
		this.matrixSize = this.verticies.size();
		this.edges = (EdgeType[][]) new Object[this.matrixSize][this.matrixSize];
		for (Entry<VertexType, EdgeType> entry : fileEntries){
			this.edges[this.verticies.get(entry.inVertex)][this.verticies.get(entry.outVertex)] = entry.midEdge;
		}
	}
	
	public MatrixGraph(LinkedList<Entry<VertexType, EdgeType>> fileEntries, int matrixSizeBuffer){
		Integer  index = 0;
		this.verticies = new HashMap<VertexType, Integer>();
		for (Entry<VertexType, EdgeType> entry : fileEntries){
			if (!this.verticies.containsValue(entry.inVertex)){
				this.verticies.put(entry.inVertex,index);
				index++;
			}
			if (!this.verticies.containsValue(entry.outVertex)){
				this.verticies.put(entry.outVertex,index);
				index++;
			}
		}
		this.matrixSize = this.verticies.size() + matrixSizeBuffer;
		this.edges = (EdgeType[][]) new Object[this.matrixSize][this.matrixSize];
		for (Entry<VertexType, EdgeType> entry : fileEntries){
			this.edges[this.verticies.get(entry.inVertex)][this.verticies.get(entry.outVertex)] = entry.midEdge;
		}
	}

	public int getMatrixSize() {
		return this.matrixSize;
	}
	
	public Integer findFreeMatrixEntry(){
		Integer freeIndex = 0;
		while (freeIndex < this.matrixSize && this.verticies.containsValue(freeIndex)){
			freeIndex++;
		}
		if (freeIndex > this.matrixSize) return null;
		else return freeIndex;
	}

	@Override
	public void addVertex(VertexType vertex){
		Integer freeIndex = findFreeMatrixEntry();
		if (freeIndex == null){
			EdgeType[][] newEdges = (EdgeType[][]) new Object[this.matrixSize+1][this.matrixSize+1];
			for (int i = 0; i < this.matrixSize; i++){
				for (int j = 0; j < this.matrixSize; j++){
					newEdges[i][j] = this.edges[i][j];
				}
			}
			this.matrixSize++;
			for (int i = 0; i < this.matrixSize; i++){
				newEdges[i][this.matrixSize-1] = null;
				newEdges[this.matrixSize-1][i] = null;
			}
			this.verticies.put(vertex, freeIndex);
		}
		else{
			this.verticies.put(vertex, freeIndex);
		}
	}

	@Override
	public void deleteVertex(VertexType vertex) {
		Integer index = this.verticies.get(vertex);
		this.verticies.remove(vertex);
		
		for (int i = 0; i < this.matrixSize; i++){
			this.edges[i][index] = null;
			this.edges[index][i] = null;
		}
	}

	@Override
	public void addEdge(VertexType source, VertexType destination, EdgeType edge) {
		Integer sourceIndex = this.verticies.get(source);
		Integer destinationIndex = this.verticies.get(destination);
		this.edges[sourceIndex][destinationIndex] = edge;		
	}

	@Override
	public void deleteEdge(VertexType source, VertexType destination) {
		Integer sourceIndex = this.verticies.get(source);
		Integer destinationIndex = this.verticies.get(destination);
		this.edges[sourceIndex][destinationIndex] = null;
	}

	@Override
	public VertexType[] getNeighbours(VertexType vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EdgeType[] getIncidentEdges(VertexType vertex) {
		LinkedList<EdgeType> incidentEdges = new LinkedList<EdgeType>();
		Integer index = this.verticies.get(vertex);
		for (int i = 0; i < this.matrixSize; i++){
			EdgeType edge1 = this.edges[i][index];
			if(edge1 != null) incidentEdges.add(edge1);
			EdgeType edge2 = this.edges[index][i];
			if(edge2 != null) incidentEdges.add(edge2);
		}
		return (EdgeType[]) incidentEdges.toArray();
	}

	@Override
	public int vertexNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean areNeighbours(VertexType firstVertex, VertexType secondVertex) {
		// TODO Auto-generated method stub
		return false;
	}

}
