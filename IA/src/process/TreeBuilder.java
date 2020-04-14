package process;


import data.Node_MinMax;
import data.Tree;

public class TreeBuilder {
	
    public static void constructTree(Tree tree) {    
    	Node_MinMax newNode;
        int cpt = 1;
        Node_MinMax node;
        for (int index=0; index >= 0 && index+1!= tree.getTree().size()-1; index++){
        	node=tree.getTree().get(index);
        	if (node.getNbOfCoins() > 0) {
        		for (int i =1; i<4; i++) {
        			if(node.getNbOfCoins() - i <=0)
        				newNode = new Node_MinMax(0, !node.isMaxPlayer(), ((cpt-1)/3), 0);// créér un noeud pr le 2eme joueur
        			else
        				newNode = new Node_MinMax((node.getNbOfCoins() - i) , !node.isMaxPlayer(), ((cpt-1)/3), 0);// créér un noeud pr le 2eme joueur
        			tree.addNode(newNode);
        			cpt++;
        		}
        	}
        }  
    }
    
}