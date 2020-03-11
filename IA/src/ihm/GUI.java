package ihm;

import javax.imageio.ImageIO;
import javax.swing.*;

import data.Element;
import data.Hole;
import data.QLearningPara;
import data.Target;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import process.A_StarCore;
import process.Map;
import process.InfosReader;
import process.QLearningCore;


public class GUI extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	Image image;
	
	//Space between the cells
	int spacing = 2;
	
	//Jpanel
	private Board board = new Board();
	JTextPane infos = new JTextPane();
	
	
	private Runnable instance = this;
	
	/**
	 * define which option should be run in the run() method
	 * 
	 * @see StartQlearningAction
	 */
	private boolean runQlearning=false;
	private boolean runAStar=false;
	
	
	private Map map= new Map(0,0); 
	
	//Qlearning spec
	private Target t=new Target(QLearningPara.REWARD,false);
	private QLearningCore coreQ;
	
	//ASTar spec
	//private A_StarCore coreA= new A_StarCore(1, 0, 0, map);
	
	
	public GUI() {
		init();
	}
	
	public void init() {
		//definition des bases de la fenetre
		this.setTitle("KURIOS");
		this.setSize(1500, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		
		//ajout de la Map
		board.setBounds(50, 50, 850, 850);
		this.getContentPane().add(board);
		
		//ajout du logo
		JLabel label_logo = new JLabel("");
		label_logo.setBounds(1000, 50, 450, 170);
		this.getContentPane().add(label_logo);
		label_logo.setIcon(new ImageIcon("src/images/logo_v2.png"));
		
		//////////////PANEL BOUTON///////////////
		JPanel panel_button = new JPanel();
		panel_button.setBackground(Color.DARK_GRAY);
		panel_button.setBounds(1100, 250, 300, 300);
		this.getContentPane().add(panel_button);
		panel_button.setLayout(new GridLayout(0, 1, 0, 20));
		
				//Ajout des boutons
				JButton button_qlearning= new JButton("QLEARNING");
				button_qlearning.addActionListener(new StartQlearningAction());
				panel_button.add(button_qlearning);
				
				JButton button_astar = new JButton("A*");
				button_astar.addActionListener(new StartAStarAction());
				panel_button.add(button_astar);
				
				JButton button_minmax = new JButton("MinMax");
				panel_button.add(button_minmax);
				
		//////////////PANEL INFO////////////////
		JPanel panel_info = new JPanel();
		panel_info.setBackground(Color.WHITE);
		panel_info.setBounds(1000, 580, 500, 175);
		this.getContentPane().add(panel_info);
				
		infos.setEditable(false);
		infos.setText("Infos sur l'algorithme");
		panel_info.add(infos);
	}
	
	public class Board extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			if(runQlearning) {
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
			else if (runAStar) {
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	public void qLearning(QLearningCore coreQ) {
		map.initMapQLearning(t);//initialise la carte
		coreQ= new QLearningCore(map,t);
		
		for (int i = 0; i <= 2; i++) {
			try {
				while(!t.isAchieved()) {
					coreQ.run();
					this.repaint();
					Thread.sleep(2);
				}
				coreQ.reset();
			}
			catch(InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println("\t\tQTABLE FINAL");
		coreQ.result();
		coreQ.dicreasedExploration();
		try {
			while(!t.isAchieved()) {
				coreQ.run();
				this.repaint();
				Thread.sleep(2000);
			}
			runQlearning=false;
		}
		catch(InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void aStar() {
		map.initMapA_Star();
		this.repaint();
		//runAStar=false;
	}
	
	public void run() {
		if(runQlearning)
			qLearning(coreQ);
		else if(runAStar)
			aStar();
	}
	
	private class StartQlearningAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			runQlearning=true;
			infos.setText(InfosReader.ReadInfos("src/informations/infoQLearning.txt"));
			Thread qLearningThread = new Thread(instance);
			qLearningThread.start();
		 }
	}
	
	private class StartAStarAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			runAStar=true;
			infos.setText(InfosReader.ReadInfos("src/informations/infoAstar.txt"));
			Thread aStarThread = new Thread(instance);
			aStarThread.start();
		 }
	}
}
