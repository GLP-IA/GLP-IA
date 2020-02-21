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

	public void run() {
		QFonction f=new QFonction(qTable,explorationRate,rewardMatter);
		MoovCharacter mv =new MoovCharacter(character);
		while(!t.isAchieved()) {
			Random rand = new Random();
			double exp=rand.nextDouble();
			System.out.println("exp:"+exp+ "exploration Rate:"+explorationRate);
			if(exp<explorationRate)
				learning(f,mv);
			else
				application(f,mv);
			
			if(map.getCase(character.getCoordX(),character.getCoordY()).getReward()==100) //on considere que l'objectif a pour recompense 100
				t.setAchieved(true);
			
			explorationRate-=0.2;
			map.hasMooved(character.getCoordX(),character.getCoordY());
			map.afficher();
		}
		reset();
	}
	public void learning(QFonction f,MoovCharacter mv) {
		int reward;	
		int oldX=character.getCoordX();
		int oldY=character.getCoordY();
		
		//generation d'un nombre aléatoirement entre 0 et 4 pour choisir le deplacement a effectue
		Random rand = new Random();	
		int r=rand.nextInt(4);
		System.out.println("deplacement:"+r);
		if(r==0)
			mv.moovUp();

		if(r==1)
			mv.moovDown();

		if(r==2) 
			mv.moovLeft();
	
		if(r==3) 
			mv.moovRight();
		
		Element pos=map.getCase(character.getCoordX(),character.getCoordY());
		reward=pos.getReward();
		f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
		System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
	}
	
	public void application(QFonction f,MoovCharacter mv) {
		int oldX=character.getCoordX();
		int oldY=character.getCoordY();
		int reward;
		
		//utilise la QTable pour bouger
		int nextDir=qTable.maxDirection(new States(25).getState(oldX,oldY));
		System.out.println("nextDir:"+nextDir);
		if(nextDir==0)
			mv.moovUp();

		if(nextDir==1)
			mv.moovDown();

		if(nextDir==2) 
			mv.moovLeft();

		if(nextDir==3) 
			mv.moovRight();
				
		Element pos=map.getCase(character.getCoordX(),character.getCoordY());
		reward=pos.getReward();
		f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
		System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
	}
	
	public void reset() {
		character.setCoordY(0);
		character.setCoordX(0);
		t.setAchieved(false);
	}
}