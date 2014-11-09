package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import factories.GraphObjectFactory;
import graphs.Entry;

public class GraphFileReader<VertexType, EdgeType> {

	private GraphObjectFactory<VertexType, EdgeType> factory;
	
	public GraphFileReader(GraphObjectFactory<VertexType, EdgeType> factory){
		this.factory = factory;
	}
	
	public LinkedList<Entry<VertexType, EdgeType>> readGraphFile(String filePath) throws IOException{
		LinkedList<VertexType> verticies = new LinkedList<VertexType>();
		LinkedList<Entry<VertexType, EdgeType>> entries = new LinkedList<Entry<VertexType, EdgeType>>();
		BufferedReader reader = null;
		try {
			String line;
			String[] entry;
			reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				entry = line.split(";");
				entry[0] = entry[0].trim();
				entry[1] = entry[1].trim();
				entry[2] = entry[2].trim();
				VertexType inVertex = factory.createVertex(entry[0]);
				if (!verticies.contains(inVertex)) {
					verticies.add(inVertex);
				}
				else{
					inVertex = verticies.get(verticies.indexOf(inVertex));
				}
				VertexType outVertex = factory.createVertex(entry[1]);
				if (!verticies.contains(outVertex)) {
					verticies.add(outVertex);
				}
				else{
					outVertex = verticies.get(verticies.indexOf(outVertex));
				}
				EdgeType edge = factory.createEdge(entry[2]);		
				entries.add(new Entry<VertexType, EdgeType>(inVertex , outVertex, edge));
			}
		}
		catch (IOException exception) {
			if (reader != null) reader.close();
			throw exception;
		}
		finally {
			if (reader != null) reader.close();
		}
		return entries;
	}
	
}
