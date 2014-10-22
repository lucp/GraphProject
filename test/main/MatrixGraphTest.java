package main;

import static org.junit.Assert.*;

import java.io.IOException;

import factories.GraphIntegerDoubleFactory;
import graphs.MatrixGraph;

import org.junit.Test;

import reader.GraphFileReader;

public class MatrixGraphTest {

	@Test
	public void matrixGraphDefaultConstructorTest() {
		int size = 10;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		assertEquals("graph size", size, matrixGraph.getMatrixSize());
		assertEquals("graph has no vertex", matrixGraph.vertexNumber(), 0);
		assertEquals("graph has no edge", matrixGraph.vertexNumber(), 0);
	}
	
	@Test
	public void matrixGraphFileConstructorTest() {
		int buffer = 20;
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> reader = new GraphFileReader<Integer, Double>(factory);
		try{
			MatrixGraph<Integer, Double>matrixGraph = new MatrixGraph<Integer, Double>(reader.readGraphFile("res/graf.txt"), buffer);
			assertNotNull("graph exists", matrixGraph);
			assertEquals("matrix size", buffer+20, matrixGraph.getMatrixSize());
			assertEquals("graph has 20 vertecies", matrixGraph.vertexNumber(), 20);
			assertEquals("graph has 100 edges", matrixGraph.edgeNumber(), 100);
		}
		catch(IOException ioe){
			fail("IOException");
		}
	}

}
