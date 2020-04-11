package data;

public class AnalyzedBox extends Trail{

	public AnalyzedBox(int x, int y) {
		super(x, y);
	}

	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
