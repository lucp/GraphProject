package graphs;

import java.util.LinkedList;

public interface Graph<VertexType, EdgeType> {

	public void addVertex(VertexType vertex);
	public void deleteVertex(VertexType vertex);
	public void addEdge(VertexType source, VertexType destination, EdgeType edge);
	public void deleteEdge(VertexType source, VertexType destination);
	public LinkedList<VertexType> getNeighbours(VertexType vertex);
	public LinkedList<EdgeType> getIncidentEdges(VertexType vertex);
	public int vertexNumber();
	public int edgeNumber();
	public boolean areNeighbours(VertexType firstVertex, VertexType secondVertex);
	
}
