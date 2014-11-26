package graphs;

public class ListElement<VertexType, EdgeType>{
	
	public VertexType inVertex;
	public EdgeType inEdge;
	
	public ListElement(VertexType vertex, EdgeType edge){
		this.inVertex = vertex;
		this.inEdge = edge;
	}
	
	@Override
	public String toString() {
		return new String("[" + this.inVertex + "," +this.inEdge +"]");
	}
	
}