package test;

import data.Target;
import process.Grille;
import process.QLearningCore;

public class TestCore {
	private static int reward = 100;
	 private static int mapWidth = 5;
	 private static int mapHeight = 5;
	
	
	public static void main(String[] args) {
		
		
		Grille g = new Grille(mapWidth, mapHeight, 0,0); //pourquoi 0,0 ?
		Target t=new Target(reward,false); // on peut coder en dure le false
		g.placer(4,4,t);//placement de la case cible
		
		g.afficher();
		
		QLearningCore core= new QLearningCore(g,t);
		core.run(); ///ATTENTION LE PERSONNAGE PEUT SORTIR DES LIMITES		
	}

}
