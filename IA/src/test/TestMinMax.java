package test;

import java.util.ArrayList;
import java.util.Iterator;

import data.Node_MinMax;
import data.Tree;
import process.TreeBuilder;

public class TestMinMax {

	public static void main(String[] args) {
		 Tree tree = new Tree();
		 int nbOfCoins = 10;
		Node_MinMax root = new Node_MinMax(nbOfCoins, true, 0,0);
	    tree.setRoot(root);
	    TreeBuilder.constructTree(tree);
	    
	    for (Iterator<Node_MinMax> it = tree.getTree().iterator(); it.hasNext();){
	    	Node_MinMax node =it.next();
	    	System.out.println(node.getNbOfCoins()+" parent: "+ node.getParent());
	    }
	}

}
