package data;

/**
 * This class Player is the character's specifications.
 * 
 * @author gimardthibault
 *
 */
public class Player {
	/**
	 * The command define if the player has cheated or cooperate.
	 * 
	 * @see Player#isHasCheat()
	 * @see Player#setHasCheat(boolean)
	 */
	private boolean hasCheat;
	
	/**
	 * The player's scores.
	 * 
	 * @see Player#getScores()
	 */
	private Score scores;
	
	/**
	 * The constructor's class
	 * 
	 * @param hasCheat
	 * @param scores
	 */
	public Player(boolean hasCheat, Score scores) {
		this.hasCheat = hasCheat;
		this.scores = scores;
	}

	/**
	 * Return hasCheat
	 * 
	 * @return the player's choice
	 */
	public boolean isHasCheat() {
		return hasCheat;
	}

	/**
	 * Get the scores
	 * 
	 * @return the player's score
	 */
	public Score getScores() {
		return scores;
	}

	/**
	 * Update the cheat
	 * 
	 * @param hasCheat
	 */
	public void setHasCheat(boolean hasCheat) {
		this.hasCheat = hasCheat;
	}


	
	
	
	

}
