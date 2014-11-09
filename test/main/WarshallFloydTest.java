package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import factories.GraphIntegerDoubleFactory;
import graphs.Entry;
import graphs.ListGraph;
import graphs.MatrixGraph;

import org.junit.Test;

import reader.GraphFileReader;
import algorithms.WarshallFloyd;

public class WarshallFloydTest {
	
	@Test
	public void executeTest() throws IOException {
		
		String filepath = "res/duzy_graf.txt";		
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> graphReader = new GraphFileReader<Integer, Double>(factory);
		
		LinkedList<Entry<Integer, Double>> entryList = graphReader.readGraphFile(filepath);
		
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(entryList);
		ListGraph<Integer, Double> listGraph = new ListGraph<Integer, Double>(entryList);
		
		WarshallFloyd<Integer, Double> warshallFloyd = new WarshallFloyd<Integer, Double>(matrixGraph);
		long startTime = System.currentTimeMillis();
		warshallFloyd.execute();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time: " + new Float(elapsedTime)/1000);
//		System.out.println(matrixGraph);
//		System.out.println(warshallFloyd);
		printPath(matrixGraph.getVertexByValue(106), matrixGraph.getVertexByValue(609), warshallFloyd);
		
		warshallFloyd = new WarshallFloyd<Integer, Double>(listGraph);
		startTime = System.currentTimeMillis();		
		warshallFloyd.execute();
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("Time: " + new Float(elapsedTime)/1000);
//		System.out.println(listGraph);
//		System.out.println(warshallFloyd);
		printPath(listGraph.getVertexByValue(106), listGraph.getVertexByValue(609), warshallFloyd);
		
	}
	
	public void printDistances(Number[][] path){
		for (int i = 0; i < path.length; i++){
			for (int j = 0; j < path.length; j++){
				if (path[i][j].doubleValue() == Double.MAX_VALUE) System.out.print("Inf\t");
				else System.out.print(path[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public void printPath(Integer source, Integer destination, WarshallFloyd<Integer, Double> warshallFloyd){
		Integer point = destination;
		while (!point.equals(source)){
			System.out.print(point + " <- ");
			point = warshallFloyd.getPredecessor(source, point);
		}
		System.out.println(point);
		System.out.println("distance: " + warshallFloyd.getPath(source, destination));
	}

}
