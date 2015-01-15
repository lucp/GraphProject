package main;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		String textBinary = huffman.toBinaryString(text);
		String codeBinary = huffman.encode();
		double ratio = huffman.getRatio(textBinary, codeBinary);
		System.out.println(text);
		System.out.println("Original:\t" + textBinary);
		System.out.println("Encoded:\t" + codeBinary);
		System.out.println("Ratio:\t" + ratio);
	}

	@Test
	public void textFileCompressTest() throws InstantiationException, IllegalAccessException {
		String text = null;
		BufferedReader bufferedReader = null;
		try {
			String line;
			bufferedReader = new BufferedReader(new FileReader("res/seneca.txt"));
			while ((line = bufferedReader.readLine()) != null) {
				text += line;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (bufferedReader != null) bufferedReader.close();
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		Huffman huffman = new Huffman(text, ListGraph.class);
		String textBinary = huffman.toBinaryString(text);
		String codeBinary = huffman.encode();
		double ratio = huffman.getRatio(textBinary, codeBinary);
		System.out.println("\nText file");
		System.out.println("Ratio:\t" + ratio);
	}
	
}
