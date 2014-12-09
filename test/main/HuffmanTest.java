package main;

import static org.junit.Assert.*;
import graphs.Graph;
import graphs.ListGraph;
import graphs.MatrixGraph;
import model.HEdge;
import model.HVertex;

import org.junit.Test;

import algorithms.Huffman;

public class HuffmanTest {

	@Test
	public void generateTreeTest() throws InstantiationException, IllegalAccessException {
		String text = "TO BE OR NOT TO BE";
		Huffman huffman = new Huffman(text, MatrixGraph.class);
		Graph<HVertex, HEdge> tree = huffman.generateTree();
		assertEquals(tree.vertexNumber(), 13);
		assertEquals(tree.edgeNumber(), 12);
		assertEquals(tree.getRoot().probability, 18);
		System.out.println(huffman.encode());
		System.out.println(huffman.getCodeOf("T", tree));
	}

}
