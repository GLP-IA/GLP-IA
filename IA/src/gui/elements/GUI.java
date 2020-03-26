package gui.elements;

import data.AStarPara;
import process.A_StarCore;
import data.Node;

import data.QLearningPara;
import process.QLearningCore;
import data.Target;

import data.Character;
import process.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JLabel label_instrumLabel = new JLabel("");
	
	
	private Runnable instance = this;
	
	
	public GUI() {
		init();
	}
	
	public void init() {
		//definition des bases de la fenetre
		this.setTitle("KURIOS");
		this.setSize(1500, 1000);
		this.setExtendedState(MAXIMIZED_BOTH);	
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
		label_instrumLabel.setBounds(1000, 420, 500, 500);
		this.getContentPane().add(label_instrumLabel);
		
	}
	
	public synchronized void qLearning() throws InterruptedException {
		if(AStarPara.runAStar) {
			wait();
		}
		
		map.initMapQLearning(t);//initialise la carte
		coreQ= new QLearningCore(map,t,character);
		
		//instrumentation : exploration (on) et exploitation (off)
		label_instrumLabel.setIcon(new ImageIcon("src/images/instrumentation_Qlearning_1.png"));

		
		//exploration
		for (int i = 0; i <= 100; i++) {
			while(!t.isAchieved()) {
				coreQ.run();
				this.repaint();
				Thread.sleep(1);
			}
			coreQ.reset();
		}
		
		System.out.println("\t\tQTABLE FINAL");
		coreQ.result();
		coreQ.dicreasedExploration();
		
		
		//instrumentation : exploration (off) et exploitation (on)
		label_instrumLabel.setIcon(new ImageIcon("src/images/instrumentation_Qlearning_2.png"));

		//exploitation
		while(!t.isAchieved()) {
			coreQ.run();
			this.repaint();
			Thread.sleep(2000);
		}
		QLearningPara.runQlearning=false;
		notify();
	}
	
	public synchronized void aStar() throws InterruptedException{
		if(QLearningPara.runQlearning) {
			wait();
		}
		
		map.initMapA_Star(AStarPara.Target[0],AStarPara.Target[1],AStarPara.Target[2]);
		coreA=new A_StarCore(map);
		ArrayList<Node> pathHole = null;
		int i=0;
		int counter=0;
		
		instrumentationAstar();
		
		while(i<AStarPara.Target.length) {
			pathHole = coreA.findPath(AStarPara.Target[i]);

			///using the path to moov the character
			if(AStarPara.Target[i].isAchieved()) {
				Iterator<Node> it;
				Node node;
				for(it=pathHole.iterator();it.hasNext();) {
					node=it.next();
					coreA.usePath(character,node);
					this.repaint();
					Thread.sleep(1000);
				}
				counter=coreA.count(pathHole);
				break;
			}
			else {
				i++;
				coreA.reset(character);
				pathHole=null;
			}
		}
		coreA.reset(character);
		AStarPara.runAStar=false;
		notify();
	}
	
	public void instrumentationAstar() {
		if (coreA.getForm().getFormType().equals("Triangle"))
			label_instrumLabel.setIcon(new ImageIcon("src/images/kurios_triangle.png"));
	
		if (coreA.getForm().getFormType().equals("Square"))
			label_instrumLabel.setIcon(new ImageIcon("src/images/kurios_square.png"));
	
		if (coreA.getForm().getFormType().equals("Circle"))
			label_instrumLabel.setIcon(new ImageIcon("src/images/kurios_circle.png"));
	}

	public void run() {
		if(QLearningPara.runQlearning)
			try {
				qLearning();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if(AStarPara.runAStar)
			try {
				aStar();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private class StartQlearningAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			QLearningPara.runQlearning=true;
			Thread qLearningThread = new Thread(instance);
			qLearningThread.start();
		 }
	}
	
	private class StartAStarAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			AStarPara.runAStar=true;
			Thread aStarThread = new Thread(instance);
			aStarThread.start();
		 }
	}
}
