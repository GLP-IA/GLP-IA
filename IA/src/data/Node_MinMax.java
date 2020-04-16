package data;



//import data.Player;


public class Node_MinMax {
	
    private int nbOfCoins;
    private boolean isMaxPlayer; 
    //private boolean secondPlayer = !isMaxPlayer;
    private int utility; // utility correspond au score des noeuds
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
   /*
    public ArrayList<Node_MinMax> getChildren() {
        return children;
    }

    public void addChild(Node_MinMax newNode) {
        children.add(newNode);
    }
    */
   
   /*
   public int rightChild () {
	  return middleChild()+1;
   }
   
   public int middleChild () {
		  return leftChild()+1;
   }
   
   public int leftChild () {
	   if (nbOfCoins == 0)
		  return index+1;
	   else 
		   return (3*index)+1;
   }
   */
   public int getIndex () {
		return index;
   }
	
}
	
	

