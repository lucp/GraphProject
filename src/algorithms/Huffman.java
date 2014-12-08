package algorithms;

import factories.GraphObjectFactory;
import graphs.Graph;

import java.util.LinkedList;

public class Huffman<VertexType, EdgeType> {

	private LinkedList<Graph<VertexType, EdgeType>> trees;
	
	public Huffman(String input, GraphObjectFactory<VertexType, EdgeType> factory, Class<Graph<VertexType, EdgeType>> graphClass) throws InstantiationException, IllegalAccessException {
		this.trees = new LinkedList<Graph<VertexType, EdgeType>>();
		for (int i = 0; i < input.length(); i++) {
			Graph<VertexType, EdgeType> root = graphClass.newInstance();
			root.addVertex(factory.createVertex(String.valueOf(input.charAt(i))));
			this.trees.add(root);
		}
	}
	
	public Graph<VertexType, EdgeType> generateTree() {
		while (this.trees.size() != 1) {
			//for ()
		}
		return null; //TODO
	}
	
}
