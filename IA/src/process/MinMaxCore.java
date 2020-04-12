package process;

import java.util.ArrayList;
import java.util.Iterator;

import data.Node_MinMax;
import data.Tree;

public class MinMaxCore {
	
	private Tree tree;
	private int nbOfCoins;
	
	public MinMaxCore() {
		nbOfCoins = 10;
		tree = new Tree();
		Node_MinMax root = new Node_MinMax(nbOfCoins, true, 0, 0);
	    tree.setRoot(root);
	    TreeBuilder.constructTree(tree.getTree());
	     
	}
	
	
	public boolean checkWin( Node_MinMax root) {
        return root.getUtility() == 1;
    }

    public void setWinner(Tree tree) {
        ArrayList<Node_MinMax> children = tree.getTree();
        
        for (Iterator<Node_MinMax> child = children.iterator() ; child.hasNext() ; ){
            if (child.next().getNbOfCoins() == 0) {
            	if (child.next().isMaxPlayer()) 
            		child.next().setUtility(-1);
            	else  
            		child.next().setUtility(1);
            }
            
        }
        propagation();
    }
    
    public void propagation() {
    	Node_MinMax parent;
    	int indexParent;
    	for (int i=tree.getTree().size(); i >= 0; i--) {
    		indexParent = tree.getTree().get(i).getParent();
    		parent = tree.getTree().get(indexParent);
    		parent.setUtility(tree.getTree().get(i).getUtility() + parent.getUtility());
    	}
    }
    
    
    public Node_MinMax findBestChild(ArrayList<Node_MinMax> children, boolean isMaxPlayer) {
    	int max = children.get(0).getUtility();
    	int min = children.get(0).getUtility();
    	Node_MinMax bestChild = children.get(0);
    	for (Iterator<Node_MinMax> child = children.iterator() ; child.hasNext() ; ){
    	  if (child.next().getUtility() > max && isMaxPlayer) {
    		  max = child.next().getUtility();
    		  bestChild = child.next();
    	  } else if (child.next().getUtility() < min && !isMaxPlayer) {
    		  min = child.next().getUtility();
    		  bestChild = child.next();
    	  }
      }
        
        return bestChild;
    }

  //mettre condition de fin : lorqu'un des joueurs ne peut plus prendre de coins
    boolean isLeaf() {
        return nbOfCoins == 0;
    }
     
    
    
}
