package data;

public class EmptyCase extends Element {

	public EmptyCase(int reward) {
		super(reward);
	}

	@Override
	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
