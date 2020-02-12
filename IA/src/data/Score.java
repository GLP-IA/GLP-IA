package data;

/**
 * The class Score permits to generate the player's score
 * 
 * @author gimardthibault
 *
 */
import java.util.ArrayList;

public class Score {
	/**
	 * The list evolution of integer
	 */
	private ArrayList<Integer> evolution;
	
	/**
	 * Update the evolution of the score
	 * 
	 * @param evolution
	 */
	public Score() {
		evolution = new ArrayList<Integer>();
	}
	
	/**
	 * Get the evolution of the score
	 * 
	 * @return evolution
	 */
	public ArrayList<Integer> getEvolution() {
		return evolution;
	}
	

}
