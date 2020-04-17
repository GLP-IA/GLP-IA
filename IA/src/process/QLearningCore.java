package process;
import java.util.Random;

import data.Character;
import data.Element;
import data.Target;
import data.QLearningPara;

public class QLearningCore {
	private Map map;
	private Target t;
	private Character character;
	private QTable qTable;
	
	private QFonction f;
	Random rand = new Random();
	
	
	public QLearningCore(Map map, Target t,Character character){
		this.map = map;
		this.t=t;
		this.character =character;
		qTable = new QTable();
		f=new QFonction(qTable,QLearningPara.GAMMA,QLearningPara.ALPHA);
	}

	public void run() {
				double exp=rand.nextDouble();
				if(exp<QLearningPara.GAMMA)
					learning();
				else
					application();
				
				if(map.getCase(character.getCoordX(),character.getCoordY()).getReward()==100) { //on considere que l'objectif a pour recompense 100
					t.setAchieved(true);
					//System.out.println("\n >> Bravo l'objectif est atteint ! <<");
				}
				//debug(exp);
	}
	
	/**
	 * explore aléatoirement la carte
	 */
	public void learning() {
		int reward;	
		int oldX=getX();
		int oldY=getY();
		boolean hasMooved =false;
		
		//generation d'un nombre aléatoirement entre 0 et 4 pour choisir le deplacement a effectue
		int r=rand.nextInt(4);
		
		if(r==0)
			MoovCharacter.moovUp(character,QLearningPara.DIM_MAP);

		if(r==1)
			MoovCharacter.moovDown(character,QLearningPara.DIM_MAP);

		if(r==2)
			MoovCharacter.moovLeft(character,QLearningPara.DIM_MAP);
	
		if(r==3)
			MoovCharacter.moovRight(character,QLearningPara.DIM_MAP);

		if(oldX!=getX() || oldY!= getY())
			hasMooved=true;
		
		if(hasMooved) {
			Element pos=map.getCase(getX(),getY());
			reward=pos.getReward();
			f.update(getX(),getY(),oldX,oldY,reward);
		}
	}
	
	/**
	 * utilisation de la QTable pour se deplacer
	 */
	public void application() {
		int oldX=getX();
		int oldY=getY();
		int reward;
		boolean hasMooved =false;
		
		//recupère l'état qui à la plus grande espérance de récompense
		int nextDir=qTable.maxDirection(States.getState(oldX,oldY));

		if(nextDir==0)
			MoovCharacter.moovUp(character,QLearningPara.DIM_MAP);

		if(nextDir==1)
			MoovCharacter.moovDown(character,QLearningPara.DIM_MAP);

		if(nextDir==2) 
			MoovCharacter.moovLeft(character,QLearningPara.DIM_MAP);
			
		if(nextDir==3)
			MoovCharacter.moovRight(character, QLearningPara.DIM_MAP);
		
		if(oldX!=getX() || oldY!= getY())
			hasMooved=true;
		
		if(hasMooved) {
			Element pos=map.getCase(getX(),getY());
			reward=pos.getReward();
			f.update(getX(),getY(),oldX,oldY,reward);
		}
	}
	
	public int getX(){
		return character.getCoordX();
	}
	
	public int getY() {
		return character.getCoordY();
	}
	
	/**
	 * permet de remettre les valeurs "par défaut" pour ainsi relancer le programme 
	 */
	public void reset() {
		character.setCoordY(rand.nextInt(5));
		character.setCoordX(rand.nextInt(5));
		t.setAchieved(false);
	}
	
	/**
	 * réduit le taux d'exploration pour ainsi favoriser l'exploitation
	 */
	public void dicreasedExploration() {
		QLearningPara.GAMMA=0;
	}
	
	/**
	 * affiche la qtable une fois remplis
	 */
	public String result() {
		return qTable.print();
	}
	
	/**
	 * permet de tester en mode console le programme
	 * 
	 * @param exp nombre généré aléatoirement pour définir le choix entre l'exploitation et l'exploration
	 */
	public void debug(double exp) {
		map.printMapQLearning();
		System.out.println("exp: "+exp+ " exploration Rate: "+ QLearningPara.GAMMA);
		if(exp<QLearningPara.GAMMA)
			System.out.println(">> EXPLORATION (deplacement aleatoire) <<");
		else
			System.out.println(">> EXPLOITATION (utilise la Qtable) <<");
		System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
		map.printMapQLearning();
	}
}