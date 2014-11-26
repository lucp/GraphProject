package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;

import algorithms.BFS;
import reader.GraphFileReader;
import factories.GraphIntegerIntegerFactory;
import graphs.Entry;
import graphs.ListElement;
import graphs.ListGraph;
import graphs.MatrixGraph;

public class BFSTest {

	@Test
	public void findPathTest() throws IOException {
		String filepath = "res/duzy_graf.txt";		
		GraphIntegerIntegerFactory factory = new GraphIntegerIntegerFactory();
		GraphFileReader<Integer, Integer> graphReader = new GraphFileReader<Integer, Integer>(factory);
		
		LinkedList<Entry<Integer, Integer>> entryList = graphReader.readGraphFile(filepath);
		
		MatrixGraph<Integer, Integer> matrixGraph = new MatrixGraph<Integer, Integer>(entryList);
		ListGraph<Integer, Integer> listGraph = new ListGraph<Integer, Integer>(entryList);
		
		Integer sourceMatrix = matrixGraph.getVertexByValue(109);
		Integer destinationMatrix = matrixGraph.getVertexByValue(609);
		Integer sourceList = listGraph.getVertexByValue(109);
		Integer destinationList = listGraph.getVertexByValue(609);
		
		BFS<Integer, Integer> bfs = new BFS<Integer, Integer>(matrixGraph);
		LinkedList<Integer> path = bfs.findPath(sourceMatrix, destinationMatrix);
		System.out.println(path);
		
		bfs = new BFS<Integer, Integer>(listGraph);
		path = bfs.findPath(sourceList, destinationList);
		System.out.println(path);
		
	}
	
	@Test
	public void findPathAsListElementsTest() throws IOException {
		String filepath = "res/duzy_graf.txt";		
		GraphIntegerIntegerFactory factory = new GraphIntegerIntegerFactory();
		GraphFileReader<Integer, Integer> graphReader = new GraphFileReader<Integer, Integer>(factory);
		
		LinkedList<Entry<Integer, Integer>> entryList = graphReader.readGraphFile(filepath);
		
		MatrixGraph<Integer, Integer> matrixGraph = new MatrixGraph<Integer, Integer>(entryList);
		ListGraph<Integer, Integer> listGraph = new ListGraph<Integer, Integer>(entryList);
		
		Integer sourceMatrix = matrixGraph.getVertexByValue(109);
		Integer destinationMatrix = matrixGraph.getVertexByValue(609);
		Integer sourceList = listGraph.getVertexByValue(109);
		Integer destinationList = listGraph.getVertexByValue(609);
		
		BFS<Integer, Integer> bfs = new BFS<Integer, Integer>(matrixGraph);
		LinkedList<ListElement<Integer, Integer>> path = bfs.findPathAsListElements(sourceMatrix, destinationMatrix);
		System.out.println(path);
		
		bfs = new BFS<Integer, Integer>(listGraph);
		path = bfs.findPathAsListElements(sourceList, destinationList);
		System.out.println(path);
		
	}

}
