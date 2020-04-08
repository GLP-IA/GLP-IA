package data;

import java.util.ArrayList;



public class Node_MinMax {
	
    private int nbOfCoins;
    private boolean isMaxPlayer;
    private int utility; // utility correspond au score des noeuds
    private ArrayList<Node_MinMax> children;
   

    public Node_MinMax(int nbOfCoins, boolean isMaxPlayer) {
        this.nbOfCoins = nbOfCoins;
        this.isMaxPlayer = isMaxPlayer;
        children = new ArrayList<Node_MinMax>();
    }

    public int getNbOfCoins() {
        return nbOfCoins;
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }
    
    int getUtility() {
        return utility;
    }

    void setUtility(int utility) {
        this.utility = utility;
    }

    ArrayList<Node_MinMax> getChildren() {
        return children;
    }

    public void addChild(Node_MinMax newNode) {
        children.add(newNode);
    }
    
}
	
	

