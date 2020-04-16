package test;

import java.util.Iterator;

import data.Node_MinMax;
import process.GameMachine;
import process.MinMaxCore;



public class TestMinMax {

	public static void main(String[] args) {
		GameMachine game = new GameMachine();
		MinMaxCore minmaxCore= game.getMinmaxCore();
		 
		 
		 for (Iterator<Node_MinMax> it = minmaxCore.getTreeCore().iterator(); it.hasNext();){
		    	Node_MinMax node = it.next();
		    	System.out.println(" n°" +node.getIndex() + " nbOfCoins: " +node.getNbOfCoins() + " parent : " + node.getParent() +" isMaxPlayer : "
		    	+ node.isMaxPlayer() + " utility : " + node.getUtility() 
		    	+ " isLeaf : " + minmaxCore.isLeaf(node) + " checkWin : " + minmaxCore.checkWin(node));
		 }
		 int i = 0;
		Node_MinMax bestChild = minmaxCore.findBestChild(minmaxCore.getTreeCore().get(i));
		 System.out.println("node : " + i +" his bestChild index : " + bestChild.getIndex() + " nbOfCoins: " + bestChild.getNbOfCoins());
		
			game.Round(2);
		
	}
	
	
}
