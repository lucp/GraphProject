package main;

import static org.junit.Assert.*;

import java.io.IOException;

import factories.GraphIntegerDoubleFactory;
import graphs.ListGraph;

import org.junit.Before;
import org.junit.Test;

import reader.GraphFileReader;

public class ListGraphTest {

	private int size;
	private ListGraph<Integer, Double> listGraph;
	private Integer vertex1;
	private Integer vertex2;
	private Integer vertex3;
	private Double edge1;
	private Double edge2;
	
	@Before
	public void init(){
		size = 4;
		listGraph = new ListGraph<Integer, Double>(size);
		vertex1 = new Integer(1);
		vertex2 = new Integer(2);
		vertex3 = new Integer(3);
		edge1 = new Double(4);
		edge2 = new Double(5);
		listGraph.addVertex(vertex1);
		listGraph.addVertex(vertex2);
		listGraph.addVertex(vertex3);
		listGraph.addEdge(vertex1, vertex2, edge1);
		listGraph.addEdge(vertex2, vertex3, edge2);
	}
	
	
	@Test
	public void listGraphDefaultConstructorTest() {
		int size = 10;
		ListGraph<Integer, Double> listGraph = new ListGraph<Integer, Double>(size);
		assertEquals("graph size", size, listGraph.getListSize());
		assertEquals("graph has no vertex", listGraph.vertexNumber(), 0);
		assertEquals("graph has no edge", listGraph.vertexNumber(), 0);
	}
	
	@Test(expected = IOException.class)
	public void listGraphFileConstructorExceptionTest() throws IOException {
		int buffer = 20;
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> reader = new GraphFileReader<Integer, Double>(factory);
		@SuppressWarnings("unused")
		ListGraph<Integer, Double> listGraph = new ListGraph<Integer, Double>(reader.readGraphFile("res/niemategopliku.txt"), buffer);
	}
	
	@Test
	public void listGraphFileConstructorTest() throws IOException {
		int buffer = 20;
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> reader = new GraphFileReader<Integer, Double>(factory);
		ListGraph<Integer, Double>listGraph = new ListGraph<Integer, Double>(reader.readGraphFile("res/graf.txt"), buffer);
		assertNotNull("graph exists", listGraph);
		assertEquals("list size", buffer+20, listGraph.getListSize());
		assertEquals("graph has 20 vertecies", listGraph.vertexNumber(), 20);
		assertEquals("graph has 100 edges", listGraph.edgeNumber(), 100);
	}
	
	@Test
	public void findFreeListEntryTest(){
		assertEquals("free space is 4", listGraph.findFreeListEntry(), new Integer(3));
		Integer vertex4 = new Integer(4);
		listGraph.addVertex(vertex4);
		assertNull("no free list entry", listGraph.findFreeListEntry());
	}
	
	@Test
	public void addVertexTest(){
		Integer vertex4 = new Integer(4);
		assertEquals("list size", listGraph.getListSize(), 4);
		listGraph.addVertex(vertex4);
		assertEquals("2 vertex expected", listGraph.vertexNumber(), 4);
		assertEquals("size of list", listGraph.getListSize(), 4);
		Integer vertex5 = new Integer(5);
		listGraph.addVertex(vertex5);
		assertEquals("2 vertex expected", listGraph.vertexNumber(), 5);
		assertEquals("size of list", listGraph.getListSize(), 5);
		Double edge = new Double(3);
		listGraph.addEdge(vertex5, vertex4, edge);
		assertEquals("3 edges expected", listGraph.edgeNumber(), 3);
	}
	
	@Test
	public void deleteVertexTest(){
		listGraph.deleteVertex(vertex1);
		assertEquals("2 vertex expected", listGraph.vertexNumber(), 2);
		assertEquals("size of list", listGraph.getListSize(), size);
		assertEquals("1 edge", listGraph.edgeNumber(), 1);
	}
	
	@Test
	public void addEdgeTest() {
		listGraph.addEdge(vertex2, vertex1, edge1);
		assertEquals("1 edge", listGraph.edgeNumber(), 3);
	}

	@Test
	public void deleteEdgeTest() {
		listGraph.deleteEdge(vertex1, vertex2);
		assertEquals("1 edge", listGraph.edgeNumber(), 1);
	}
	
	@Test
	public void getNeighboursTest() {
		assertTrue("neighbours", listGraph.getNeighbours(vertex1).contains(vertex2));
		assertTrue("neighbours", listGraph.getNeighbours(vertex2).contains(vertex1));
		assertTrue("neighbours", listGraph.getNeighbours(vertex2).contains(vertex3));
		assertFalse("not neighbours", listGraph.getNeighbours(vertex2).contains(vertex2));
		assertFalse("not neighbours", listGraph.getNeighbours(vertex1).contains(vertex3));
	}

	@Test
	public void getIncidentEdgesTest() {
		assertTrue("incident edge", listGraph.getIncidentEdges(vertex1).contains(edge1));
		assertTrue("incident edge", listGraph.getIncidentEdges(vertex2).contains(edge1));
		assertTrue("incident edge", listGraph.getIncidentEdges(vertex2).contains(edge2));
		assertFalse("not incident edge", listGraph.getIncidentEdges(vertex1).contains(edge2));
	}

	@Test
	public void areNeighboursTest() {
		assertTrue("are neighbours", listGraph.areNeighbours(vertex1, vertex2));
		assertTrue("are neighbours", listGraph.areNeighbours(vertex2, vertex3));
		assertTrue("are neighbours", listGraph.areNeighbours(vertex3, vertex2));
		assertFalse("are not neighbours", listGraph.areNeighbours(vertex1, vertex3));
	}

}