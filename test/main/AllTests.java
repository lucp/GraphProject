package main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import algorithms.WarshallFloyd;

@RunWith(Suite.class)
@SuiteClasses({ GraphFileReaderTest.class, GraphObjectFactoryTest.class,
		MatrixGraphTest.class, ListGraphTest.class, WarshallFloydTest.class, FordBellmanTest.class })
public class AllTests {

}
