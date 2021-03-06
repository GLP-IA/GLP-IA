package data;

import java.util.ArrayList;


public class Tree {

	private ArrayList<Node_MinMax> tree ;
	
	    public Tree() {
	    	tree = new ArrayList<Node_MinMax>();
	    }

	    public ArrayList<Node_MinMax> getTree() {
	        return tree;
	    }

		public void setRoot(Node_MinMax root) {
			this.tree.add(0,root);
		}
		
		public void addNode(Node_MinMax node) {
			this.tree.add(node);
		}
	   	
		public Node_MinMax getParent(Node_MinMax node) {
			return tree.get(node.getParent());
		}
	    
	 
}

