package process;

import java.util.ArrayList;
import java.util.Iterator;

import data.Node_MinMax;

public class TreeBuilder {
	
    public static void constructTree(ArrayList<Node_MinMax> root) {    
        Node_MinMax newNode;
        int cpt = 0;
        for (Iterator<Node_MinMax> it = root.iterator(); it.hasNext();){
        	if (it.next().getNbOfCoins() > 0) {
        		for (int i =1; i<4; i++) {
        			newNode = new Node_MinMax((it.next().getNbOfCoins() - i) , !it.next().isMaxPlayer(), ((cpt-1)/3), 0);// créér un noeud pr le 2eme joueur
        			root.add(newNode);
        			cpt++;
        			System.out.println("ok boomer");
        		}
        	
        		System.out.println("truc plus visible ");
        	}
        	else {
        		System.out.println("4 eme texte");
        	}
        }
        
    }
}