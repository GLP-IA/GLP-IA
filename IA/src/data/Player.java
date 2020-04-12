package data;

/**
 * This class Player is the character's specifications.
 * 
 * @author gimardthibault, Nathan VIRAYIE
 *
 */
public class Player {
	/**
	 * The command define if the player is MaxPalayer or not.
	 * 
	 * 
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
	public Player(boolean isMaxPlayer) {
		this.isMaxPlayer = true;
		score = 0;
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


	
	
	
	

}
