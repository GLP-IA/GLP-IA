package data;
import java.util.ArrayList;


public class Map {
	private ArrayList<Element> map;
	private int dimension;
	
	
	public Map(ArrayList<Element> map, int dimension) {
		this.map = map;
		this.dimension = dimension;
	}


	public ArrayList<Element> getMap() {
		return map;
	}


	public int getDimension() {
		return dimension;
	}


}
