package gui.elements;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.MinMaxPara;
import process.GameMachine;


public class MinMaxWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private GameMachine game = new GameMachine();
	private int choice;
	private JLabel image = new JLabel("");
	public MinMaxWindow() {
		init();
	}
	
	public void init() {
		//definition des bases de la fenetre
		this.setTitle("KURIOS");
		this.setSize(900, 700);
		this.setExtendedState(MAXIMIZED_BOTH);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		
		
		//////////////PANEL BOUTON///////////////
		JPanel panel_button = new JPanel();
		panel_button.setBackground(Color.DARK_GRAY);
		panel_button.setBounds(1000, 250, 300, 200);
		this.getContentPane().add(panel_button);
		panel_button.setLayout(new GridLayout(0, 1, 0, 20));
		
		
		//Ajout des boutons
		JButton button1= new JButton("-1");
		button1.addActionListener(new Play1Action());
		panel_button.add(button1);
		
		JButton button2 = new JButton("-2");
		button2.addActionListener(new Play2Action());
		panel_button.add(button2);
		
		JButton button3 = new JButton("-3");
		button3.addActionListener(new Play3Action());
		panel_button.add(button3);
		
		//////////////////////iMAGE////////////////////////////
		image.setBounds(200, 50, 1000, 1000);
		this.getContentPane().add(image);
		image.setIcon(new ImageIcon("src/images/MinMax_kurios_play.png"));
	}

	
	
	public void run() {
		while (game.getNbOfCoins() > 0 ){
			if(game.getCurrentNode().isMaxPlayer()) {
				game.KuriosTurn();
				image.setIcon(new ImageIcon("src/images/MinMax_kurios_play.png"));
				
			}else {
				game.OpponentTurn(choice);
				image.setIcon(new ImageIcon("src/images/MinMax_opponent_play.png"));
				
			}
			this.repaint();
		}
		
	}
	
	private class Play1Action implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 MinMaxPara.runMinMax=true;
			choice = 1;
			run();
		 }
	}
	private class Play2Action implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 MinMaxPara.runMinMax=true;
			 choice = 2;
			 run();
		 }
	}
	private class Play3Action implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 MinMaxPara.runMinMax=true;
			 choice = 3;
			 run();
		 }
	}
}