package data;

/**
 * The class Target permits to define where the robots have to go.
 * 
 * @author gimardthibault
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
	 * @param reward
	 * @param achieved
	 * 
	 * @see Target#achieved
	 */
	public Target(int reward, boolean achieved) {
		super(reward);
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
