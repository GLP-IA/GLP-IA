package data;
/**
 * This class is composed of the element's specifications
 * 
 * @author gimardthibault, Nathan Virayie
 *
 */
public class Element {
	private int reward;
	
	/**
	 * The constructor's class
	 * @param reward recompense obtenu en passant par l'element
	 */
	public Element(int reward) {
		this.reward=reward;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}
}
