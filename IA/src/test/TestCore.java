package test;

import data.Target;

import process.Grille;
import process.QLearningCore;

public class TestCore {
	private static int reward = 100;
	private static int mapWidth = 5;
	private static int mapHeight = 5;
	
	
	public static void main(String[] args) {
		Grille g = new Grille(mapWidth, mapHeight,0,0); 
		Target t=new Target(reward,false);
		
		g.initMap(4,4,t);//placement de la case cible
		
		g.afficher();
		QLearningCore core= new QLearningCore(g,t);
		for (int i = 1; i <= 100; i++) {
			System.out.print(">>>>>>>>>>>>>>>>>>>>>> DEBUT EPISODE " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<< \n");
			core.run();
			core.reset();	
			System.out.println(">>>>>>>>>>>>>>>>>>>>>> FIN EPISODE " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<< \n");
		}
		System.out.println("\t\tQTABLE FINAL");
		core.result();
		/*core.dicreasedExploration();
		core.run();*/
	}
}