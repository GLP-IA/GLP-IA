package process;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import data.Character;
import data.Form;
import data.Hole;
import data.Node;
import data.AStarPara;

public class A_StarCore {
	private Map map;
	private Form form;
	
	//public int countMoov=0;
	
	private Queue<Node> openSet; // L'ensemble des noeuds decouverts qui peuvent avoir besoin d'etre (re)developpe
	private ArrayList<Node> closedSet;//l'ensemble des noeud correcte
	private Node current; //current position
	
	
	public A_StarCore(Map map) {
		this.map = map;
		
		//Generate a random form
		Random rand = new Random();
		int r=rand.nextInt(3);
		form=AStarPara.Forms[r];
		
		current=new Node(null, AStarPara.xStart, AStarPara.yStart, 0, 0);
		
		openSet= new PriorityQueue<Node>();
		closedSet= new ArrayList<Node>();
		openSet.add(current);	// Initialement, seul le noeud de depart est connu.
	}
	
	/**
	 * Finds path to xEnd/yEnd or returns null
	 *
	 * @param hole is the target
	 * @return (List<Node> | null) the path
	 */
	public ArrayList<Node> findPath(Hole target) {
		ArrayList<Node> path= new ArrayList<Node>();
		closedSet.add(current);
		NodeOperation.addNeigborsToOpenList(current, map, openSet, closedSet, target);
		while (current.getX() != target.getCoordX() || current.getY() != target.getCoordY()) {
			if (openSet.isEmpty()) { // Nothing to examine
				return null;
			}
			
			// get first node (lowest f score) and remove it
			current = openSet.remove(); 
			
			closedSet.add(current); // then add to the closedSet
	        NodeOperation.addNeigborsToOpenList(current, map, openSet, closedSet, target);
			debug(current,target);
		}
		
		check(target);
		
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
		//Moov the character
		character.setCoordX(node.getX());
		character.setCoordY(node.getY());
	}

	/**
	 * remet à zero les elements necessaire pour redemarrer le programme
	 */
	public void reset(Character character) {
		closedSet= new ArrayList<Node>();
		openSet=new PriorityQueue<Node>();
		openSet.add(current);
		
		character.setCoordX(0);
		character.setCoordY(0);
		
		for(int i=0;i<AStarPara.Target.length;i++)
			AStarPara.Target[i].setAchieved(false);
	}

	/**
	 * check if the form is corresponding to the hole
	 * 
	 * @param target the hole to which the form must correspond
	 */
	public void check(Hole target) {
		if(current.getX() == target.getCoordX() && current.getY() == target.getCoordY()) {
			System.out.println("U have find the goal");
			if(target.getHoleType().equals(form.getFormType())) {
				target.setAchieved(true);
				System.out.println("the form match with the hole");
			}
			else
				System.out.println("the form don't match with the hole, try another one");
		}
	}

	
	/**
	 * permet de tester en mode console le programme
	 * 
	 */
	public void debug(Node current, Hole hole) {
		System.out.println("Trous:"+hole.getHoleType()+"\tForme:"+form.getFormType());
		System.out.println("current node: g="+current.getG()+" h="+current.getH());
		System.out.println("xNode="+current.getX()+" yNode="+current.getY()+"\txHole="+hole.getCoordX()+" yHole="+hole.getCoordY());
		//map.printMapA_Star();
	}
}
