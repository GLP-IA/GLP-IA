package data;
/**
 * The class Obstacle extends Element.
 * Obstacles should not be touched by the AI
 * 
 * @author gimardthibault, Nathan VIRAYIE
 *
 */
public class Obstacle extends Element {
	/**
	 * The type of the obstacle
	 * 
	 * @see Obstacle#Obstacle(int, int, String)
	 * @see Obstacle#getType()
	 * @see Obstacle#setType(String)
	 */
	private String type; //mettre un  booléan "à heurté" plutot

	/**
	 * The constructor's class
	 * 
	 * @param coordX
	 * @param coordY
	 * @param type
	 * 
	 * @see Obstacle#type
	 */
	public Obstacle(int malus, String type) {
		super(malus);
		this.type = type;
	}

	/**
	 * Get the type of the obstacle
	 * 
	 * @return the type of the obstacle
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Update the type of obstacle
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
