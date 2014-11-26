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

	@Override
	public Integer createVertex(Integer vertex) {
		if (vertex == null) return null;
		else return new Integer(vertex);
	}

	@Override
	public Integer createEdge(Integer edge) {
		if (edge == null) return null;
		else return new Integer(edge);
	}

}
