package process;

import data.Node_MinMax;
import data.Tree;



public class GameMachine {
	private Tree tree;
	private int nbOfCoins;
	
	public GameMachine() {
		nbOfCoins = 10;
		tree = new Tree();
		 Node_MinMax root = new Node_MinMax(nbOfCoins, true);
	     tree.setRoot(root);
	     TreeBuilder.constructTree(root);
	}

	public int getNbOfCoins() {
		return nbOfCoins;
	}

	
	/**
	 * Définit les règles du jeu 
	 */
	public void Round() {
	
	}


}

