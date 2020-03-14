package process;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import data.Character;
import data.Hole;
import data.Node;
import data.Score;
import data.AStarPara;

public class A_StarCore {
	private Character character;
	private Map map;
	
	private Hole[] holes; //contiendra les references des trous(objectif) placer sur la carte
	
	private Queue<Node> openSet; // L'ensemble des noeuds decouverts qui peuvent avoir besoin d'etre (re)developpe
	private List<Node> closedSet;//l'ensemble des noeud correcte
	private List<Node> path;//chemin
	private Node current; //current position
	
	/*private final int xstart;
	private final int ystart;
	private final boolean diag;*/
	
	
	public A_StarCore(Map map, Character character) {
		this.map = map;
		this.character=character;
		
		holes = new Hole [3]; //il y aura min. 3 trou avec diff�rentes formes
		
		current=new Node(null,character.getCoordX(),character.getCoordY(),AStarPara.g,5);
		openSet= new PriorityQueue<Node>();
		closedSet= new ArrayList<Node>();
		openSet.add(current);	// Initialement, seul le n�ud de d�part est connu.
	}

	/**
	 * Place les trous connu dans le tableau
	 * 
	 * @param hole1
	 * @param hole2
	 * @param hole3
	 */
	public void initHoles(Hole hole1, Hole hole2, Hole hole3) {
		holes[0]=(Hole) map.getCase(6,5);
		holes[1]=(Hole) map.getCase(5,3);
		holes[2]=(Hole) map.getCase(2,4);
	}
	
	/**
	 *fonction qui calcul h de maniere heuristique selon le principe de la distance Manhattan
	 *
	 */
	public double calcH(Node pos) {
		return Math.abs(pos.getX() - holes[0].getCoordX()) + Math.abs(pos.getY() - holes[0].getCoordY());
	}

	/**
	 * calcul f selon g et h
	 */
	public double calcF(Node a) {
		return(a.getG()+calcH(a));
	}
	
	public int compareNode(Node a, Node b) {
	   return (int)(calcF(a) - calcF(b));
	}
	
	/**
	 * Finds path to xEnd/yEnd or returns null
	 *
	 * @param xEnd coordinates of the target position
	 * @param yEnd
	 * @return (List<Node> | null) the path
	 */
	public List<Node> findPath(int xEnd, int yEnd) {
		closedSet.add(current);
	      //addNeigborsToOpenList();
		while (current.getX() != xEnd || current.getY() != yEnd) {
			if (openSet.isEmpty()) { // Nothing to examine
				return null;
				}
			current = openSet.remove(); // get first node (lowest f score) and remove it
			closedSet.add(this.current); // then add to the closedSet
	           //addNeigborsToOpenList();
		}
		path.add(0, this.current);
	        
		while (current.getX() != AStarPara.xStart || current.getY() !=AStarPara.yStart) {
			current = current.getParent();
			path.add(0, current);
		}
		return path;
	}
}
