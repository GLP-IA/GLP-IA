package gui.management;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.EmptyCase;
import data.GoodBox;
import data.Hole;
import data.Obstacle;
import data.Target;
import data.WrongBox;
import data.Character;

/**
 * Définit comment chaque objet doit être afficher sur l'ihm
 * 
 * @author Nathan Virayie
 */
public class ImageStrategy {

	private int spacing =2;
	
	public void setImage(Graphics graphics, Target elem, int i, int j) {
		try {
			graphics.drawImage(ImageIO.read(new File("src/images/target.png")), i*80+spacing, j*80+spacing, 80,80,null);
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
		}
	}
	
	public void setImage(Graphics graphics, Character elem, int i, int j) {
		try {
			graphics.drawImage(ImageIO.read(new File("src/images/Kurios.png")), i*80+spacing, j*80+spacing, 80,80,null);	
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
		}
	}
	
	public void setImage(Graphics graphics, EmptyCase elem, int i, int j) {
		try {
			graphics.drawImage(ImageIO.read(new File("src/images/emptyCase.png")), i*80+spacing, j*80+spacing, 80,80,null);
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
		}
	}
	
	public void setImage(Graphics graphics, Obstacle elem, int i, int j) {
		try {
			graphics.drawImage(ImageIO.read(new File("src/images/obstacle_v2.png")), i*80+spacing, j*80+spacing, 80,80,null);
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
		}
	}
	
	public void setImage(Graphics graphics, Hole elem, int i, int j) {
		try {
			if(elem.getHoleType().equals("Triangle"))
				graphics.drawImage(ImageIO.read(new File("src/images/triangle.png")), i*80+spacing, j*80+spacing, 80,80,null);
			if(elem.getHoleType().equals("Square")) 
				graphics.drawImage(ImageIO.read(new File("src/images/square.png")), i*80+spacing, j*80+spacing, 80,80,null);
			if(elem.getHoleType().equals("Circle")) 
				graphics.drawImage(ImageIO.read(new File("src/images/circle.png")), i*80+spacing, j*80+spacing, 80,80,null);
		}catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
		}
	}

	public void setImage(Graphics graphics, WrongBox trail, int i, int j) {
		try {
			graphics.drawImage(ImageIO.read(new File("src/images/wrongBox.png")), i*80+spacing, j*80+spacing, 80,80,null);
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
		}
	}

	public void setImage(Graphics graphics, GoodBox trail, int i, int j) {
		try {
			graphics.drawImage(ImageIO.read(new File("src/images/goodBox.png")), i*80+spacing, j*80+spacing, 80,80,null);
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
		}
	}
}
