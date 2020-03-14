package data;

import data.ElementVisitor;

/**
 * This class is composed of the element's specifications
 * 
 * @author gimardthibault, Nathan VIRAYIE
 *
 */
public abstract class Element {
	private int reward;
	
	/**
	 * The constructor's class
	 * @param reward obtain when the character is on the case
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
	
	/**
	 * A element can accept the visit of any {@link ElementVisitor}.
	 * 
	 * @param visitor
	 *            the element visitor of generic type
	 * @return the generic return type that depends on the concret return type
	 *         of the element visitor
	 * 
	 */
	 public abstract <E> E accept(ElementVisitor<E> elem);
}
