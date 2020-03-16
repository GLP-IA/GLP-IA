package data;
/**
 * The class Obstacle extends Element.
 * Obstacles should not be touched by the AI
 * 
 * @author gimardthibault, Nathan VIRAYIE
 *
 */
public class Obstacle extends Element {
	public Obstacle(int malus) {
		super(malus);
	}
	
	@Override
	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
