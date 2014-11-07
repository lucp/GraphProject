package main;

import static org.junit.Assert.*;

import java.io.IOException;

import factories.GraphIntegerDoubleFactory;
import graphs.MatrixGraph;

import org.junit.Before;
import org.junit.Test;

import reader.GraphFileReader;

public class MatrixGraphTest {

	private int size;
	private MatrixGraph<Integer, Double> matrixGraph;
	private Integer vertex1;
	private Integer vertex2;
	private Integer vertex3;
	private Double edge1;
	private Double edge2;
	
	@Before
	public void init(){
		size = 4;
		matrixGraph = new MatrixGraph<Integer, Double>(size);
		vertex1 = new Integer(1);
		vertex2 = new Integer(2);
		vertex3 = new Integer(3);
		edge1 = new Double(4);
		edge2 = new Double(5);
		matrixGraph.addVertex(vertex1);
		matrixGraph.addVertex(vertex2);
		matrixGraph.addVertex(vertex3);
		matrixGraph.addEdge(vertex1, vertex2, edge1);
		matrixGraph.addEdge(vertex2, vertex3, edge2);
	}
	
	
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
		@SuppressWarnings("unused")
		MatrixGraph<Integer, Double> matrixGraph = new MatrixGraph<Integer, Double>(reader.readGraphFile("res/niemategopliku.txt"), buffer);
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
		assertEquals("free space is 4", matrixGraph.findFreeMatrixEntry(), new Integer(3));
		Integer vertex4 = new Integer(4);
		matrixGraph.addVertex(vertex4);
		assertNull("no free matrix entry", matrixGraph.findFreeMatrixEntry());
	}
	
	@Test
	public void addVertexTest(){
		Integer vertex4 = new Integer(4);
		assertEquals("matrix size", matrixGraph.getMatrixSize(), 4);
		matrixGraph.addVertex(vertex4);
		assertEquals("2 vertex expected", matrixGraph.vertexNumber(), 4);
		assertEquals("size of matrix", matrixGraph.getMatrixSize(), 4);
		Integer vertex5 = new Integer(5);
		matrixGraph.addVertex(vertex5);
		assertEquals("2 vertex expected", matrixGraph.vertexNumber(), 5);
		assertEquals("size of matrix", matrixGraph.getMatrixSize(), 5);
		Double edge = new Double(3);
		matrixGraph.addEdge(vertex5, vertex4, edge);
		assertEquals("3 edges expected", matrixGraph.edgeNumber(), 3);
	}
	
	@Test
	public void deleteVertexTest(){
		matrixGraph.deleteVertex(vertex1);
		assertEquals("2 vertex expected", matrixGraph.vertexNumber(), 2);
		assertEquals("size of matrix", matrixGraph.getMatrixSize(), size);
		assertEquals("1 edge", matrixGraph.edgeNumber(), 1);
	}
	
	@Test
	public void addEdgeTest() {
		matrixGraph.addEdge(vertex2, vertex1, edge1);
		assertEquals("1 edge", matrixGraph.edgeNumber(), 3);
	}

	@Test
	public void deleteEdgeTest() {
		matrixGraph.deleteEdge(vertex1, vertex2);
		assertEquals("1 edge", matrixGraph.edgeNumber(), 1);
	}
	
	@Test
	public void getNeighboursTest() {
		assertFalse("neighbours", matrixGraph.getNeighbours(vertex2).contains(vertex1));
		assertTrue("neighbours", matrixGraph.getNeighbours(vertex1).contains(vertex2));
		assertTrue("neighbours", matrixGraph.getNeighbours(vertex2).contains(vertex3));
		assertFalse("not neighbours", matrixGraph.getNeighbours(vertex2).contains(vertex2));
		assertFalse("not neighbours", matrixGraph.getNeighbours(vertex1).contains(vertex3));
	}
	
	@Test
	public void getAllNeighboursTest() {
		assertTrue("neighbours", matrixGraph.getAllNeighbours(vertex2).contains(vertex1));
		assertTrue("neighbours", matrixGraph.getAllNeighbours(vertex1).contains(vertex2));
		assertTrue("neighbours", matrixGraph.getAllNeighbours(vertex2).contains(vertex3));
		assertFalse("not neighbours", matrixGraph.getAllNeighbours(vertex2).contains(vertex2));
		assertFalse("not neighbours", matrixGraph.getAllNeighbours(vertex1).contains(vertex3));
	}

	
	@Test
	public void getIncidentEdgesTest() {
		assertFalse("incident edge", matrixGraph.getIncidentEdges(vertex2).contains(edge1));
		assertTrue("incident edge", matrixGraph.getIncidentEdges(vertex2).contains(edge2));
		assertTrue("incident edge", matrixGraph.getIncidentEdges(vertex1).contains(edge1));
		assertFalse("not incident edge", matrixGraph.getIncidentEdges(vertex1).contains(edge2));
	}
	
	@Test
	public void getAllIncidentEdgesTest() {
		assertTrue("incident edge", matrixGraph.getAllIncidentEdges(vertex2).contains(edge1));
		assertTrue("incident edge", matrixGraph.getAllIncidentEdges(vertex2).contains(edge2));
		assertTrue("incident edge", matrixGraph.getAllIncidentEdges(vertex1).contains(edge1));
		assertFalse("not incident edge", matrixGraph.getAllIncidentEdges(vertex1).contains(edge2));
	}
	
	@Test
	public void areNeighboursTest() {
		assertTrue("are neighbours", matrixGraph.areNeighbours(vertex1, vertex2));
		assertTrue("are neighbours", matrixGraph.areNeighbours(vertex2, vertex3));
		assertFalse("are neighbours", matrixGraph.areNeighbours(vertex3, vertex2));
		assertFalse("are not neighbours", matrixGraph.areNeighbours(vertex1, vertex3));
	}
	
	@Test
	public void areAllNeighboursTest() {
		assertTrue("are neighbours", matrixGraph.areAllNeighbours(vertex1, vertex2));
		assertTrue("are neighbours", matrixGraph.areAllNeighbours(vertex2, vertex3));
		assertTrue("are neighbours", matrixGraph.areAllNeighbours(vertex3, vertex2));
		assertFalse("are not neighbours", matrixGraph.areAllNeighbours(vertex1, vertex3));
	}

}
