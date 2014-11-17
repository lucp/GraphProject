package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import factories.GraphIntegerIntegerFactory;
import graphs.Entry;
import graphs.ListGraph;
import graphs.MatrixGraph;

import org.junit.Test;

import reader.GraphFileReader;
import algorithms.FordBellman;

public class FordBellmanTest {
	
	@Test
	public void executeTest() throws IOException {
		System.out.println("Ford-Bellman\n");
		
		String filepath = "res/duzy_graf.txt";		
		GraphIntegerIntegerFactory factory = new GraphIntegerIntegerFactory();
		GraphFileReader<Integer, Integer> graphReader = new GraphFileReader<Integer, Integer>(factory);
		
		LinkedList<Entry<Integer, Integer>> entryList = graphReader.readGraphFile(filepath);
		
		MatrixGraph<Integer, Integer> matrixGraph = new MatrixGraph<Integer, Integer>(entryList);
		ListGraph<Integer, Integer> listGraph = new ListGraph<Integer, Integer>(entryList);
		
		Integer inVertex = matrixGraph.getVertexByValue(109);
		Integer outVertex = matrixGraph.getVertexByValue(609);
		
		System.out.println("- Matrix Graph -");
		FordBellman<Integer, Integer> fordBellman = new FordBellman<Integer, Integer>(matrixGraph);
		long startTime = System.currentTimeMillis();
		fordBellman.execute(inVertex);
		long stopTime = System.currentTimeMillis();
		long elapsedTimeM = stopTime - startTime;
		System.out.println("Time: " + new Float(elapsedTimeM)/1000);
		System.out.println(fordBellman);
		printPath(inVertex, outVertex, fordBellman);
		
		System.out.println("\n- List Graph -");
		fordBellman = new FordBellman<Integer, Integer>(listGraph);
		startTime = System.currentTimeMillis();		
		fordBellman.execute(inVertex);
		stopTime = System.currentTimeMillis();
		long elapsedTimeL = stopTime - startTime;
		System.out.println("Time: " + new Float(elapsedTimeL)/1000);
		System.out.println(fordBellman);
		printPath(inVertex, outVertex, fordBellman);
		
		System.out.println("\nR: " + new Double(elapsedTimeL)/new Double(elapsedTimeM));
	
	}
	
	public void printDistance(Integer source, Integer destination, FordBellman<Integer, Integer> fordBellman){
		System.out.println(fordBellman.getWeight(destination));
	}
	
	public void printPath(Integer source, Integer destination, FordBellman<Integer, Integer> fordBellman){
		Integer point = destination;
		System.out.print("Path: ");
		while (point != null && !point.equals(source)){
			System.out.print(point + " <- ");
			point = fordBellman.getPredecessor(point);
		}
		System.out.println(source);
	}

}
