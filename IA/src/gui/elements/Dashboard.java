package gui.elements;


import java.awt.Graphics;
import javax.swing.JPanel;

import data.AStarPara;
import data.PathAstar;
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
		
		map.accept(drawVisitor);
		if(AStarPara.runAStar)
			path.accept(drawVisitor);
	}

	public void setPath(PathAstar path) {
		this.path = path;
	}

 }
