package test;

import java.util.ArrayList;
import java.util.Iterator;

import data.AStarPara;
import data.Character;
import data.Node;
import process.A_StarCore;
import process.Map;

public class TestA_Star {
	
	public static void main(String[] args) {
		Character character = new Character(0,0);
		Map map = new Map(character); 
		
		map.initMapA_Star(AStarPara.Target[0],AStarPara.Target[1],AStarPara.Target[2]); //initialise 
		
		A_StarCore as = new A_StarCore(map);
		
		ArrayList<Node> pathHole = null;
		int i=0;
		
	    while(i<AStarPara.Target.length) {
			pathHole = as.findPath(AStarPara.Target[i]);
			
			///using the path to moov the character
			if(AStarPara.Target[i].isAchieved()) {
				Iterator<Node> it;
				Node node;
				for(it=pathHole.iterator();it.hasNext();) {
					node=it.next();
					as.usePath(character,node);
					map.printMapA_Star();
				}
				break;
			}
			else {
				i++;
				as.reset(character);
				pathHole=null;
			}
	    }
	}
}
