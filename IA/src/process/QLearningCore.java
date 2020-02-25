package process;
import java.util.Random;

import data.Character;
import data.Element;
import data.Score;
import data.Target;

public class QLearningCore {
	private Grille map;
	private Target t;
	private Character character;
	private QTable qTable;
	private double gamma = 0.9; // exploration rate , détermine l'importance des futures récompenses , facteur 0 l'agent ne considéra que les récompenses actuelles, un facteur approchant 1 il visera une récompense élevée à long terme 
	private double alpha = 0.2; // learning rate : facteur 0 empêchera l'agent d'apprendre, facteur de 1 ne  considérerait que les informations les plus récentes
	Random rand = new Random();	
	
	public QLearningCore(Grille map, Target t){
		this.map = map;
		this.t=t;
		character = new Character(0,0,new Score());//positionne le personnage
		qTable = new QTable(25);//la dimension de la carte est fixe donc peut etre codé en dur ici 5x5
	}

	public void run() {
		QFonction f=new QFonction(qTable,gamma,alpha);
		MoovCharacter mv =new MoovCharacter(character,25);
		while(!t.isAchieved()) {
			double exp=rand.nextDouble();
			System.out.println("exp: "+exp+ " exploration Rate: "+ gamma);
			if(exp<gamma)
				learning(f,mv);
			else
				application(f,mv);
			
			if(map.getCase(character.getCoordX(),character.getCoordY()).getReward()==100) { //on considere que l'objectif a pour recompense 100
				t.setAchieved(true);
				//System.out.println("\n >> Bravo l'objectif est atteint ! <<");
			}
			
			//explorationRate-=0.2;
			map.hasMooved(character.getCoordX(),character.getCoordY());
			map.afficher();
		}
	}
	public void learning(QFonction f,MoovCharacter mv) {
		int reward;	
		int oldX=character.getCoordX();
		int oldY=character.getCoordY();
		
		//generation d'un nombre aléatoirement entre 0 et 4 pour choisir le deplacement a effectue
		int r=rand.nextInt(4);
		
		System.out.println(">> EXPLORATION (deplacement aleatoire) <<");
		
		if(r==0) {
			mv.moovUp();
			System.out.println("direction : UP ");
		}
		if(r==1) {
			mv.moovDown();
			System.out.println("direction : DOWN ");
		}

		if(r==2) {
			mv.moovLeft();
			System.out.println("direction : LEFT ");
		}
	
		if(r==3) {
			mv.moovRight();
			System.out.println("direction : RIGHT ");
		}
		
		Element pos=map.getCase(character.getCoordX(),character.getCoordY());
		reward = pos.getReward();
		f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
		System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
	}
	
	public void application(QFonction f,MoovCharacter mv) {
		int oldX=character.getCoordX();
		int oldY=character.getCoordY();
		int reward;
		
		//utilise la QTable pour se deplacer
		int nextDir=qTable.maxDirection(new States(25).getState(oldX,oldY));
		//System.out.println("nextDir :"+nextDir);
		System.out.println(">> EXPLOITATION (utilise la Qtable) <<");
		if(nextDir==0) {
			mv.moovUp();
			System.out.println("direction : UP ");
		}
		if(nextDir==1) {
			mv.moovDown();
			System.out.println("direction : DOWN ");
		}
		if(nextDir==2) { 
			mv.moovLeft();
			System.out.println("direction : LEFT ");
		}
		if(nextDir==3) {
			mv.moovRight();
			System.out.println("direction : RIGHT ");
		}		
		Element pos=map.getCase(character.getCoordX(),character.getCoordY());
		reward=pos.getReward();
		f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
		System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
	}
	
	public void reset() {
		character.setCoordY(rand.nextInt(5));
		character.setCoordX(rand.nextInt(5));
		map.hasMooved(character.getCoordX(),character.getCoordY());
		t.setAchieved(false);
		map.afficher();
	}
	
	public void dicreasedExploration() {
		gamma-=gamma*0.2;
	}
	
	public void result() {
		qTable.afficher();
	}
}