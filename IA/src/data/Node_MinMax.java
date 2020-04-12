package data;

import java.util.Iterator;

//import data.Player;


public class Node_MinMax {
	
    private int nbOfCoins;
    private boolean isMaxPlayer; 
    //private boolean secondPlayer = !isMaxPlayer;
    private int utility; // utility correspond au score des noeuds
    private int parent;
   

    public Node_MinMax(int nbOfCoins, boolean isMaxPlayer, int parent, int utility) {
        this.nbOfCoins = nbOfCoins;
        this.isMaxPlayer = isMaxPlayer;
        this.parent = parent;
        this.utility = utility;

    }

    public int getNbOfCoins() {
        return nbOfCoins;
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }
    public int getParent() {
		return parent;
	}

	
    
    public int getUtility() {
        return utility;
    }
    

   public void setUtility(int utility) {
        this.utility = utility;
    }
   /*
    public ArrayList<Node_MinMax> getChildren() {
        return children;
    }

    public void addChild(Node_MinMax newNode) {
        children.add(newNode);
    }
    */

public Iterator<Node_MinMax> iterator() {
	// TODO Auto-generated method stub
	return null;
}
}
	
	

