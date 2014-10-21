package factories;

public class GraphIntegerDoubleFactory implements GraphObjectFactory<Integer, Double> {

	@Override
	public Integer createVertex(String vertex) {
		return new Integer(vertex);
	}

	@Override
	public Double createEdge(String edge) {
		return new Double(edge);
	}

}
