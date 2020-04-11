package gui.management;

import java.awt.Graphics;
import java.util.Iterator;

import data.ElementVisitor;
import data.EmptyCase;
import data.Form;
import data.GoodBox;
import data.Hole;
import data.Obstacle;
import data.PathAstar;
import data.Target;
import data.Trail;
import data.WrongBox;
import process.Map;
import data.AnalyzedBox;
import data.Character;
import data.Element;

/**
 * This visitor print the squares of the map.
 * 
 * 
 * @author Nathan VIRAYIE
 */
public class DrawVisitor implements ElementVisitor<Void> {

	/**
	 * The image strategy used to define the images of squares and IA
	 * 
	 */
	private ImageStrategy imageStrategy = new ImageStrategy();
	
	private Graphics graphics;
	private int i=0;
	private int j=0;
	
	//Coordonnée du personnage
	private int cX=0;
	private int cY=0;
	
	public DrawVisitor(Graphics graphics) {
		this.graphics = graphics;
	}

	public void setI(int i) {
		this.i=i;
	}
	
	public void setJ(int j) {
		this.j=j;
	}
	
	public void setcX(int cX) {
		this.cX = cX;
	}

	public void setcY(int cY) {
		this.cY = cY;
	}

	public Void visit(Character elem) {
		imageStrategy.setImage(graphics,elem, i,j);
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
	
	public Void visit(Form elem) {
		//imageStrategy.setImage(graphics, elem,i,j);
		return null;
	}
	
	public Void visit(Map elem) {
		printMap(elem,elem.getCharacter());
		return null;
	}
	
	public Void visit(GoodBox trail) {
		imageStrategy.setImage(graphics, trail,i,j);
		return null;
	}
	
	public Void visit(WrongBox trail) {
		imageStrategy.setImage(graphics, trail,i,j);
		return null;
	}
	
	public Void visit(AnalyzedBox trail) {
		imageStrategy.setImage(graphics, trail,i,j);
		return null;
	}
	
	public Void visit(PathAstar path) {
		printPath(path);
		return null;
	}
	
	public void printMap(Map map,Character character) {
		Element element;
		for(int i = 0; i < map.getHeight(); i++) {
			for(int j = 0; j < map.getWidth() ; j++) {
				setI(i);
				setJ(j);
				element = map.getCase(j,i);
				element.accept(this);
				
				if(j==character.getCoordX() && i==character.getCoordY()) {
					character.accept(this);	
					setcX(character.getCoordX());
					setcY(character.getCoordY());
				}
			}
		}
	}
	
	public void printPath(PathAstar p) {
		Iterator<Trail> it;
		Trail trail;
		
		for(it=p.getPath().iterator();it.hasNext();) {
			trail=it.next();
			setI(trail.getY());
			setJ(trail.getX());
				
			if(trail.getX()==cX && trail.getY()==cY)
				continue;
			else
				trail.accept(this);
		}						
	}
}
