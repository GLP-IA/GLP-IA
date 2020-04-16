package process;




import java.util.ArrayList;
import java.util.Iterator;

import data.MinMaxPara;
import data.Node_MinMax;
import data.Tree;


public class MinMaxCore {
	
	private Tree tree;
	private int nbOfCoins;
	
	public MinMaxCore() {
		tree = new Tree();
		nbOfCoins = MinMaxPara.nbOfCoins;
		Node_MinMax root = new Node_MinMax(nbOfCoins, false, 0, 0, 0);
	    tree.setRoot(root);
	    TreeBuilder.constructTree(tree);    
	     
	}
	
	
	public boolean checkWin(Node_MinMax root) {
        return root.getUtility() == 1 && root.isLeaf();
    }

   	
	public void setWinner() {
        Node_MinMax node;
      	for (Iterator<Node_MinMax> it = tree.getTree().iterator(); it.hasNext();){
      		node= it.next();
            if (node.getNbOfCoins() == 0) {
            	
            	if (node.isMaxPlayer()) { 
            		node.setUtility(1);
            		
            	}else { 
            		node.setUtility(-1);
            		
            	}
            }
            
        }
        propagation();
    }
	
	public void propagation() {
    	Node_MinMax parent;
    	int indexParent;
    	 for (int index=tree.getTree().size()-1; index >= 0; index--){
    		indexParent = tree.getTree().get(index).getParent();
    		parent = tree.getTree().get(indexParent);
    		parent.setUtility(tree.getTree().get(index).getUtility() + parent.getUtility());
    	}
    }
    	
  public ArrayList<Node_MinMax> getTreeCore() {
		return tree.getTree();
	}


	public int getNbOfCoinsCore() {
		return nbOfCoins;
	}
	
	public Node_MinMax secondPlayerTurn (int choice, Node_MinMax nodeMinMax) {
		
		int result = nodeMinMax.getNbOfCoins() - choice;
		Node_MinMax node;
		// parcourir et chercher dans l 'ArrayList un noeud = result et qui a pour parent l'index du noeud  passer en parametre
		if(result < 0)
			result = 0;
		for (Iterator<Node_MinMax> it = tree.getTree().iterator(); it.hasNext();){
			node = it.next();
        	if (node.getNbOfCoins() == result && node.getParent() == nodeMinMax.getIndex() ) 
        		nodeMinMax = node;	
		}
	
		return  nodeMinMax;//ici
		
	}

	public Node_MinMax findBestChild( Node_MinMax nodeMinMax) {
		Node_MinMax node;
    	int valueleftChild= nodeMinMax.getNbOfCoins() - 1;//valeur du filsG
    	Node_MinMax nodeleft = null;
    	// parcourir et chercher dans l 'ArrayList un noeud = result et qui a pour parent l'index du noeud  passer en parametre 
    	
    	for (Iterator<Node_MinMax> it = tree.getTree().iterator(); it.hasNext();){
        	node = it.next();
        	if (node.getNbOfCoins() == valueleftChild && node.getParent() == nodeMinMax.getIndex() ) {
        		nodeleft = node;
        		break;
        	}
    	}	    	
    	Node_MinMax bestChild = nodeleft;   
    	int max = bestChild.getUtility();
    	 for (int i= nodeleft.getIndex(); i <= nodeleft.getIndex()+2; i++){ //
    		 if (tree.getTree().get(i).getUtility() > max) {
    			 max = tree.getTree().get(i).getUtility();
	    		  bestChild = tree.getTree().get(i);
	    	  } 
    	 }
        
        return bestChild;
    }
}
