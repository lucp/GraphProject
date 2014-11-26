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
	
	@Override
	public Integer createVertex(Integer vertex) {
		if (vertex == null) return null;
		else return new Integer(vertex);
	}

	@Override
	public Double createEdge(Double edge) {
		if (edge == null) return null;
		else return new Double(edge);
	}

}
