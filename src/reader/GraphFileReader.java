package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class GraphFileReader<VertexType, EdgeType> {

	private GraphObjectFactory<VertexType, EdgeType> factory;
	
	public GraphFileReader(GraphObjectFactory<VertexType, EdgeType> factory){
		this.factory = factory;
	}
	
	/*public Map<> readGraphGile(String filePath){
		LinkedList<VertexType[]> connectedNodes = new LinkedList<VertexType[]>();
		LinkedList<EdgeType>  connectingEgde = new LinkedList<EdgeType>();
		BufferedReader reader = null;
		try {
			String line;
			String[] entry;
			reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				entry = line.split(",");
				connectedNodes.add(this.factory.createVertex(entry[0]))
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}*/
	
	//public int getVertexNumberFromLines(LinkedList<String[]> lines)
}
