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
import data.AStarPara;
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
		if(elem.isAchieved())
			imageStrategy.setImage(graphics, elem,i,j,true);
		else
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
		
		Hole target1=AStarPara.Target[0];
		Hole target2=AStarPara.Target[1];
		Hole target3=AStarPara.Target[2];
		
		for(it=p.getPath().iterator();it.hasNext();) {
			trail=it.next();
			setI(trail.getY());
			setJ(trail.getX());
			
			//don't drawImage over the character
			if(trail.getX()==cX && trail.getY()==cY)
				continue;
			
			//don't drawImage over the target
			else if( (trail.getX()==target1.getCoordX() && trail.getY()==target1.getCoordY())
					|| (trail.getX()==target2.getCoordX() && trail.getY()==target2.getCoordY())
					|| (trail.getX()==target3.getCoordX() && trail.getY()==target3.getCoordY()) ){
				continue;
			}
			
			else
				trail.accept(this);
		}						
	}
}
