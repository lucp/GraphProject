package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import algorithms.BFS;
import reader.GraphFileReader;
import factories.GraphIntegerIntegerFactory;
import graphs.Entry;
import graphs.ListElement;
import graphs.ListGraph;
import graphs.MatrixGraph;

public class BFSTest {

	private MatrixGraph<Integer, Integer> matrixGraph;
	private ListGraph<Integer, Integer> listGraph;
	
	private Integer sourceMatrix;
	private Integer destinationMatrix;
	private Integer sourceList;
	private Integer destinationList;
	
	@Before
	public void init() throws IOException{
		
		String filepath = "res/duzy_graf.txt";		
		GraphIntegerIntegerFactory factory = new GraphIntegerIntegerFactory();
		GraphFileReader<Integer, Integer> graphReader = new GraphFileReader<Integer, Integer>(factory);
		
		LinkedList<Entry<Integer, Integer>> entryList = graphReader.readGraphFile(filepath);
		
		matrixGraph = new MatrixGraph<Integer, Integer>(entryList);
		listGraph = new ListGraph<Integer, Integer>(entryList);
		
		sourceMatrix = matrixGraph.getVertexByValue(109);
		destinationMatrix = matrixGraph.getVertexByValue(609);
		sourceList = listGraph.getVertexByValue(109);
		destinationList = listGraph.getVertexByValue(609);
	}
	
	@Test
	public void findPathTest() {
				
		BFS<Integer, Integer> bfs = new BFS<Integer, Integer>(matrixGraph);
		LinkedList<Integer> path = bfs.findPath(sourceMatrix, destinationMatrix);
//		System.out.println(path);
		
		bfs = new BFS<Integer, Integer>(listGraph);
		path = bfs.findPath(sourceList, destinationList);
//		System.out.println(path);
		
	}
	
	@Test
	public void findPathAsListElementsTest() throws IOException {
		
		
		BFS<Integer, Integer> bfs = new BFS<Integer, Integer>(matrixGraph);
		LinkedList<ListElement<Integer, Integer>> path = bfs.findPathAsListElements(sourceMatrix, destinationMatrix);
//		System.out.println(path);
		
		bfs = new BFS<Integer, Integer>(listGraph);
		path = bfs.findPathAsListElements(sourceList, destinationList);
//		System.out.println(path);
		
	}

	@Test
	public void findPathAsListElementsForbiddenValueTest() throws IOException {
		
		Integer forbiddenValue = new Integer(136);
		
		BFS<Integer, Integer> bfs = new BFS<Integer, Integer>(matrixGraph);
		LinkedList<ListElement<Integer, Integer>> path = bfs.findPathAsListElements(sourceMatrix, destinationMatrix, forbiddenValue);
//		System.out.println(path);
		
		bfs = new BFS<Integer, Integer>(listGraph);
		path = bfs.findPathAsListElements(sourceList, destinationList, forbiddenValue);
//		System.out.println(path);
		
	}
	
	@Test
	public void findPathAsListElementsForbiddenEdgesTest() throws IOException {
		
		HashSet<Integer> forbiddenEdges = new HashSet<Integer>();
		forbiddenEdges.add(this.matrixGraph.getIncidentEdges(this.matrixGraph.getVertexByValue(new Integer(109))).get(2));
		
		BFS<Integer, Integer> bfs = new BFS<Integer, Integer>(matrixGraph);
		LinkedList<ListElement<Integer, Integer>> path = bfs.findPathAsListElements(sourceMatrix, destinationMatrix, forbiddenEdges);
		System.out.println(path);
		
		bfs = new BFS<Integer, Integer>(listGraph);
		path = bfs.findPathAsListElements(sourceList, destinationList, forbiddenEdges);
		System.out.println(path);
		
	}
	
}
