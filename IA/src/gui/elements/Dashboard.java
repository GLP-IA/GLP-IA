package gui.elements;

import java.awt.Graphics;
import javax.swing.JPanel;
import gui.management.DrawVisitor;
import process.Map;

/**
 * The main panel of the GUI (the grid is created here)
 * 
 * @author Nathan VIRAYIE
 *
 */
public class Dashboard extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private Map map;
	
	
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
	}
 }
