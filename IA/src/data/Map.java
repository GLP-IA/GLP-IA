package data;
import java.util.ArrayList;

/**
 * This class Map is environment's game
 * 
 * @author gimardthibault
 *
 */
public class Map {
	/**
	 * The list of elements in the map
	 * 
	 * @see Map#getMap()
	 * 
	 */
	private ArrayList<Element> map;
	
	/**
	 * The number of cells in the game (lines*columns)
	 * 
	 * @see Map#getDimension()
	 */
	private int dimension;
	
	/**
	 * The constructor's class
	 * 
	 * @param map
	 * @param dimension
	 */
	public Map(int dimension) {
		map = new ArrayList<Element>();
		this.dimension = dimension;
	}


	/**
	 * Get the map of the game
	 * 
	 * @return the map
	 * 
	 */
	public ArrayList<Element> getMap() {
		return map;
	}


	/**
	 * Get the dimension of the map
	 * 
	 * @return the Map's dimension
	 */
	public int getDimension() {
		return dimension;
	}


}
