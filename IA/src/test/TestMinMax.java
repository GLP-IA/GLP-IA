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
	    ArrayList<Node_MinMax> arbre = tree.getTree();
	    TreeBuilder.constructTree(arbre);
	    
	    for (Iterator<Node_MinMax> it = tree.getTree().iterator(); it.hasNext();){
	    System.out.println(it.next().getNbOfCoins());
	    }
	}

}
