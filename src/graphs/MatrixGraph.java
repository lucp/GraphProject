package graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class MatrixGraph<VertexType, EdgeType> implements Graph<VertexType, EdgeType> {

	private int vertexNumber;
	
	private EdgeType[][] edges;
	
	private HashMap<Integer, VertexType> verticies;
	
	public MatrixGraph(int vertexNumber){
		this.vertexNumber = vertexNumber;
		this.edges = (EdgeType[][]) new Object[vertexNumber][vertexNumber];
		this.verticies = new boolean[vertexNumber];
		for (int i=0; i<vertexNumber ; i++){
			this.verticies[i] = true;
		}
	}
	
	public MatrixGraph(String filePath){
		LinkedList<String> lines;
		BufferedReader reader = null;
		try {
			String line;
			reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				
			}
			String[] num = line.split(",");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void addVertex(VertexType vertexNumber) {
		// TODO Auto-generated method stub
		
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
