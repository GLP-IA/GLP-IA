package data;
 /**
  * all the parameters for Astar
  * 
  * @author Nathan VIRAYIE
  *
  */
public class AStarPara {
	public static final int DIM_MAP =100;
	
	
	public static final int xStart=0;
	public static final int yStart=0;

	public static final Form[] Forms = {new Form("Triangle"),new Form("Square"),new Form("Circle")};
	public static final Hole[] Target = {new Hole(3,6,"Triangle"), new Hole(4,2,"Square"), new Hole(8,7,"Circle")};

	public static final int EMPTY_REWARD = 1; //cost of the movement
	
	
	/**
	 * define which option should be run in the run() method
	 * 
	 */
	public static boolean runAStar=false;
}
