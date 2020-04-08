package data;

public class WrongBox extends Trail{

	public WrongBox(int x, int y) {
		super(x, y);
	}

	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
