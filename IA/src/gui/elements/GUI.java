package gui.elements;

import javax.swing.*;

import data.AStarPara;
import data.Character;
import data.QLearningPara;
import data.Target;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import process.A_StarCore;
import process.Map;
import process.InfosReader;
import process.QLearningCore;


public class GUI extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private Character character = new Character(0,0);//positionne le personnage
	private Map map= new Map(character);
	
	//Qlearning spec
		private Target t=new Target(QLearningPara.REWARD,false);
		private QLearningCore coreQ;
		
	//ASTar spec
	private A_StarCore coreA;
	
	//Jpanel
	private Dashboard dashboard = new Dashboard(map);
	JTextPane infos = new JTextPane();
	
	private Runnable instance = this;
	
	
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
		dashboard.setBounds(50, 50, 850, 850);
		this.getContentPane().add(dashboard);
		
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
	
	public void qLearning(QLearningCore coreQ) {
		map.initMapQLearning(t);//initialise la carte
		coreQ= new QLearningCore(map,t,character);
		
		for (int i = 0; i <= 100; i++) {
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
			QLearningPara.runQlearning=false;
		}
		catch(InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void aStar() {
		map.initMapA_Star();
		this.repaint();
		AStarPara.runAStar=false;
	}
	
	public void run() {
		if(QLearningPara.runQlearning)
			qLearning(coreQ);
		else if(AStarPara.runAStar)
			aStar();
	}
	
	private class StartQlearningAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			QLearningPara.runQlearning=true;
			infos.setText(InfosReader.ReadInfos("src/informations/infoQLearning.txt"));
			Thread qLearningThread = new Thread(instance);
			qLearningThread.start();
		 }
	}
	
	private class StartAStarAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			AStarPara.runAStar=true;
			infos.setText(InfosReader.ReadInfos("src/informations/infoAstar.txt"));
			Thread aStarThread = new Thread(instance);
			aStarThread.start();
		 }
	}
}
