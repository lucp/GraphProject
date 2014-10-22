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
	public void addVertex(VertexType vertexNumber){
		Integer freeIndex = findFreeMatrixEntry();
		if (freeIndex == null){
			
		}
		else{
			
		}
	}

	@Override
	public void deleteVertex(VertexType number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(VertexType source, VertexType destination, EdgeType edge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEdge(VertexType source, VertexType destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VertexType[] getNeighbours(VertexType vertexNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EdgeType[] getIncidentEdges(VertexType vartexNumber) {
		// TODO Auto-generated method stub
		return null;
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
