package process;

import data.Node_MinMax;

public class TreeBuilder {
	
    public static void constructTree(Node_MinMax parentNode) {    
        boolean isChildMaxPlayer = !parentNode.isMaxPlayer();
        for (int i =1; i<4; i++) {
        	Node_MinMax newNode = new Node_MinMax(parentNode.getNbOfCoins() - i , isChildMaxPlayer);
            parentNode.addChild(newNode);
            if (newNode.getNbOfCoins() > 0) {
                constructTree(newNode);
            }
        }
        
    }


}
