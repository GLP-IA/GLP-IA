package process;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import data.Character;
import data.Form;
import data.GoodBox;
import data.Hole;
import data.Node;
import data.PathAstar;
import data.Score;
import data.AStarPara;

public class A_StarCore {
	private Map map;
	private  Form form;
	private PathAstar historic;
	private boolean uselessAnalyse;
	
	public Score score=new Score();
	
	private Queue<Node> openSet; // L'ensemble des noeuds decouverts qui peuvent avoir besoin d'etre (re)developpe
	private ArrayList<Node> closedSet;//l'ensemble des noeud correcte
	private Node current; //current position
	
	private ArrayList<Node> path;
	
	
	public A_StarCore(Map map) {
		this.map = map;
		
		uselessAnalyse =false;
		
		//Generate a random form
		Random rand = new Random();
		int r=rand.nextInt(3);
		form=AStarPara.Forms[r];
				
		current=new Node(null, AStarPara.xStart, AStarPara.yStart, 0, 0);
		
		openSet= new PriorityQueue<Node>(new Comparator<Node>(){
			public int compare(Node arg0, Node arg1) {
				return arg0.compareTo(arg1);
			}
		});
		closedSet= new ArrayList<Node>();
		path= new ArrayList<Node>();
		historic= new PathAstar();
	}
	
	/**
	 * Finds path to xEnd/yEnd or returns null
	 *
	 * @param hole is the target
	 * @return (Node | null) the path
	 */
	public Node findPath(Hole target) {
		if(form.getFormType().equals("Triangle") && !AStarPara.pathToTriangle.isEmpty()) {
			current=AStarPara.pathToTriangle.get(AStarPara.pathToTriangle.size()-1);
			check(target);
			uselessAnalyse=true;
			System.out.println("using saved path triangle");
			return current;
		}
		
		else if(form.getFormType().equals("Square") && !AStarPara.pathToSquare.isEmpty()) {
			current= AStarPara.pathToSquare.get(AStarPara.pathToSquare.size()-1);
			check(target);
			uselessAnalyse=true;
			System.out.println("using saved path square");
			return current;
		}
		
		else if(form.getFormType().equals("Circle") && !AStarPara.pathToCircle.isEmpty()) {
			current= AStarPara.pathToCircle.get(AStarPara.pathToCircle.size()-1);
			check(target);
			uselessAnalyse=true;
			System.out.println("using saved path circle");
			return current;
		}
		
		else {
			if (openSet.isEmpty() && AStarPara.firstLaunch) { // Nothing to examine
				current.setH(NodeOperation.calcH(current,target));
				openSet.add(current);	// Initialement, seul le noeud de depart est connu.
				AStarPara.firstLaunch=false;
			}
			else if(openSet.isEmpty() && !AStarPara.firstLaunch) {
				return null;
			}
				
			// get first node (lowest f score) and remove it
			current = openSet.remove();
				
			closedSet.add(current); // then add to the closedSet
			NodeOperation.addNeigbors(current, map, openSet, closedSet, target,historic);
				
			debug(current,target);
			return current;
		}
	}
	
	/**
	 * retrace the path by following the parent of the terminal node
	 * @return the complete path
	 */
	public ArrayList<Node> retracePath(){
		path.add(0,current);
		
		while (current.getX() != AStarPara.xStart || current.getY() !=AStarPara.yStart) {
			current = current.getParent();
			path.add(0, current);
		}
		
		return path;
	}
	
	/**
	 * moov the character by following the path
	 * 
	 * @param path
	 */
	public void usePath(Character character, Node node) {
		character.setCoordX(node.getX());
		character.setCoordY(node.getY());
		historic.addToPath(new GoodBox(node.getX(),node.getY()));
		
		//Save the path
		if(form.getFormType().equals("Triangle"))
			AStarPara.pathToTriangle.add(node);	
		if(form.getFormType().equals("Square"))
			AStarPara.pathToSquare.add(node);
		if(form.getFormType().equals("Circle"))
			AStarPara.pathToCircle.add(node);
	}
	
	/**
	 * count how many node have been analyzed
	 * @return the number of movement
	 */
	public int countMoovement() {
		return historic.getPath().size()-1;
	}

	/**
	 * remet à zero les elements necessaire pour redemarrer le programme
	 */
	public void reset() {
		closedSet= new ArrayList<Node>();
		openSet=new PriorityQueue<Node>();
		path=new ArrayList<Node>();
		uselessAnalyse=false;
		//openSet.add(current);
	}

	/**
	 * check if the form is corresponding to the hole
	 * 
	 * @param target the hole to which the form must correspond
	 */
	public boolean check(Hole target) {
		boolean reached = current.getX() == target.getCoordX() && current.getY() == target.getCoordY();
		if(reached) {
			System.out.println("U have find the goal");
			if(target.getHoleType().equals(form.getFormType())) {
				target.setAchieved(true);
				System.out.println("the form match with the hole");
			}
			else
				System.out.println("the form don't match with the hole, try another one");
		}
		return reached;
	}

	
	/**
	 * permet de tester en mode console le programme
	 * 
	 */
	public void debug(Node current, Hole hole) {
		System.out.println("H:"+current.getH()+" G:"+current.getG()+" F:"+NodeOperation.calcF(current));
		System.out.println("Trous:"+hole.getHoleType()+"\tForme:"+form.getFormType());
		//System.out.println("xNode="+current.getX()+" yNode="+current.getY()+"\txHole="+hole.getCoordX()+" yHole="+hole.getCoordY());
		//map.printMapA_Star();
	}

	public  Form getForm() {
		return form;
	}

	public PathAstar getHistoric() {
		return historic;
	}

	public boolean isUselessAnalyse() {
		return uselessAnalyse;
	}
}
