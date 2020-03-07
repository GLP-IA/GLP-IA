package test;

import data.QLearningPara;
import data.Target;

import process.Map;
import process.QLearningCore;

public class TestCore {
	
	
	public static void main(String[] args) {
		Map g = new Map(0,0); 
		Target t=new Target(QLearningPara.REWARD,false);
		
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