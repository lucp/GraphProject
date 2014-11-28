package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import algorithms.BFS;
import algorithms.FordFulkerson;
import reader.GraphFileReader;
import factories.GraphIntegerIntegerFactory;
import graphs.Entry;
import graphs.ListGraph;
import graphs.MatrixGraph;

public class FordFulkersonTest {

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
	public void test() {
		 BFS<Integer, Integer> bfs = new BFS<Integer, Integer>(matrixGraph);
		 FordFulkerson<Integer, Integer> fordFulkerson = new FordFulkerson<Integer, Integer>(matrixGraph);
		 Integer flow = (Integer) fordFulkerson.execute(sourceMatrix, destinationMatrix);
		 System.out.println(flow);
		 
		 bfs = new BFS<Integer, Integer>(listGraph);
		 fordFulkerson = new FordFulkerson<Integer, Integer>(listGraph);
		 flow = (Integer) fordFulkerson.execute(sourceList, destinationList);
		 System.out.println(flow);
	}
}
