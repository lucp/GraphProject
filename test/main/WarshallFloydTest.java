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
import algorithms.WarshallFloyd;

public class WarshallFloydTest {
	
	@Test
	public void executeTest() throws IOException {
		System.out.println("Warshall-Floyd\n");
		
		String filepath = "res/duzy_graf.txt";		
		GraphIntegerIntegerFactory factory = new GraphIntegerIntegerFactory();
		GraphFileReader<Integer, Integer> graphReader = new GraphFileReader<Integer, Integer>(factory);
		
		LinkedList<Entry<Integer, Integer>> entryList = graphReader.readGraphFile(filepath);
		
		MatrixGraph<Integer, Integer> matrixGraph = new MatrixGraph<Integer, Integer>(entryList);
		ListGraph<Integer, Integer> listGraph = new ListGraph<Integer, Integer>(entryList);
		
		System.out.println("- Matrix Graph -");
		WarshallFloyd<Integer, Integer> warshallFloyd = new WarshallFloyd<Integer, Integer>(matrixGraph);
		long startTime = System.currentTimeMillis();
		warshallFloyd.execute();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time: " + new Float(elapsedTime)/1000);
//		System.out.println(matrixGraph);
//		System.out.println(warshallFloyd);
		printPath(matrixGraph.getVertexByValue(109), matrixGraph.getVertexByValue(609), warshallFloyd);
		
		System.out.println("\n- List Graph -");
		warshallFloyd = new WarshallFloyd<Integer, Integer>(listGraph);
		startTime = System.currentTimeMillis();		
		warshallFloyd.execute();
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("Time: " + new Float(elapsedTime)/1000);
//		System.out.println(listGraph);
//		System.out.println(warshallFloyd);
		printPath(listGraph.getVertexByValue(109), listGraph.getVertexByValue(609), warshallFloyd);
		
	}
	
	public void printDistances(Number[][] path){
		for (int i = 0; i < path.length; i++){
			for (int j = 0; j < path.length; j++){
				if (path[i][j].doubleValue() == Integer.MAX_VALUE) System.out.print("Inf\t");
				else System.out.print(path[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public void printPath(Integer source, Integer destination, WarshallFloyd<Integer, Integer> warshallFloyd){
		Integer point = destination;
		System.out.print("Path: ");
		while (!point.equals(source)){
			System.out.print(point + " <- ");
			point = warshallFloyd.getPredecessor(source, point);
		}
		System.out.println(point);
		System.out.println("Distance: " + warshallFloyd.getPath(source, destination));
	}

}
