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
		Huffman huffman = new Huffman("TO BE OR NOT TO BE", MatrixGraph.class);
		Graph<HVertex, HEdge> graph = huffman.generateTree();
		assertEquals(graph.vertexNumber(), 13);
		assertEquals(graph.edgeNumber(), 12);	
	}

}
