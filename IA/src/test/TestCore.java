package test;

import data.Target;
import data.Obstacle;

import process.Grille;
import process.QLearningCore;

public class TestCore {
	private static int reward = 100;
	 private static int mapWidth = 5;
	 private static int mapHeight = 5;
	
	
	public static void main(String[] args) {
		
		
		Grille g = new Grille(mapWidth, mapHeight, 0,0); //pourquoi 0,0 ?
		Target t=new Target(reward,false); // on peut coder en dure le false
		Obstacle O = new Obstacle (-500,"MUR");
		g.placer(1,3,O);//placement de la case cible

		g.placer(4,4,t);//placement de la case cible
		
		
		g.afficher();
		
		QLearningCore core= new QLearningCore(g,t);
		for (int i = 1; i <= 5; i++) {
			
		System.out.print(">>>>>>>>>>>>>>>>>>>>>> DEBUT EPISODE " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<< \n");
		core.run();
		core.reset();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> FIN EPISODE " + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<< \n");
		}
	}

}
