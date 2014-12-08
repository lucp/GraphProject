package model;

public class HVertex {

	public int probability;
	public String character;
	
	public HVertex(int probability, String character) {
		super();
		this.probability = probability;
		this.character = character;
	}
	
	@Override
	public boolean equals(Object hvertex) {
		if (this.character.equals(((HVertex)hvertex).character)) return true;
		else return false;
	}
	
}
