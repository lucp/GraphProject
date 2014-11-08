package main;

import static org.junit.Assert.*;

import java.io.IOException;

import factories.GraphIntegerDoubleFactory;
import graphs.MatrixGraph;

import org.junit.Before;
import org.junit.Test;

import reader.GraphFileReader;
import algorithms.WarshallFloyd;

public class WarshallFloydTest {
	
	@Test
	public void executeTest() throws IOException {
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> graphReader = new GraphFileReader<Integer, Double>(factory);
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(graphReader.readGraphFile("res/graf.txt"));
		WarshallFloyd<Integer, Double> warshallFloyd = new WarshallFloyd<Integer, Double>(matrixGraph);
		Number[][] path = warshallFloyd.execute();
		print(path);
	}
	
	public void print(Number[][] path){
		for (int i = 0; i < path.length; i++){
			for (int j = 0; j < path.length; j++){
				System.out.print(path[i][j]+"\t");
			}
			System.out.println();
		}
	}

}
