package data;

/**
 * This class is composed of the character's specifications
 *
 * @author gimardthibault,nathan,aymen
 *
 */

public class Character {
	/**
	 * The character's position
	 * 
	 * @see getPosition()
	 * @see setPosition(Element position)
	 */
	private int coordx;
	private int coordY;
	
	
	/**
	 * The constructor's class
	 * 
	 * @param position
	 * 
	 * @see Character#position
	 */
	public Character(int coordx, int coordY) {
		super();
		this.coordx = coordx;
		this.coordY = coordY;
	}
	
	/**
	 * Update the character's position
	 * 
	 * @param position
	 */
	public int getCoordX() {
		return coordx;
	}

	public void setCoordX(int coordx) {
		this.coordx = coordx;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
