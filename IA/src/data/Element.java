package data;
/**
 * This class is composed of the element's specifications
 * 
 * @author gimardthibault, Nathan Virayie
 *
 */
public class Element {
	/**
	 * The position in the x-axis 
	 * 
	 * @see Element#getCoordX()
	 * @see Element#setCoordX(int coordX)
	 * 
	 */
	private int coordX;
	/**
	 * The position in the y-axis 
	 * 
	 * @see Element#getCoordY()
	 * @see Element#setCoordY(int coordY)
	 * 
	 */
	private int coordY;
	
	private int reward;
	/**
	 * The constructor's class
	 * 
	 * @param coordX
	 * @param coordY
	 * 
	 * @see Element#coordX
	 * @see Element#coordY
	 */
	public Element(int coordX, int coordY, int reward) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.reward=reward;
	}

	/**
	 * Get the position in the x-axis
	 * 
	 * @return coordX
	 */
	public int getCoordX() {
		return coordX;
	}

	/**
	 * Get the position in the y-axis
	 * 
	 * @return coordY
	 */
	public int getCoordY() {
		return coordY;
	}

	/**
	 * Update the coordX
	 * 
	 * @param coordX
	 */
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	/**
	 * Update the coordY
	 * 
	 * @param coordY
	 */
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}
}
