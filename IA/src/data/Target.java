package data;

/**
 * The class Target permits to define where the robots have to go.
 * 
 * @author gimardthibault, Nathan Virayie
 *
 */
public class Target extends Element{
	/**
	 * Define the achievement
	 * 
	 * @see Target#isAchieved()
	 * @see Target#setAchieved(boolean)
	 */
	private boolean achieved;

	/**
	 * The constructor's class
	 * 
	 * @param coordX
	 * @param coordY
	 * @param achieved
	 * 
	 * @see Target#achieved
	 */
	public Target(int coordX, int coordY, int reward, boolean achieved) {
		super(coordX, coordY, reward);
		this.achieved = achieved;
	}

	/**
	 * Explain if the the character's achievement
	 * 
	 * @return the achievement
	 */
	public boolean isAchieved() {
		return achieved;
	}

	/**
	 * Update the achievement
	 * 
	 * @param achieved
	 */
	public void setAchieved(boolean achieved) {
		this.achieved = achieved;
	}	
}
