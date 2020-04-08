package data;

/**
 * This class Player is the character's specifications.
 * 
 * @author gimardthibault, Nathan VIRAYIE
 *
 */
public class Player {
	/**
	 * The command define if the player has cheated or cooperate.
	 * 
	 * @see Player#hasCheat()
	 * @see Player#setHasCheat(boolean)
	 */
	private boolean isMaxPlayer;
	
	/**
	 * The player's scores.
	 * 
	 * @see Player#getScores()
	 */
	private int score;
	
	/**
	 * The constructor's class
	 * 
	 * @param hasCheat
	 * @param scores
	 */
	public Player(boolean hasCheat) {
		this.hasCheat = hasCheat;
		score = 0;
	}

	/**
	 * Return hasCheat
	 * 
	 * @return the player's choice
	 */
	public boolean cheat() {
		return hasCheat;
	}

	/**
	 * Get the scores
	 * 
	 * @return the player's score
	 */
	public int getScores() {
		return score;
	}

	public void setScore(int score) {
		this.score=score;
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
