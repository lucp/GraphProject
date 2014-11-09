package graphs;

import java.util.HashMap;
import java.util.LinkedList;

public class ListGraph<VertexType, EdgeType> implements Graph<VertexType, EdgeType> {

	@SuppressWarnings("hiding")
	private class ListElement<VertexType, EdgeType>{
		public VertexType inVertex;
		public EdgeType inEdge;
		public ListElement(VertexType vertex, EdgeType edge){
			this.inVertex = vertex;
			this.inEdge = edge;
		}
	}
	
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
		Integer freeIndex = 0;
		while (freeIndex < this.listSize && this.verticies.containsValue(freeIndex)){
			freeIndex++;
		}
		if (freeIndex >= this.listSize) return null;
		else return freeIndex;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addVertex(VertexType vertex) {
		Integer freeIndex = findFreeListEntry();
		if (freeIndex == null){
			LinkedList<ListElement<VertexType, EdgeType>>[] newNeighbourhood = new LinkedList[this.listSize+1];
			for (int i = 0; i < this.listSize; i++){
				newNeighbourhood[i] = this.neighbourhood[i];
			}
			this.listSize++;
			newNeighbourhood[this.listSize-1] = new LinkedList<ListElement<VertexType, EdgeType>>();
			this.neighbourhood = newNeighbourhood;
			this.verticies.put(vertex, this.listSize-1);
		}
		else{
			this.verticies.put(vertex, freeIndex);
			this.neighbourhood[freeIndex] =  new LinkedList<ListElement<VertexType, EdgeType>>();
		}
	}

	@Override
	public void deleteVertex(VertexType vertex) {
		Integer index = this.verticies.get(vertex);
		this.verticies.remove(vertex);
		
		this.neighbourhood[index] = null;
	}

	@Override
	public void addEdge(VertexType source, VertexType destination, EdgeType edge) {
		Integer sourceIndex = this.verticies.get(source);
		this.neighbourhood[sourceIndex].add(new ListElement<VertexType, EdgeType>(destination, edge));
	}

	@Override
	public void deleteEdge(VertexType source, VertexType destination) {
		Integer sourceIndex = this.verticies.get(source);
		int destinationIndex = -1;
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			if (this.neighbourhood[sourceIndex].get(i).inVertex == destination){
				destinationIndex = i;
				break;
			}
		}
		if (destinationIndex != -1){
			this.neighbourhood[sourceIndex].remove(destinationIndex);
		}
	}

	@Override
	public LinkedList<VertexType> getNeighbours(VertexType vertex) {
		Integer sourceIndex = this.verticies.get(vertex);
		LinkedList<VertexType> neighbours = new LinkedList<VertexType>();
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			neighbours.add(this.neighbourhood[sourceIndex].get(i).inVertex);
		}
		return neighbours;
	}
	
	public LinkedList<VertexType> getAllNeighbours(VertexType vertex) {
		Integer sourceIndex = this.verticies.get(vertex);
		LinkedList<VertexType> neighbours = new LinkedList<VertexType>();
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			neighbours.add(this.neighbourhood[sourceIndex].get(i).inVertex);
		}
		for (VertexType neighbour : this.verticies.keySet()){
			if (neighbour != vertex){
				sourceIndex = this.verticies.get(neighbour);
				for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
					if (this.neighbourhood[sourceIndex].get(i).inVertex == vertex){
						neighbours.add(neighbour);
					}
				}
			}
		}
		return neighbours;
	}

	@Override
	public LinkedList<EdgeType> getIncidentEdges(VertexType vertex) {
		LinkedList<EdgeType> incidentEdges = new LinkedList<EdgeType>();
		Integer sourceIndex = this.verticies.get(vertex);
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			incidentEdges.add(this.neighbourhood[sourceIndex].get(i).inEdge);
		}
		return incidentEdges;
	}
	
	public LinkedList<EdgeType> getAllIncidentEdges(VertexType vertex) {
		LinkedList<EdgeType> incidentEdges = new LinkedList<EdgeType>();
		Integer sourceIndex = this.verticies.get(vertex);
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			incidentEdges.add(this.neighbourhood[sourceIndex].get(i).inEdge);
		}
		for (VertexType neighbour : this.verticies.keySet()){
			if (neighbour != vertex){
				sourceIndex = this.verticies.get(neighbour);
				for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
					if (this.neighbourhood[sourceIndex].get(i).inVertex == vertex){
						incidentEdges.add(this.neighbourhood[sourceIndex].get(i).inEdge);
					}
				}
			}
		}
		return incidentEdges;
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
		Integer sourceIndex = this.verticies.get(firstVertex);
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			if (this.neighbourhood[sourceIndex].get(i).inVertex == secondVertex){
				return true;
			}
		}
		return false;
	}
	
	public boolean areAllNeighbours(VertexType firstVertex, VertexType secondVertex) {
		Integer sourceIndex = this.verticies.get(firstVertex);
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			if (this.neighbourhood[sourceIndex].get(i).inVertex == secondVertex){
				return true;
			}
		}
		sourceIndex = this.verticies.get(secondVertex);
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			if (this.neighbourhood[sourceIndex].get(i).inVertex == firstVertex){
				return true;
			}
		}
		return false;
	}
	


	@Override
	public EdgeType getEdge(VertexType source, VertexType destination) {
		Integer sourceIndex = this.verticies.get(source);
		for (int i = 0; i < this.neighbourhood[sourceIndex].size(); i++){
			if (this.neighbourhood[sourceIndex].get(i).inVertex == destination){
				return this.neighbourhood[sourceIndex].get(i).inEdge;
			}
		}
		return null;
	}

	@Override
	public LinkedList<VertexType> getVerticies() {
		LinkedList<VertexType> verticies = new LinkedList<VertexType>();
		for (VertexType vertex : this.verticies.keySet()){
			verticies.add(vertex);
		}
		return verticies;
	}

	@Override
	public Entry<VertexType, EdgeType> getVertexPair(EdgeType edge) {
		for (VertexType inVertex : this.verticies.keySet()){
			for (ListElement<VertexType, EdgeType> element : this.neighbourhood[this.verticies.get(inVertex)]){
				if (element.inEdge == edge){
					return new Entry<VertexType, EdgeType>(inVertex, element.inVertex, element.inEdge);
				}
			}
		}
		return null;
	}

	@Override
	public LinkedList<EdgeType> getEdges() {
		LinkedList<EdgeType> list = new LinkedList<EdgeType>();
		for (VertexType inVertex : this.verticies.keySet()){
			for (ListElement<VertexType, EdgeType> element : this.neighbourhood[this.verticies.get(inVertex)]){
				list.add(element.inEdge);
			}
		}
		return list;
	}
	
	@Override
	public String toString() {
		String listGraphString = new String();
		for (VertexType vertex : this.verticies.keySet()){
			listGraphString += vertex.toString() + ":\t";
			int index = this.verticies.get(vertex);
			for (ListElement entry : this.neighbourhood[index]){
				listGraphString += "(" + entry.inEdge.toString() + "," + entry.inVertex.toString() + ")\t";
			}
			listGraphString += "\n";
		}
		return listGraphString;
	}

}
