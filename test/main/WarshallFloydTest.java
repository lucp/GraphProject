package main;

import static org.junit.Assert.*;

import java.io.IOException;

import factories.GraphIntegerDoubleFactory;
import graphs.MatrixGraph;

import org.junit.Test;

import reader.GraphFileReader;
import algorithms.WarshallFloyd;

public class WarshallFloydTest {
	
	@Test
	public void executeTest() throws IOException {
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> graphReader = new GraphFileReader<Integer, Double>(factory);
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(graphReader.readGraphFile("res/duzy_graf.txt"));
		WarshallFloyd<Integer, Double> warshallFloyd = new WarshallFloyd<Integer, Double>(matrixGraph);
		Number[][] path = warshallFloyd.execute();
		assertEquals(path[109][609].doubleValue(), 18.0, 0.1);
		printPath(109, 609, warshallFloyd);
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
