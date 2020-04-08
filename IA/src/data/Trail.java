package data;
import data.ElementVisitor;

public abstract class Trail{
	private int x;
	private int y;
	
	public Trail(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public abstract <E> E accept(ElementVisitor<E> elem);

}
