package data;

public class Hole extends Target {
	
	private String holeType;

	public Hole(int coordX, int coordY, boolean achieved, String holeType) {
		super(coordX, coordY, achieved);
		this.holeType = holeType;
	}

	public String getHoleType() {
		return holeType;
	}


}
