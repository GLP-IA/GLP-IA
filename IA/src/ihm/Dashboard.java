package ihm;


import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import data.Element;
import data.Hole;
import data.QLearningPara;
import data.AStarPara;
import process.Map;

public class Dashboard extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private Map map;
	private static final int spacing=2;
	
	public Dashboard(Map map) {
		this.map=map;
	}
	
	/**
	 * Defines what to do when the repaint() method is called.
	 */
	@Override
	public void paintComponent(Graphics g) {
		if(QLearningPara.runQlearning) {
			for(int i = 0; i < map.getWidth(); i++) {
				for(int j = 0; j < map.getHeight() ; j++) {
					try {
						//case Vide
						if((map.getCase(j,i).getReward() == 0))
								g.drawImage(ImageIO.read(new File("src/images/emptyCase.png")), i*80+spacing, j*80+spacing, 80,80,null);
					
						//Obstacle
						if(map.getCase(j,i).getReward() == -500) 
							g.drawImage(ImageIO.read(new File("src/images/obstacle_v2.png")), i*80+spacing, j*80+spacing, 80,80,null);
							
						//Objectif
						if(map.getCase(j,i).getReward() == 100) 
							g.drawImage(ImageIO.read(new File("src/images/target.png")), i*80+spacing, j*80+spacing, 80,80,null);
							
						//Perso
						if(map.getX()==j && map.getY() == i) 
							g.drawImage(ImageIO.read(new File("src/images/Kurios.png")), i*80+spacing, j*80+spacing, 80,80,null);		
					}
					catch (IOException e) {
						System.err.println("-- Can not read the image file ! --");
					}
				}
			}
		}
		else if (AStarPara.runAStar) {
			Element e=null;
			for(int i = 0; i < map.getWidth(); i++) {
				for(int j = 0; j < map.getHeight() ; j++) {
					try {
						e=map.getCase(j, i);
						//case Vide
						if((map.getCase(j,i).getReward() == 0))
								g.drawImage(ImageIO.read(new File("src/images/emptyCase.png")), i*80+spacing, j*80+spacing, 80,80,null);
							
						//Objectif
						if( e.getClass().getName().equals("data.Hole")) {
								if(((Hole) e).getHoleType().equals("Triangle"))
									g.drawImage(ImageIO.read(new File("src/images/triangle.png")), i*80+spacing, j*80+spacing, 80,80,null);
								if(((Hole) e).getHoleType().equals("Square")) 
									g.drawImage(ImageIO.read(new File("src/images/square.png")), i*80+spacing, j*80+spacing, 80,80,null);
								if(((Hole) e).getHoleType().equals("Circle")) 
									g.drawImage(ImageIO.read(new File("src/images/circle.png")), i*80+spacing, j*80+spacing, 80,80,null);
						}
						//Perso
						if(map.getX()==j && map.getY() == i) 
							g.drawImage(ImageIO.read(new File("src/images/Kurios.png")), i*80+spacing, j*80+spacing, 80,80,null);		
					}
					catch (IOException err) {
						System.err.println("-- Can not read the image file ! --");
					}
				}
			}
		}
		else {
			for(int i = 0; i < map.getWidth(); i++) {
				for(int j = 0; j < map.getHeight() ; j++) {
					try {
						//case Vide
						if((map.getCase(j,i).getReward() == 0))
								g.drawImage(ImageIO.read(new File("src/images/emptyCase.png")), i*80+spacing, j*80+spacing, 80,80,null);
						//Perso
						if(map.getX()==j && map.getY() == i) 
							g.drawImage(ImageIO.read(new File("src/images/Kurios.png")), i*80+spacing, j*80+spacing, 80,80,null);		
						
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
