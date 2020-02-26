package data;

/**
 * This class is composed of the character's specifications
 *
 * @author gimardthibault,nathan,aymen
 *
 */

public class Character {
	/**
	 * The character's position
	 * 
	 * @see getPosition()
	 * @see setPosition(Element position)
	 */
	private int coordx;
	private int coordY;
	
	/**
	 * The character's score
	 * 
	 * @see Character(Element position, Score scores)
	 * @see getScores
	 */
	private Score scores;
	
	
	/**
	 * The constructor's class
	 * 
	 * @param position
	 * @param scores
	 * 
	 * @see Character#position
	 * @see Character#scores
	 */
	public Character(int coordx, int coordY, Score scores) {
		super();
		this.coordx = coordx;
		this.coordY = coordY;
		this.scores = scores;
	}
	
	/**
	 * Get the character's scores
	 * 
	 * @return the character's scores
	 */
	public Score getScores() {
		return scores;
	}

	/**
	 * Update the character's position
	 * 
	 * @param position
	 */
	public int getCoordX() {
		return coordx;
	}

	public void setCoordX(int coordx) {
		this.coordx = coordx;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
}
