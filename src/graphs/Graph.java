package graphs;

import java.util.LinkedList;

import factories.GraphObjectFactory;

public interface Graph<VertexType, EdgeType> {

	public void addVertex(VertexType vertex);
	public void deleteVertex(VertexType vertex);
	public LinkedList<VertexType> getVerticies();
	public Entry<VertexType, EdgeType> getVertexPair(EdgeType edge);
	public VertexType getVertexByValue(VertexType value);
	public void addEdge(VertexType source, VertexType destination, EdgeType edge);
	public void deleteEdge(VertexType source, VertexType destination);
	public EdgeType getEdge(VertexType source, VertexType destination);
	public LinkedList<EdgeType> getEdges();
	public LinkedList<VertexType> getNeighbours(VertexType vertex);
	public LinkedList<ListElement<VertexType, EdgeType>> getNeighboursAsListElements(VertexType vertex);
	public LinkedList<EdgeType> getIncidentEdges(VertexType vertex);
	public int vertexNumber();
	public int edgeNumber();
	public boolean areNeighbours(VertexType firstVertex, VertexType secondVertex);
	public LinkedList<Entry<VertexType, EdgeType>> getAllEntries();
	public Graph<VertexType, EdgeType> getCopy(GraphObjectFactory<VertexType, EdgeType> factory);
	
}
