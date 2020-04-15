package process;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import data.AStarPara;
import data.AnalyzedBox;
import data.Hole;
import data.Node;
import data.PathAstar;
import data.WrongBox;

public class NodeOperation {

	/**
	 * add the side nodes to the list 
	 * 
	 * @param current the current node
	 * @param map 
	 * @param openSet
	 * @param closedSet
	 * @param target
	 */
	   public static void addNeigbors(Node current, Map map, Queue<Node> openSet, ArrayList<Node> closedSet, Hole target, PathAstar p) {
	        Node node;
	        for (int x = -1; x <= 1; x++) {
	            for (int y = -1; y <= 1; y++) {
	                node = new Node(current, current.getX()+ x, current.getY() + y, current.getG(), 0);
	                if ((Math.abs(x)!=Math.abs(y)) // not currrent and not the diagonal
	                    && current.getX() + x >= 0 && current.getX() + x < map.getWidth() // check map boundaries
	                    && current.getY() + y >= 0 && current.getY() + y < map.getHeight()
	                    && (map.getCase(current.getX() + x, current.getY() + y).getReward() != AStarPara.MALUS)// check if square is walkable
	                    && !findNeighborInList(openSet, node) && !findNeighborInList(closedSet, node)) { // if not already done
	                        node.setG(node.getParent().getG() + 1); //add movement cost
	                        node.setH(calcH(node,target));	//calculate the distance to the target
	                        if(node.getH()<current.getH()) {
		                        openSet.add(node);
		                        p.addToPath(new AnalyzedBox(node.getX(),node.getY()));
	                        }
	                        else {
	                        	p.addToPath(new WrongBox(node.getX(),node.getY()));
	                        }
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
		public static Integer calcF(Node a) {
			return (int)(a.getG()+a.getH());
		}
}
