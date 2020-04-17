package gui.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import data.MinMaxPara;
import process.GameMachine;

/**
 * 
 * @author Nathan Virayie
 */
public class MinMaxWindow extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private GameMachine game = new GameMachine();
	private JTextArea scorePanel= new JTextArea();
	
	private JLabel image = new JLabel("");
	private ImageIcon kuriosImage =new ImageIcon("src/images/MinMax_kurios_play.png");
	private ImageIcon opponentImage =new ImageIcon("src/images/MinMax_opponent_play.png");
	
	private int choice=0;
	private boolean hasPlay =true;
	
	private Runnable instance = this;
	private Thread minMaxThread;
	
	public MinMaxWindow() {
		init();
	}
	
	public void init() {
		this.setTitle("MIN MAX");
		this.setSize(950, 900);
		this.setVisible(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		
		//////////////PANEL BOUTON///////////////
		JPanel panel_button = new JPanel();
		panel_button.setBackground(Color.GRAY);
		panel_button.setBounds(400, 475, 200, 200);
		this.getContentPane().add(panel_button);
		panel_button.setLayout(new GridLayout(0, 1, 0, 20));
		
		
		JButton startButton= new JButton("START");
		startButton.addActionListener(new StartGameAction());
		panel_button.add(startButton);
		
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
		image.setBounds(0, -50, 1400, 1000);
		this.getContentPane().add(image);
		image.setIcon(kuriosImage);
		
		scorePanel.setEditable(false);
		scorePanel.setFont(new Font("Arial",Font.BOLD,30));
		scorePanel.setBounds(500, 0, 320, 150);
		this.getContentPane().add(scorePanel);
	}

	public synchronized void play() throws InterruptedException{		
		while (game.getNbOfCoins() > 0 ){	
			if(!game.getCurrentNode().isMaxPlayer() && hasPlay) {
				scorePanel.setText("COINS: "+Integer.toString(game.getNbOfCoins())+"\nMINMAX TURN TURN");
				image.setIcon(kuriosImage);
				this.repaint();
				Thread.sleep(1000);
				game.KuriosTurn();
				choice=0;
				hasPlay=false;
			}
			
			else{
				image.setIcon(opponentImage);
				this.repaint();
				scorePanel.setText("COINS: "+Integer.toString(game.getNbOfCoins())+"\nYOUR TURN");
				
				if(!hasPlay) {
					wait();
				}
				else
					notify();
				
				if(choice!=0)
					game.OpponentTurn(choice);
			}
		}
		
		if (game.whoHasWin())
			scorePanel.setText("Kurios Win !");
		else
			scorePanel.setText("YOU Win !");
	}
	
	public void run() {
		try {
			play();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class StartGameAction implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			MinMaxPara.runMinMax=true;
			minMaxThread = new Thread(instance);
			minMaxThread.start();
		 }
	}

	private class Play1Action implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			choice = 1;
			hasPlay=true;
			minMaxThread = new Thread(instance);
			minMaxThread.start();
		 }
	}
	private class Play2Action implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			choice = 2;
			hasPlay=true;
			minMaxThread = new Thread(instance);
			minMaxThread.start();
		 }
	}
	private class Play3Action implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 choice = 3;
			 hasPlay=true;
			 minMaxThread = new Thread(instance);
			 minMaxThread.start();
		 }
	}
}