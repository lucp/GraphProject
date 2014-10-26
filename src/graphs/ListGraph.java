package graphs;

import java.util.HashMap;
import java.util.LinkedList;

public class ListGraph<VertexType, EdgeType> implements Graph<VertexType, EdgeType> {

	int listSize;
	
	LinkedList<ListElement<VertexType, EdgeType>>[] neighbourhood;
	
	private HashMap<VertexType, Integer> verticies;
	
	@SuppressWarnings("unchecked")
	public ListGraph(int listSize){
		this.listSize = listSize;
		this.neighbourhood = new LinkedList[this.listSize];
		this.verticies = new HashMap<VertexType, Integer>();
	}
	
	@SuppressWarnings("unchecked")
	public ListGraph(LinkedList<Entry<VertexType, EdgeType>> fileEntries){
		Integer index = 0;
		this.verticies = new HashMap<VertexType, Integer>();
		for (Entry<VertexType, EdgeType> entry : fileEntries){
			if (!this.verticies.containsKey(entry.inVertex)){
				this.verticies.put(entry.inVertex,index);
				index++;
			}
			if (!this.verticies.containsKey(entry.outVertex)){
				this.verticies.put(entry.outVertex,index);
				index++;
			}
		}
		this.listSize = this.verticies.size();
		this.neighbourhood = new LinkedList[this.listSize];
		for (int i = 0; i < this.listSize; i++){
			this.neighbourhood[i] =  new LinkedList<ListElement<VertexType, EdgeType>>();
		}
		for (Entry<VertexType, EdgeType> entry : fileEntries){
			this.neighbourhood[this.verticies.get(entry.inVertex)].add(new ListElement<VertexType, EdgeType>(entry.outVertex, entry.midEdge));
		}
	}
	
	@SuppressWarnings("unchecked")
	public ListGraph(LinkedList<Entry<VertexType, EdgeType>> fileEntries, int buffer){
		Integer index = 0;
		this.verticies = new HashMap<VertexType, Integer>();
		for (Entry<VertexType, EdgeType> entry : fileEntries){
			if (!this.verticies.containsKey(entry.inVertex)){
				this.verticies.put(entry.inVertex,index);
				index++;
			}
			if (!this.verticies.containsKey(entry.outVertex)){
				this.verticies.put(entry.outVertex,index);
				index++;
			}
		}
		this.listSize = this.verticies.size()+buffer;
		this.neighbourhood = new LinkedList[this.listSize];
		for (int i = 0; i < this.listSize-buffer; i++){
			this.neighbourhood[i] =  new LinkedList<ListElement<VertexType, EdgeType>>();
		}
		for (Entry<VertexType, EdgeType> entry : fileEntries){
			this.neighbourhood[this.verticies.get(entry.inVertex)].add(new ListElement<VertexType, EdgeType>(entry.outVertex, entry.midEdge));
		}
	}
	
	public int getListSize(){
		return this.listSize;
	}
	
	public Integer findFreeListEntry(){
		return null;
	}
	
	@Override
	public void addVertex(VertexType vertex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVertex(VertexType vertex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(VertexType source, VertexType destination, EdgeType edge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEdge(VertexType source, VertexType destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<VertexType> getNeighbours(VertexType vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<EdgeType> getIncidentEdges(VertexType vartex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int vertexNumber() {
		return this.verticies.size();
	}

	@Override
	public int edgeNumber() {
		int edgeNumber = 0;
		for (LinkedList<ListElement<VertexType, EdgeType>> list : this.neighbourhood){
			if (list != null){
				edgeNumber += list.size();
			}
		}
		return edgeNumber;
	}

	@Override
	public boolean areNeighbours(VertexType firstVertex, VertexType secondVertex) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@SuppressWarnings("hiding")
	private class ListElement<VertexType, EdgeType>{
		public VertexType inVertex;
		public EdgeType inEdge;
		public ListElement(VertexType vertex, EdgeType edge){
			this.inVertex = vertex;
			this.inEdge = edge;
		}
	}

}
