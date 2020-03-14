package test;

import java.util.Iterator;

import data.Character;
import data.EmptyCase;
import data.Node;
import process.A_StarCore;
import process.Map;
import data.Character;

public class TestA_Star {
	
	public static void main(String[] args) {
		Character character = new Character(0,0);
		Map map = new Map(character); 
		
		map.initMapA_Star();//initalise la carte
		A_StarCore as = new A_StarCore(map,character);
	    java.util.List<Node> path = as.findPath(6, 5);
	    
	        if (path != null) {
	           Node n;
	          Iterator<Node> it;//= path.iterator();
	           for(it=path.iterator();it.hasNext();) {
	        	   n=it.next();
	        	   map.setCase(n.getX(),n.getY(),new EmptyCase(250));
	           }
	        }
		map.printMapA_Star();
	}
}
