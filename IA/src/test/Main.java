package test;

import gui.elements.GUI;

public class Main implements Runnable {
	
	GUI gui = new GUI();

	public static void main(String[] args) {
		new Thread(new Main()).start();
	}
	
	//run the IHM
	public void run() {
		 while(true) {
			 gui.repaint();
		 }
		
	}

}

