package process;

import java.util.ArrayList;
import java.util.Iterator;

import data.MinMaxPara;
import data.Node_MinMax;
import data.Tree;
/**
 * Contains all the specifics of the minmax core
 * 
 * @author Nathan Virayie
 */

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
	
	/**
	 * Verify if the node is a winning option for minmax
	 * 
	 * @param node to verify
	 * @return true if minmax win / false if it's not a leaf or the opponent win
	 */
	public boolean checkWin(Node_MinMax node) {
        return (node.getUtility() == 1 && node.isLeaf());
    }

   	/**
   	 * Distributes positive utility on the winning nodes and a negative one on the others
   	 * 
   	 */
	public void evaluate() {
        Node_MinMax node;
      	for (Iterator<Node_MinMax> it = tree.getTree().iterator(); it.hasNext();){
      		node= it.next();
            if (node.getNbOfCoins() == 0) {
            	if (node.isMaxPlayer())
            		node.setUtility(1);

            	else
            		node.setUtility(-1);
            }   
        }
        propagation();
    }
	
	/**
	 * Spreads the utilities from the leaf to the roots of the tree
	 */
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
		
		if(result <= 0)
			result = 0;
		
		for (Iterator<Node_MinMax> it = tree.getTree().iterator(); it.hasNext();){
			node = it.next();
        	if (node.getNbOfCoins() == result && node.getParent() == nodeMinMax.getIndex() ) 
        		nodeMinMax = node;	
		}
		return  nodeMinMax;
	}

	/**
	 * Look for the best option from the current node
	 * 
	 * @param nodeMinMax the current node
	 * @return the child node with the best utility
	 */
	public Node_MinMax findBestChild( Node_MinMax nodeMinMax) {
		Node_MinMax node;
    	int valueleftChild= nodeMinMax.getNbOfCoins() - 1;
    	Node_MinMax nodeleft = null;
    	Node_MinMax bestChild;
    	
    	for (Iterator<Node_MinMax> it = tree.getTree().iterator(); it.hasNext();){
        	node = it.next();
        	if (node.getNbOfCoins() == valueleftChild && node.getParent() == nodeMinMax.getIndex() ) {
        		nodeleft = node;
        		break;
        	}
    	}
    	
    	bestChild = nodeleft;   
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
