package data;

import process.NodeOperation;

/**
 * Represents the case on the map with some parameters
 * 
 * @author Nathan VIRAYIE
 */
public class Node implements Comparable<Node>{
    private Node parent;
    private int x;
    private int y;
    private double g;
    private double h;
    
    public Node(Node parent, int xpos, int ypos, double g, double h) {
        this.parent = parent;
        this.x = xpos;
        this.y = ypos;
        this.g = g;
        this.h = h;
   }

	public Node getParent() {
		return parent;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getG() {
		return g;
	}

	public double getH() {
		return h;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setG(double g) {
		this.g = g;
	}

	public void setH(double h) {
		this.h = h;
	}

	public int compareTo(Node b) {
		 return (int)(NodeOperation.calcF(this) - NodeOperation.calcF(b));
	}
    
   
}