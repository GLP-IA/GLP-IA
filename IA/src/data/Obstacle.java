package data;

public class Obstacle extends Element {
	private String type;

	public Obstacle(int coordX, int coordY, String type) {
		super(coordX, coordY);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
