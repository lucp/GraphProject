package algorithms;

import graphs.Graph;
import graphs.ListGraph;
import graphs.MatrixGraph;

import java.util.HashMap;
import java.util.LinkedList;

import model.HEdge;
import model.HVertex;

public class Huffman {

	private LinkedList<Graph<HVertex, HEdge>> trees;
	
	public Huffman(String input, Class<?> graphClass) throws InstantiationException, IllegalAccessException {
		this.trees = new LinkedList<Graph<HVertex, HEdge>>();
		HashMap<String, HVertex> characters = new HashMap<String, HVertex>();
		for (int i = 0; i < input.length(); i++) {
			String character = String.valueOf(input.charAt(i));
			if (characters.containsKey(character)) {
				characters.get(character).probability++;
			}
			else {
				HVertex vertex = new HVertex(1, character);
				Graph<HVertex, HEdge> root;
				if (graphClass == MatrixGraph.class) {
					root = new MatrixGraph<HVertex, HEdge>(1);
				}
				else {
					root = new ListGraph<HVertex, HEdge>(1);
				}
				root.addVertex(vertex);
				this.trees.add(root);
				characters.put(character, vertex);
			}
		}
	}
	
	public Graph<HVertex, HEdge> generateTree() {
		while (this.trees.size() > 1) {
			Graph<HVertex, HEdge> minRoot1 = null;
			HVertex minRootVertex1 = null;
			int minRootProbability1 = Integer.MAX_VALUE;
			Graph<HVertex, HEdge> minRoot2 = null;
			HVertex minRootVertex2 = null;
			int minRootProbability2 = Integer.MAX_VALUE;
			for (Graph<HVertex, HEdge> tree : this.trees) {
				HVertex treeRoot = tree.getRoot();
				int treeProbability = treeRoot.probability;
				if (treeProbability < minRootProbability2) {
					if (treeProbability < minRootProbability1) {
						minRoot2 = minRoot1;
						minRootVertex2 = minRootVertex1;
						minRootProbability2 = minRootProbability1;
						minRoot1 = tree;
						minRootVertex1 = treeRoot;
						minRootProbability1 = treeProbability;
					}
					else {
						minRoot2 = tree;
						minRootVertex2 = treeRoot;
						minRootProbability2 = treeProbability;
					}
				}
			}
			minRoot1.mergeWith(minRoot2);
			HVertex hvertex = new HVertex(minRootProbability1 + minRootProbability2, null);
			HEdge hedge0 = new HEdge((byte)0);
			HEdge hedge1 = new HEdge((byte)1);
			minRoot1.addVertex(hvertex);
			minRoot1.addEdge(hvertex, minRootVertex1, hedge0);
			minRoot1.addEdge(hvertex, minRootVertex2, hedge1);
			this.trees.remove(minRoot2);
		}
		return this.trees.getFirst();
	}
	
}
