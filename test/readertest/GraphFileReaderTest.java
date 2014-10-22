package readertest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;

import factories.GraphIntegerDoubleFactory;
import graphs.Entry;
import reader.GraphFileReader;

public class GraphFileReaderTest {

	@Test
	public void readGraphFileTest() {
		GraphIntegerDoubleFactory factory = new GraphIntegerDoubleFactory();
		GraphFileReader<Integer, Double> reader = new GraphFileReader<Integer, Double>(factory);
		try {
			LinkedList<Entry<Integer, Double>> entries = reader.readGraphFile("res/graf.txt");
			assertNotNull("entries exists", entries);
			assertEquals("file length", entries.size(), 100);
		}
		catch(IOException ioe){
			fail("IOException");
		}
	
	}

}
