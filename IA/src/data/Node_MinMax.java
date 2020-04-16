package data;

public class Node_MinMax {
	
    private int nbOfCoins;
    private boolean isMaxPlayer; 
    private int utility; // utility correspond a l'evaluation du noeud
    private int parent;
    private int index;
   

    public Node_MinMax(int nbOfCoins, boolean isMaxPlayer, int parent, int index, int utility) {
        this.nbOfCoins = nbOfCoins;
        this.isMaxPlayer = isMaxPlayer;
        this.parent = parent;
        this.index = index;
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
   
   public boolean isLeaf() {
       return nbOfCoins == 0;
   }

   public int getIndex () {
		return index;
   }
	
}