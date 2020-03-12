package test;

import java.awt.List;
import java.util.Iterator;

import data.Element;
import data.Node;
import process.A_StarCore;
import process.Map;

public class TestA_Star {
	
	public static void main(String[] args) {
		Map map = new Map(0,0); 
		
		map.initMapA_Star();//initalise la carte
		A_StarCore as = new A_StarCore(map);
	    java.util.List<Node> path = as.findPath(6, 5);
	    
	        if (path != null) {
	           Node n;
	          Iterator<Node> it;//= path.iterator();
	           for(it=path.iterator();it.hasNext();) {
	        	   n=it.next();
	        	   map.setCase(n.getX(),n.getY(),new Element(250));
	           }
	        }
		map.printMapA_Star();
	}
}
