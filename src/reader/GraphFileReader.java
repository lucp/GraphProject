package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import factories.GraphObjectFactory;

public class GraphFileReader<VertexType, EdgeType> {

	private GraphObjectFactory<VertexType, EdgeType> factory;
	
	public GraphFileReader(GraphObjectFactory<VertexType, EdgeType> factory){
		this.factory = factory;
	}
	
	public LinkedList<FileEntry> readGraphFile(String filePath){
		LinkedList<FileEntry> entries = new LinkedList<FileEntry>();
		BufferedReader reader = null;
		try {
			String line;
			String[] entry;
			reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				entry = line.split(",");
				entries.add(new FileEntry(factory.createVertex(entry[0]),factory.createVertex(entry[1]),factory.createEdge(entry[2])));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return entries;
	}
	
	public class FileEntry{
		
		public VertexType inVertex;
		public VertexType outVertex;
		public EdgeType midEdge;
		
		public FileEntry(VertexType inVertex, VertexType outVertex,EdgeType midEdge) {
			super();
			this.inVertex = inVertex;
			this.outVertex = outVertex;
			this.midEdge = midEdge;
		}
			
	}
	
}
