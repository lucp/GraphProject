package graphs;

public interface Graph<VertexType, EdgeType> {

	public void addVertex(VertexType vertexNumber);
	public void deleteVertex(VertexType number);
	public void addEdge(VertexType source, VertexType destination, EdgeType edge);
	public void deleteEdge(VertexType source, VertexType destination);
	public VertexType[] getNeighbours(VertexType vertexNumber);
	public EdgeType[] getIncidentEdges(VertexType vartexNumber);
	public int vertexNumber();
	public int edgeNumber();
	public boolean areNeighbours(VertexType firstVertex, VertexType secondVertex);
	
}
