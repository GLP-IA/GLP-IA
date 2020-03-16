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
import data.QLearningPara;
import data.Score;
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
		
		current=new Node(null,character.getCoordX(),character.getCoordY(),AStarPara.g,5);
		
		openSet= new PriorityQueue<Node>();
		closedSet= new ArrayList<Node>();
		openSet.add(current);	// Initialement, seul le noeud de depart est connu.
	}
	
	/**
	 * Finds path to xEnd/yEnd or returns null
	 *
	 *@param hole is the target
	 * @return (List<Node> | null) the path
	 */
	public List<Node> findPath(Hole hole) {
		closedSet.add(current);
		NodeOperation.addNeigborsToOpenList(current, map, openSet, closedSet, hole);
		while (current.getX() != hole.getCoordX() || current.getY() != hole.getCoordY()) {
			if (openSet.isEmpty()) { // Nothing to examine
				return null;
				}
			current = openSet.remove(); // get first node (lowest f score) and remove it
			
			//Moov the character
			character.setCoordX(current.getX());
			character.setCoordY(current.getY());
			
			closedSet.add(this.current); // then add to the closedSet
	        NodeOperation.addNeigborsToOpenList(current, map, openSet, closedSet, hole);
			debug(hole);
		}
		
		//check if the form is corresponding to the hole
		if(hole.getHoleType().equals(form.getFormType()))
			hole.setAchieved(true);
		
		path.add(0, this.current);
	        
		while (current.getX() != AStarPara.xStart || current.getY() !=AStarPara.yStart) {
			current = current.getParent();
			path.add(0, current);
		}
		return path;
	}
	
	/**
	 * permet de tester en mode console le programme
	 * 
	 */
	public void debug(Hole hole) {
		System.out.println("Trous:"+hole.getHoleType()+"\tForme:"+form.getFormType());
		System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
		map.printMapA_Star();
	}
}
