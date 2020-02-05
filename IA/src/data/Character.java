package data;

/**
 * This class is composed of the character's specifications
 *
 * @author gimardthibault
 *
 */

public class Character {
	/**
	 * The character's position
	 * 
	 * @see getPosition()
	 * @see setPosition(Element position)
	 */
	private Element position;
	
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
	public Character(Element position, Score scores) {
		super();
		this.position = position;
		this.scores = scores;
	}
	
	/**
	 * The character's position
	 * 
	 * @return the character's position
	 */
	public Element getPosition() {
		return position;
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
	public void setPosition(Element position) {
		this.position = position;
	}

}
