package data;

/**
 * This class Hole extends the class Target. 
 * The holes are a trap in the game.
 * 
 * @author gimardthibault
 *
 */
public class Hole extends Target {
	/**
	 * The hole's type
	 * 
	 * @see Hole#Hole(int, int, boolean, String)
	 * @see Hole#getHoleType()
	 */
	private String holeType;
	private int coordX;
	private int coordY;

	/**
	 * The constructor's class
	 * 
	 * @param coordX
	 * @param coordY
	 * @param achieved
	 * @param holeType forme du trou (carré,triangle etc)
	 * 
	 * @see Hole#holeType
	 */
	public Hole(int coordX, int coordY, String holeType) {
		super(0,false);
		this.coordX=coordX;
		this.coordY=coordY;
		this.holeType = holeType;
	}
	
	/**
	 * Get the type of the hole
	 * 
	 * @return the holeType
	 */
	public String getHoleType() {
		return holeType;
	}

	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}
}
