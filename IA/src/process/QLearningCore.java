package process;
import java.util.Random;

import data.Character;
import data.Score;
import data.Target;

public class QLearningCore {
	private Grille map;
	private Target t;
	private Character character;
	private QTable qTable;
	private double explorationRate;
	private double rewardMatter;
		
	public QLearningCore(Grille map, Target t, double explorationRate, double rewardMatter) {
		this.map = map;
		this.t=t;
		character = new Character(0,0,new Score());
		qTable = new QTable(25);//la dimension de la carte est fixe donc peut etre codé en dur ici 5x5
		this.explorationRate = explorationRate;
		this.rewardMatter = rewardMatter;
	}

	public void learning() {
		QFonction f=new QFonction(qTable,explorationRate,rewardMatter);
		MoovCharacter mv =new MoovCharacter(character);
		int reward,oldX,oldY;
		
		while(!t.isAchieved()) { //tq l'objectif n'est pas atteint
			oldX=character.getCoordX();
			oldY=character.getCoordY();
			
			//generation d'un nombre aléatoirement entre 0 et 4 pour choisir le deplacement a effectue
			Random rand = new Random();
			int r=rand.nextInt(4);
		
			if(r==0)
				mv.moovUp();

			if(r==1)
				mv.moovDown();

			if(r==2) 
				mv.moovLeft();
			
			if(r==3) 
				mv.moovRight();
				
			reward=map.getCase(character.getCoordX(), character.getCoordY()).getReward();
			f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
			
			if(map.getCase(character.getCoordX(),character.getCoordY()).getReward()==100) //on considere que l'objectif a pour recompense 100
				t.setAchieved(true);
		}
		reset();
	}
	
	public void application() {
		//utilise la QTable pour bouger
	}
	
	public void reset() {
		character.setCoordY(0);
		character.setCoordX(0);
		t.setAchieved(false);
	}
}