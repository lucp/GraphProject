package main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GraphFileReaderTest.class, GraphObjectFactoryTest.class,
		MatrixGraphTest.class, ListGraphTest.class, WarshallFloydTest.class, FordBellmanTest.class, 
		FordFulkersonTest.class, BFSTest.class})
public class AllTests {

}
