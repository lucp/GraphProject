package model;

public class HEdge {
	
	public byte code;

	public HEdge(byte code) {
		super();
		this.code = code;
	}
	
	@Override
	public boolean equals(Object hedge) {
		if (this.code == ((HEdge)hedge).code) return true;
		else return false;
	}
	
}
