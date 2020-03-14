package gui.management;

import java.awt.Graphics;


import data.ElementVisitor;
import data.EmptyCase;
import data.Hole;
import data.Obstacle;
import data.Target;
import process.Map;
import data.Character;
import data.Element;


public class DrawVisitor implements ElementVisitor<Void> {

	/**
	 * The color strategy used to define the colors of nodes and lines of the
	 * tree.
	 */
	private ImageStrategy imageStrategy = new ImageStrategy();
	
	private Graphics graphics;
	private int i=0;
	private int j=0;
	
	public DrawVisitor(Graphics graphics) {
		this.graphics = graphics;
	}

	public void setI(int i) {
		this.i=i;
	}
	
	public void setJ(int j) {
		this.j=j;
	}
	
	public Void visit(Character elem) {
		imageStrategy.setImage(graphics,elem,i,j);
		return null;
	}
	
	public Void visit(EmptyCase elem) {
		imageStrategy.setImage(graphics, elem,i,j);
		return null;
	}
	
	public Void visit(Obstacle elem) {
		imageStrategy.setImage(graphics, elem,i,j);
		return null;
	}

	public Void visit(Target elem) {
		imageStrategy.setImage(graphics, elem,i,j);
		return null;
	}
	
	public Void visit(Hole elem) {
		imageStrategy.setImage(graphics, elem,i,j);
		return null;
	}
	
	public Void visit(Map elem) {
		printMap(elem,elem.getCharacter());
		return null;
	}
	
	public void printMap(Map map,Character character) {
		Element element;
		for(int i = 0; i < map.getWidth(); i++) {
			for(int j = 0; j < map.getHeight() ; j++) {
				setI(i);
				setJ(j);
				element = map.getCase(j,i);
				element.accept(this);
				
				if(j==character.getCoordX() && i==character.getCoordY())
					character.accept(this);	
			}
		}
	}

}
