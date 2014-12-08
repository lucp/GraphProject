package main;

import static org.junit.Assert.*;
import model.GraphIntegerDoubleFactory;
import model.GraphObjectFactory;

import org.junit.Before;
import org.junit.Test;

public class GraphObjectFactoryTest {

	private GraphObjectFactory<Integer, Double> factory;
	
	@Before
	public void init(){
		this.factory = new GraphIntegerDoubleFactory();
	}
	
	@Test
	public void createVertexTest() {
		assertNotNull("create vertex", factory.createVertex("2"));
		assertEquals("integer equality", factory.createVertex("2"), new Integer(2));
	}
	
	@Test
	public void createEdgeTest() {
		assertNotNull("create edge", factory.createEdge("2.2"));
		assertEquals("integer equality", factory.createEdge("2.2"), new Double(2.2));
	}

}
