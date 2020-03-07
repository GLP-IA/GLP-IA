package process;

import java.util.ArrayList;

import data.Character;
import data.Hole;
import data.Score;

public class A_StarCore {
	private Character character;
	private Map map;
	private static final int dimMap =100;
	
	private Hole[] holes; //contiendra les références des trous(objectif) placer sur la carte
	private int g; //cout du déplacement
	private int h; // h est la fonction heuristique. h (n) estime le coût pour atteindre l'objectif à partir du nœud n.
	private int f; //f=g+h
	
	//on pourrais mettre  une fil de priorite (voir Queue)
	private ArrayList<Integer> openSet; // L'ensemble des nœuds découverts qui peuvent avoir besoin d'être (re) développés. 
	
	public A_StarCore(int g, int h, int f, Map map) {
		this.g = g;
		this.h = h;
		this.f = f;
		this.map = map;
		
		//initialisation du personnage
		character = new Character(0,0,new Score());
		
		holes = new Hole [3]; //il y aura min. 3 trou avec différentes formes
		openSet.add(0);	// Initialement, seul le nœud de départ est connu. (on utilisera la meme notation que les etat du qlearning)
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
	 *fonction qui calculera la distance selon 1 vol d'oiseau ou ligne droite
	 *
	 */
	public void calcH() {
		//calcul
		
		calcF();// une fois le calcul de H mis à jour il faudrai mettre à jour celui de F aussi (normalement)
	}

	/**
	 * calcul f selon g et h
	 */
	public void calcF() {
		f=g+h;
	}
	
	
}
