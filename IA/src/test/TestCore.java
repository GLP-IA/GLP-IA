package test;

import data.Target;
import process.Grille;
import process.QLearningCore;

public class TestCore {

	public static void main(String[] args) {
		
		Grille g = new Grille(5, 5, 0,0);
		Target t=new Target(100,false);
		g.placer(1,1,t);
		
		g.afficher();
		
		QLearningCore core= new QLearningCore(g,t,0.9,0.2);
		core.run(); ///ATTENTION LE PERSONNAGE PEUT SORTIR DES LIMITES
	}

}
