package factories;

public class GraphIntegerIntegerFactory implements GraphObjectFactory<Integer, Integer> {

	@Override
	public Integer createVertex(String vertex) {
		return new Integer(vertex);
	}

	@Override
	public Integer createEdge(String edge) {
		return new Integer(edge);
	}

}
