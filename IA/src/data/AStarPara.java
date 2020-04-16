package data;

import java.util.ArrayList;

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
	public static final Hole[] Target = {new Hole(8,0,"Triangle"), new Hole(0,9,"Square"), new Hole(9,9,"Circle")};

	public static final int EMPTY_REWARD = 1; //cost of the movement
	public static final int MALUS = -1;
	
	/**
	 * Contains the founded path of the IA
	 */
	public static ArrayList<Node> pathToTriangle =new ArrayList<Node>();;
	public static ArrayList<Node> pathToSquare =new ArrayList<Node>();;
	public static ArrayList<Node> pathToCircle =new ArrayList<Node>();;
	
	public static boolean firstLaunch=true;
	
	/**
	 * define which option should be run in the run() method
	 * 
	 */
	public static boolean runAStar=false;
}
