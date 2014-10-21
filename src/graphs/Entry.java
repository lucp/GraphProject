package graphs;

public class Entry<VertexType, EdgeType>{
		
	public VertexType inVertex;
	public VertexType outVertex;
	public EdgeType midEdge;
	
	public Entry(VertexType inVertex, VertexType outVertex,EdgeType midEdge) {
		super();
		this.inVertex = inVertex;
		this.outVertex = outVertex;
		this.midEdge = midEdge;
	}
		
}
	
