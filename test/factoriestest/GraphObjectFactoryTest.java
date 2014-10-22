package factoriestest;

import static org.junit.Assert.*;

import org.junit.Test;

import factories.GraphIntegerDoubleFactory;
import factories.GraphObjectFactory;

public class GraphObjectFactoryTest {

	@Test
	public void createVertexTest() {
		GraphObjectFactory<Integer, Double> factory = new GraphIntegerDoubleFactory();
		assertNotNull("create vertex", factory.createVertex("2"));
		assertEquals("integer equality", factory.createVertex("2"), new Integer(2));
	}
	
	@Test
	public void createEdgeTest() {
		GraphObjectFactory<Integer, Double> factory = new GraphIntegerDoubleFactory();
		assertNotNull("create edge", factory.createEdge("2.2"));
		assertEquals("integer equality", factory.createEdge("2.2"), new Double(2.2));
	}

}
