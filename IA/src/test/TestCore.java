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
		
		g.initMapQLearning(t);//initalise la carte
		
		g.printMapQLearning();
		QLearningCore core= new QLearningCore(g,t);
		for (int i = 1; i <= 100; i++) {
			System.out.print(">>>>>>>>>>>>>>>>>>>>>> DEBUT EPISODE " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<< \n");
			while(!t.isAchieved())
				core.run();
			core.reset();
			core.dicreasedExploration();
			System.out.println(">>>>>>>>>>>>>>>>>>>>>> FIN EPISODE " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<< \n");
		}
		System.out.println("\t\tQTABLE FINAL");
		core.result();
	}
}