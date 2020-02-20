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
	public Hole(int coordX, int coordY, boolean achieved, String holeType) {
		super(0,achieved);
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


}
