package gui.elements;


import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import data.AStarPara;
import data.MinMaxPara;
import data.PathAstar;
import data.QLearningPara;
import gui.management.DrawVisitor;
import process.Map;

public class Dashboard extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private Map map;
	private PathAstar path;
	
	public Dashboard(Map map) {
		this.map=map;
	}
	
	/**
	 * Defines what to do when the repaint() method is called.
	 */
	@Override
	public void paintComponent(Graphics g) {
		DrawVisitor drawVisitor= new DrawVisitor(g);
		if (!MinMaxPara.runMinMax) {
			map.accept(drawVisitor);
			if(AStarPara.runAStar && !QLearningPara.runQlearning)
				path.accept(drawVisitor);
		}
		else {
			try {
				g.drawImage(ImageIO.read(new File("src/images/MinMax_kurios_play.png")), 0, 0, 600, 600,null);
			} catch (IOException e) {
				System.err.println("-- Can not read the image file ! --");
			}
		}
	}
	public void setPath(PathAstar path) {
		this.path = path;
	}

 }
