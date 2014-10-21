package factories;

public interface GraphObjectFactory<VertexType, EdgeType> {

	public VertexType createVertex(String vertex);
	public EdgeType createEdge(String edge);
	
}
