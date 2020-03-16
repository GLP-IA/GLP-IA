package process;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import data.Character;
import data.Form;
import data.Hole;
import data.Node;
import data.AStarPara;

public class A_StarCore {
	private Character character;
	private Map map;
	private Form form;
	
	private Queue<Node> openSet; // L'ensemble des noeuds decouverts qui peuvent avoir besoin d'etre (re)developpe
	private ArrayList<Node> closedSet;//l'ensemble des noeud correcte
	private List<Node> path;//chemin
	private Node current; //current position
	
	
	public A_StarCore(Map map, Character character) {
		this.map = map;
		this.character=character;
		
		//Generate a random form
		Random rand = new Random();
		int r=rand.nextInt(3);
		form=AStarPara.Forms[r];
		
		current=new Node(null, character.getCoordX(), character.getCoordY(), 0, 0);
		
		openSet= new PriorityQueue<Node>();
		closedSet= new ArrayList<Node>();
		path= new ArrayList<Node>();
		openSet.add(current);	// Initialement, seul le noeud de depart est connu.
	}
	
	/**
	 * Finds path to xEnd/yEnd or returns null
	 *
	 *@param hole is the target
	 * @return (List<Node> | null) the path
	 */
	public List<Node> findPath(Hole target) {
		closedSet.add(current);
		NodeOperation.addNeigborsToOpenList(current, map, openSet, closedSet, target);
		while (current.getX() != target.getCoordX() || current.getY() != target.getCoordY()) {
			if (openSet.isEmpty()) { // Nothing to examine
				return null;
				}
			current = openSet.remove(); // get first node (lowest f score) and remove it
			
			closedSet.add(current); // then add to the closedSet
	        NodeOperation.addNeigborsToOpenList(current, map, openSet, closedSet, target);
			debug(current,target);
		}
		
		//check if the form is corresponding to the hole
		if(target.getHoleType().equals(form.getFormType()))
			target.setAchieved(true);
		
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
	public void usePath(Node node) {
		//Moov the character
		character.setCoordX(node.getX());
		character.setCoordY(node.getY());
	}

	/**
	 * permet de tester en mode console le programme
	 * 
	 */
	public void debug(Node current, Hole hole) {
		System.out.println("Trous:"+hole.getHoleType()+"\tForme:"+form.getFormType());
		//System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
		System.out.println("current node: g="+current.getG()+" h="+current.getH());
		System.out.println("x="+current.getX()+" y="+current.getY());
		map.printMapA_Star();
	}
}
