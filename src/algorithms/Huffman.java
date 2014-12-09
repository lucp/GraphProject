package algorithms;

import graphs.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import model.GraphObjectFactory;
import model.HEdge;
import model.HVertex;

public class Huffman {

	private LinkedList<Graph<HVertex, HEdge>> trees;
	
	public Huffman(String input, Class<Graph<HVertex, HEdge>> graphClass) throws InstantiationException, IllegalAccessException {
		this.trees = new LinkedList<Graph<HVertex, HEdge>>();
		HashMap<String, HVertex> characters = new HashMap<String, HVertex>();
		for (int i = 0; i < input.length(); i++) {
			String character = String.valueOf(input.charAt(i));
			if (characters.containsKey(character)) {
				characters.get(character).probability++;
			}
			else {
				HVertex vertex = new HVertex(1, character);
				Graph<HVertex, HEdge> root = graphClass.newInstance();
				root.addVertex(vertex);
				this.trees.add(root);
			}
		}
	}
	
	public Graph<HVertex, HEdge> generateTree() {
		while (this.trees.size() > 1) {
			Graph<HVertex, HEdge> minRoot1 = null;
			int minRootProbability1 = Integer.MAX_VALUE;
			Graph<HVertex, HEdge> minRoot2 = null;
			int minRootProbability2 = Integer.MAX_VALUE;
			for (Graph<HVertex, HEdge> tree : this.trees) {
				int treeProbability = tree.getRoot().probability;
				if (treeProbability < minRootProbability2) {
					if (treeProbability < minRootProbability1) {
						minRoot2 = minRoot1;
						minRootProbability2 = minRootProbability1;
						minRoot1 = tree;
						minRootProbability1 = treeProbability;
					}
					else {
						minRoot2 = tree;
						minRootProbability2 = treeProbability;
					}
				}
			}
			HVertex hvertex = new HVertex(minRootProbability1 + minRootProbability2, null);
			HEdge hedge0 = new HEdge((byte)0);
			HEdge hedge1 = new HEdge((byte)1);
			minRoot1.addVertex(hvertex);
//			minRoot1.addEdge(source, destination, edge);
		}
		return null; //TODO
	}
	
}
