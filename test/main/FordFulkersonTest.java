package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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
		System.out.println("Ford-Fulkerson\n");
		
		FordFulkerson<Integer, Integer> fordFulkerson = new FordFulkerson<Integer, Integer>(matrixGraph);
		long startTime = System.currentTimeMillis();
		Double flow = (Double) fordFulkerson.execute(sourceMatrix, destinationMatrix);
		long stopTime = System.currentTimeMillis();
		long elapsedTimeM = stopTime - startTime;
		System.out.println("Time: " + new Float(elapsedTimeM)/1000);
		System.out.println(flow);
		 
		fordFulkerson = new FordFulkerson<Integer, Integer>(listGraph);
		startTime = System.currentTimeMillis();
		flow = (Double) fordFulkerson.execute(sourceList, destinationList);
		stopTime = System.currentTimeMillis();
		elapsedTimeM = stopTime - startTime;
		System.out.println("Time: " + new Float(elapsedTimeM)/1000);
		System.out.println(flow);
	}
}
