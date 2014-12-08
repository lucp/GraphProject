package model;

public interface GraphObjectFactory<VertexType, EdgeType> {

	public VertexType createVertex(String vertex);
	public VertexType createVertex(VertexType vertex);
	public EdgeType createEdge(String edge);
	public EdgeType createEdge(EdgeType edge);
	
}
