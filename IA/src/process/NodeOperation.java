package process;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import data.Hole;
import data.Node;

public class NodeOperation {

	   public static void addNeigborsToOpenList(Node current, Map map, Queue<Node> openSet, ArrayList<Node> closedSet, Hole target) {
	        Node node;
	        for (int x = -1; x <= 1; x++) {
	            for (int y = -1; y <= 1; y++) {
	                node = new Node(current, current.getX()+ x, current.getY() + y, current.getG(), calcH(current,target));
	                if ((x != 0 || y != 0) // not currrent
	                    && current.getX() + x >= 0 && current.getX() + x < map.getWidth() // check map boundaries
	                    && current.getY() + y >= 0 && current.getY() + y < map.getHeight()
	                    && map.getCase(current.getY() + y, current.getX() + x).getReward() != -500 // check if square is walkable
	                    && !findNeighborInList(openSet, node) && !findNeighborInList(closedSet, node)) { // if not already done
	                        node.g = node.parent.g + 1; 
	                        node.g += map.getCase(current.getY() + y, current.getX() + x).getReward(); // add movement cost for this square
	                        openSet.add(node);
	                }
	            }
	        }
	    }
	   
	   /**
	    * Looks in a given List<> for a node
	    *
	    * @return (bool) NeightborInListFound
	    */
	    public static boolean findNeighborInList(Collection<Node> array, Node node) {
	    	Iterator<Node> it;
			Node compare;
			for(it=array.iterator();it.hasNext();) {
				compare=it.next();
		    	if(node.getX()==compare.getX() && node.getY()==compare.getY())
		    		return true;
			}
			return false;
	    }
	    
		/**
		 *fonction qui calcul h de maniere heuristique selon le principe de la distance Manhattan
		 *
		 */
		public static double calcH(Node pos, Hole hole) {
			return Math.abs(pos.getX() - hole.getCoordX()) + Math.abs(pos.getY() - hole.getCoordY());
		}

		/**
		 * calcul f selon g et h
		 */
		public static double calcF(Node a) {
			return(a.getG()+a.getH());
		}
}
