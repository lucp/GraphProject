package graphstest;

import static org.junit.Assert.*;
import graphs.MatrixGraph;

import org.junit.Test;

public class MatrixGraphTest {

	@Test
	public void matrixGraphDefaultConstructorTest() {
		int size = 10;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		assertEquals("graph size", size, matrixGraph.getMatrixSize());
		assertEquals("graph has no vertex", matrixGraph.vertexNumber(), 0);
		assertEquals("graph has no edge", matrixGraph.vertexNumber(), 0);
	}
	
	/*@Test
	public void matrixGraphFileConstructorTest() {
		GraphFileReader reader
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(buffer);
		assertEquals("graph size", buffer, matrixGraph.getMatrixSize());
		assertEquals("graph has no vertex", matrixGraph.vertexNumber(), 0);
		assertEquals("graph has no edge", matrixGraph.vertexNumber(), 0);
	}*/

}
