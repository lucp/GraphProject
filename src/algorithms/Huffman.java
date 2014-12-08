package algorithms;

import graphs.Graph;

import java.util.LinkedList;

import model.GraphObjectFactory;

public class Huffman<VertexType, EdgeType> { //TODO huffman element

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
			Graph<VertexType, EdgeType> minRootValue = this.trees.getFirst();
			for (Graph<VertexType, EdgeType> tree : this.trees) {
//				if (tree.getRoot())
			}
		}
		return null; //TODO
	}
	
}
