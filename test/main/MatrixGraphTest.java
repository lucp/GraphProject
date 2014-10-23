package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

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
	
	@Test(expected = IOException.class)
	public void matrixGraphFileConstructorExceptionTest() throws IOException {
		int buffer = 20;
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> reader = new GraphFileReader<Integer, Double>(factory);
		MatrixGraph<Integer, Double>matrixGraph = new MatrixGraph<Integer, Double>(reader.readGraphFile("res/niemategopliku.txt"), buffer);
	}
	
	@Test
	public void matrixGraphFileConstructorTest() throws IOException {
		int buffer = 20;
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> reader = new GraphFileReader<Integer, Double>(factory);
		MatrixGraph<Integer, Double>matrixGraph = new MatrixGraph<Integer, Double>(reader.readGraphFile("res/graf.txt"), buffer);
		assertNotNull("graph exists", matrixGraph);
		assertEquals("matrix size", buffer+20, matrixGraph.getMatrixSize());
		assertEquals("graph has 20 vertecies", matrixGraph.vertexNumber(), 20);
		assertEquals("graph has 100 edges", matrixGraph.edgeNumber(), 100);
	}
	
	@Test
	public void findFreeMatrixEntryTest(){
		int size = 2;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		Integer vertex1 = new Integer(1);
		Integer vertex2 = new Integer(2);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		assertNull("no free matrix entry", matrixGraph.findFreeMatrixEntry());
		size = 2;
		matrixGraph = new MatrixGraph<Integer, Double>(size);
		vertex1 = new Integer(1);
		matrixGraph.addVertex(vertex1);
		assertEquals("free space is 1", matrixGraph.findFreeMatrixEntry(), new Integer(1));
	}
	
	@Test
	public void addVertexTest(){
		int size = 3;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		Integer vertex1 = new Integer(1);
		Integer vertex2 = new Integer(2);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		assertEquals("2 vertex expected", matrixGraph.vertexNumber(), 2);
		assertEquals("size of matrix", matrixGraph.getMatrixSize(), 3);
		
		size = 1;
		matrixGraph = new MatrixGraph<Integer, Double>(size);
		vertex1 = new Integer(1);
		vertex2 = new Integer(2);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		assertEquals("2 vertex expected", matrixGraph.vertexNumber(), 2);
		assertEquals("size of matrix", matrixGraph.getMatrixSize(), 2);
	}
	
	@Test
	public void deleteVertexTest(){
		int size = 3;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		Integer vertex1 = new Integer(1);
		Integer vertex2 = new Integer(2);
		Double edge = new Double(3);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		matrixGraph.addEdge(vertex1, vertex2, edge);
		matrixGraph.deleteVertex(vertex1);
		assertEquals("1 vertex expected", matrixGraph.vertexNumber(), 1);
		assertEquals("size of matrix", matrixGraph.getMatrixSize(), 3);
		assertEquals("no edge", matrixGraph.edgeNumber(), 0);
	}
	
	@Test
	public void addEdgeTest() {
		int size = 3;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		Integer vertex1 = new Integer(1);
		Integer vertex2 = new Integer(2);
		Double edge = new Double(3);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		matrixGraph.addEdge(vertex1, vertex2, edge);
		assertEquals("1 edge", matrixGraph.edgeNumber(), 1);
	}

	@Test
	public void deleteEdgeTest() {
		int size = 3;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		Integer vertex1 = new Integer(1);
		Integer vertex2 = new Integer(2);
		Double edge = new Double(3);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		matrixGraph.addEdge(vertex1, vertex2, edge);
		matrixGraph.deleteEdge(vertex1, vertex2);
		assertEquals("1 edge", matrixGraph.edgeNumber(), 0);
	}
	
	@Test
	public void getNeighboursTest() {
		int size = 3;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		Integer vertex1 = new Integer(1);
		Integer vertex2 = new Integer(2);
		Integer vertex3 = new Integer(3);
		Double edge1 = new Double(4);
		Double edge2 = new Double(5);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		matrixGraph.addVertex(vertex3);
		matrixGraph.addEdge(vertex1, vertex2, edge1);
		matrixGraph.addEdge(vertex2, vertex3, edge2);
		assertTrue("neighbours", matrixGraph.getNeighbours(vertex2).contains(vertex1));
		assertTrue("neighbours", matrixGraph.getNeighbours(vertex2).contains(vertex3));
		assertFalse("not neighbours", matrixGraph.getNeighbours(vertex2).contains(vertex2));
		assertFalse("not neighbours", matrixGraph.getNeighbours(vertex1).contains(vertex3));
	}

	@Test
	public void getIncidentEdgesTest() {
		int size = 3;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		Integer vertex1 = new Integer(1);
		Integer vertex2 = new Integer(2);
		Integer vertex3 = new Integer(3);
		Double edge1 = new Double(4);
		Double edge2 = new Double(5);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		matrixGraph.addVertex(vertex3);
		matrixGraph.addEdge(vertex1, vertex2, edge1);
		matrixGraph.addEdge(vertex2, vertex3, edge2);
		assertTrue("incident edge", matrixGraph.getIncidentEdges(vertex2).contains(edge1));
		assertTrue("incident edge", matrixGraph.getIncidentEdges(vertex2).contains(edge2));
		assertTrue("incident edge", matrixGraph.getIncidentEdges(vertex1).contains(edge1));
		assertFalse("not incident edge", matrixGraph.getIncidentEdges(vertex1).contains(edge2));
	}

	@Test
	public void areNeighboursTest() {
		int size = 3;
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(size);
		Integer vertex1 = new Integer(1);
		Integer vertex2 = new Integer(2);
		Integer vertex3 = new Integer(3);
		Double edge1 = new Double(4);
		Double edge2 = new Double(5);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		matrixGraph.addVertex(vertex3);
		matrixGraph.addEdge(vertex1, vertex2, edge1);
		matrixGraph.addEdge(vertex2, vertex3, edge2);
		assertTrue("are neighbours", matrixGraph.areNeighbours(vertex1, vertex2));
		assertTrue("are neighbours", matrixGraph.areNeighbours(vertex2, vertex3));
		assertFalse("are not neighbours", matrixGraph.areNeighbours(vertex1, vertex3));
	}

}
