package process;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import data.Character;
import data.Hole;
import data.Score;
import data.AStarPara;

public class A_StarCore {
	private Character character;
	private Map map;
	
	private Hole[] holes; //contiendra les r�f�rences des trous(objectif) placer sur la carte
	/*private final List<Node> closed;//l'ensemble des noeud correcte
	private final List<Node> path;//chemin
	private Node now; //current position
	private final int xstart;
	private final int ystart;
	private int xend, yend;
	private final boolean diag;*/
	
	private Queue<Node> openSet; // L'ensemble des n�uds d�couverts qui peuvent avoir besoin d'�tre (re) d�velopp�s. 
	
	public A_StarCore(Map map) {
		this.map = map;
		
		//initialisation du personnage
		character = new Character(0,0,new Score());
		
		holes = new Hole [3]; //il y aura min. 3 trou avec diff�rentes formes
		openSet.add(new Node(null,character.getCoordX(),character.getCoordY(),AStarPara.g,calcH()));	// Initialement, seul le n�ud de d�part est connu. (on utilisera la meme notation que les etat du qlearning)
	}

	/**
	 * Place les trous connu dans le tableau
	 * 
	 * @param hole1
	 * @param hole2
	 * @param hole3
	 */
	public void initHoles(Hole hole1, Hole hole2, Hole hole3) {
		holes[0]=hole1;
		holes[1]=hole2;
		holes[2]=hole3;
	}
	
	/**
	 *fonction qui calcul h de maniere heuristique selon le principe de la distance Manhattan
	 *
	 */
	public double calcH() {
		//
		return Math.abs(character.getCoordX() - holes[0].getCoordX()) + Math.abs(character.getCoordY() - holes[0].getCoordY());
	}

	/**
	 * calcul f selon g et h
	 */
	public double calcF() {
		return(1+calcH());
	}
	
	
}
