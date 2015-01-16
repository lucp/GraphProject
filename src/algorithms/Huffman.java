package algorithms;

import graphs.Graph;
import graphs.ListElement;
import graphs.ListGraph;
import graphs.MatrixGraph;

import java.util.HashMap;
import java.util.LinkedList;

import model.HEdge;
import model.HVertex;

public class Huffman {

	private String expression;
	private LinkedList<Graph<HVertex, HEdge>> trees;
	
	public Huffman(String input, Class<?> graphClass) throws InstantiationException, IllegalAccessException {
		this.expression = input;
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
	
	public String encode() {
		String code = new String();
		Graph<HVertex, HEdge> tree = this.generateTree();
		HVertex root = tree.getRoot();
		BFS<HVertex, HEdge> bfs = new BFS<HVertex, HEdge>(tree);
		for (int i = 0; i < this.expression.length(); i++) {
			HVertex same = new HVertex(0, String.valueOf(this.expression.charAt(i)));
			HVertex target = tree.getVertexByValue(same);
			LinkedList<ListElement<HVertex, HEdge>> path = bfs.findPathAsListElements(root, target);
			for (ListElement<HVertex, HEdge> element : path) {
				if (element.inEdge != null) {
					code += element.inEdge.code;
				}
			}
			code += " ";
		}
		return code;
	}
	
	public String encode(Graph<HVertex, HEdge> tree) {
		String code = new String();
		HVertex root = tree.getRoot();
		BFS<HVertex, HEdge> bfs = new BFS<HVertex, HEdge>(tree);
		for (int i = 0; i < this.expression.length(); i++) {
			HVertex same = new HVertex(0, String.valueOf(this.expression.charAt(i)));
			HVertex target = tree.getVertexByValue(same);
			LinkedList<ListElement<HVertex, HEdge>> path = bfs.findPathAsListElements(root, target);
			for (ListElement<HVertex, HEdge> element : path) {
				if (element.inEdge != null) {
					code += element.inEdge.code;
				}
			}
			code += " ";
		}
		return code;
	}
	
	public String getCodeOf(String character, Graph<HVertex, HEdge> tree) {
		String code = new String();
		HVertex root = tree.getRoot();
		BFS<HVertex, HEdge> bfs = new BFS<HVertex, HEdge>(tree);
		HVertex same = new HVertex(0, character);
		HVertex target = tree.getVertexByValue(same);
		LinkedList<ListElement<HVertex, HEdge>> path = bfs.findPathAsListElements(root, target);
		for (ListElement<HVertex, HEdge> element : path) {
			if (element.inEdge != null) {
				code += element.inEdge.code;
			}
		}
		return code;
	}
	
	public String decode(String code) {
		code = code.trim();
		int i = 0;
		String text = new String();
		while (i < code.length()) {
			HVertex vertex = this.trees.getFirst().getRoot();
			LinkedList<ListElement<HVertex, HEdge>> element = this.trees.getFirst().getNeighboursAsListElements(vertex);
			while (!element.isEmpty()) {
				if (String.valueOf(element.get(0).inEdge.code).equals(code.charAt(i))) {
					vertex = element.get(0).inVertex;
					text += String.valueOf(element.get(0).inEdge.code);
				}
				else {
					vertex = element.get(1).inVertex;
					text += String.valueOf(element.get(1).inEdge.code);
				}
				element = this.trees.getFirst().getNeighboursAsListElements(vertex);
			}
		}
		return text;
		
	}
	
	public String toBinaryString(String input) {
		String binary = new String();
		for (int i = 0; i < input.length(); i++) {
			String intParse = Integer.toBinaryString(input.charAt(i));
			while (intParse.length() < 8) {
				intParse = "0" + intParse;
			}
			binary += intParse + " ";
		}
		return binary;
	}
	
	public double getRatio(String code1, String code2) {
		return (double)code1.trim().length() / (double)code2.trim().length();
	}
	
}
