package ihm;

import javax.imageio.ImageIO;
import javax.swing.*;

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
	
	JButton button_astar = new JButton("A*");
	JButton button_qlearning= new JButton("QLEARNING");
	
	
	private Runnable instance = this;
	
	/**
	 * define which option should be run in the run() method
	 * 
	 * @see StartQlearningAction
	 */
	private boolean runQlearning=false;
	private boolean runAStar=false;
	
	
	private Map map = new Map(0,0); 
	
	//Qlearning spec
	private Target t=new Target(QLearningPara.REWARD,false);
	private QLearningCore coreQ= new QLearningCore(map,t);
	
	//ASTar spec
	//private A_StarCore coreA= new A_StarCore(1, 0, 0, map);
	
	
	public GUI() {
		init();
	}
	
	public void init() {
		//definition des bases de la fenetre
		this.setTitle("KURIOS");
		this.setSize(1600, 1000);
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
		label_logo.setBounds(870, 50, 450, 170);
		this.getContentPane().add(label_logo);
		label_logo.setIcon(new ImageIcon("src/images/logo_v2.png"));
		
		//////////////PANEL BOUTON///////////////
		JPanel panel_button = new JPanel();
		panel_button.setBackground(Color.DARK_GRAY);
		panel_button.setBounds(1030, 250, 190, 250);
		this.getContentPane().add(panel_button);
		panel_button.setLayout(new GridLayout(0, 1, 0, 20));
		
				//Ajout des boutons
				
				button_qlearning.addActionListener(new StartQlearningAction());
				panel_button.add(button_qlearning);
				
				button_astar.addActionListener(new StartAstarAction());
				panel_button.add(button_astar);
				
				JButton button_minmax = new JButton("MinMax");
				panel_button.add(button_minmax);
				
		//////////////PANEL INFO////////////////
		JPanel panel_info = new JPanel();
		panel_info.setBackground(Color.GRAY);
		panel_info.setBounds(850, 550, 550, 200);
		this.getContentPane().add(panel_info);
				
		infos.setBackground(Color.GRAY);
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
									g.drawImage(ImageIO.read(new File("src/images/emptyCase.png")), i*70+spacing, j*70+spacing, 70,70,null);
							
							//Obstacle
							if(map.getCase(j,i).getReward() == -500) 
								g.drawImage(ImageIO.read(new File("src/images/obstacle_v2.png")), i*70+spacing, j*70+spacing, 70,70,null);
							
							//Objectif
							if(map.getCase(j,i).getReward() == 100) 
								g.setColor(Color.green);// objectif en vert
							
							//Perso
							if(map.getX()==j && map.getY() == i) 
								g.drawImage(ImageIO.read(new File("src/images/Kurios.png")), i*70+spacing, j*70+spacing, 70,70,null);		
						}
						catch (IOException e) {
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
									g.drawImage(ImageIO.read(new File("src/images/emptyCase.png")), i*70+spacing, j*70+spacing, 70,70,null);
							
							//Perso
							if(map.getX()==j && map.getY() == i) 
								g.drawImage(ImageIO.read(new File("src/images/Kurios.png")), i*70+spacing, j*70+spacing, 70,70,null);		
						}
						catch (IOException e) {
							System.err.println("-- Can not read the image file ! --");
						}
					}
				}
			}
		}
	}
	
	public void qLearning(QLearningCore coreQ) {
		map.initMapQLearning(t);//initialise la carte
		
		for (int i = 0; i <= 100; i++) {
			try {
				while(!t.isAchieved()) {
					coreQ.run();
					this.repaint();
					Thread.sleep(1);
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
				Thread.sleep(1000);
			}
			runQlearning=false;
		}
		catch(InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void aStar(A_StarCore coreA) {
		map.initMapA_Star();
		runAStar=false;
	}
	
	
	public void run() {
		if(runQlearning)
			qLearning(coreQ);
		if(runAStar)
		//	aStar(coreA);
			return;
	}
	
	private class StartQlearningAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			runQlearning=true;
			infos.setText(InfosReader.ReadInfos("src/informations/infoQLearning.txt"));
			Thread qLearningThread = new Thread(instance);
			qLearningThread.start();
		 }
	}
	private class StartAstarAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			runAStar=true;
			infos.setText(InfosReader.ReadInfos("src/informations/infoAStar.txt"));
			Thread aStarThread = new Thread(instance);
			aStarThread.start();
		 }
	}
}
