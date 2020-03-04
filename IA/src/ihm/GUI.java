package ihm;

import javax.imageio.ImageIO;
import javax.swing.*;

import data.Target;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import process.Grille;
import process.QLearningCore;


public class GUI extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	Image image;
	
	//Space between the cells
	int spacing = 2;
	
	//Jpanel
	private Board board = new Board();
	JButton qlearningLaunch= new JButton("QLEARNING");
	
	private Runnable instance = this;
	
	//Variable
	private static int reward = 100;
	private static int mapWidth = 5;
	private static int mapHeight = 5;
	
	/**
	 * define which option should be run in the run() method
	 * 
	 * @see StartQlearningAction
	 */
	private boolean runQlearning=false;
	private boolean runAStar=false;
	
	private Grille map = new Grille(mapWidth, mapHeight,0,0); 
	
	//Qlearning spec
	private Target t=new Target(reward,false);
	private QLearningCore coreQ= new QLearningCore(map,t);
	
	
	public GUI() {
		init();
	}
	
	public void init() {
		this.setTitle("KURIOS");
		this.setSize(1286, 829);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		qlearningLaunch.addActionListener(new StartQlearningAction());
		
		this.setContentPane(board);
		this.add(qlearningLaunch);
		
	}
	
	public class Board extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			//Fond de couleur
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 1286, 829);
			
			for(int i = 0; i < mapWidth; i++) {
				for(int j = 0; j < mapHeight ; j++) {
					try {
						//case Vide
						if((map.getCase(j,i).getReward() == 0))
								g.drawImage(ImageIO.read(new File("src/images/emptyCase.png")), i*100+spacing, j*100+spacing, 100,100,null);
						
						//Obstacle
						if(map.getCase(j,i).getReward() == -500) 
							g.drawImage(ImageIO.read(new File("src/images/obstacle_v2.png")), i*100+spacing, j*100+spacing, 100,100,null);
						
						//Objectif
						if(map.getCase(j,i).getReward() == 100) 
							g.setColor(Color.green);// objectif en vert
						
						//Perso
						if(map.getX()==j && map.getY() == i) 
							g.drawImage(ImageIO.read(new File("src/images/Kurios.png")), i*100+spacing, j*100+spacing, 100,100,null);		
					}
					catch (IOException e) {
						System.err.println("-- Can not read the image file ! --");
					}
				}
			}
		}
	}
	
	public void qLearning(QLearningCore coreQQ) {
		map.initMapQLearning(t);//initialise la carte
		
		for (int i = 0; i <= 100; i++) {
			try {
				while(!t.isAchieved()) {
					coreQQ.run();
					this.repaint();
					Thread.sleep(5);
				}
				coreQQ.reset();
			}
			catch(InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println("\t\tQTABLE FINAL");
		coreQQ.result();
		coreQQ.dicreasedExploration();
		try {
			while(!t.isAchieved()) {
				coreQQ.run();
				this.repaint();
				Thread.sleep(2000);
			}
			runQlearning=false;
		}
		catch(InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void run() {
		if(runQlearning)
			qLearning(coreQ);
		if(runAStar)
			return;
	}
	
	private class StartQlearningAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 runQlearning=true;
			Thread qLearningThread = new Thread(instance);
			qLearningThread.start();
		 }
	}
}
