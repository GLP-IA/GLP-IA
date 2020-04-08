package data;

public class GoodBox extends Trail{

	public GoodBox(int x, int y) {
		super(x, y);
	}

	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
