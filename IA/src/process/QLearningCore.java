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
	private double gamma = 0.9; // exploration rate , d�termine l'importance des futures r�compenses , facteur 0 l'agent ne consid�ra que les r�compenses actuelles, un facteur approchant 1 il visera une r�compense �lev�e � long terme 
	private double alpha = 0.2; // learning rate : facteur 0 emp�chera l'agent d'apprendre, facteur de 1 ne  consid�rerait que les informations les plus r�centes
	private QFonction f;
	Random rand = new Random();
	private static final int dimMap =25;
	
	public QLearningCore(Grille map, Target t){
		this.map = map;
		this.t=t;
		character = new Character(0,0,new Score());//positionne le personnage
		qTable = new QTable(dimMap);//la dimension de la carte est fixe donc peut etre cod� en dur ici 5x5
		f=new QFonction(qTable,gamma,alpha);
	}

	public void run() {
				double exp=rand.nextDouble();
				if(exp<gamma)
					learning();
				else
					application();
				
				if(map.getCase(character.getCoordX(),character.getCoordY()).getReward()==100) { //on considere que l'objectif a pour recompense 100
					t.setAchieved(true);
					System.out.println("\n >> Bravo l'objectif est atteint ! <<");
				}
				map.hasMooved(character.getCoordX(),character.getCoordY());
				//debug(exp);
	}
	
	/**
	 * explore al�atoirement la carte
	 */
	public void learning() {
		int reward;	
		int oldX=getOldX();
		int oldY=getOldY();
		
		//generation d'un nombre al�atoirement entre 0 et 4 pour choisir le deplacement a effectue
		int r=rand.nextInt(4);
		
		if(r==0) {
			MoovCharacter.moovUp(character,dimMap);
			System.out.println("direction : UP ");
		}
		if(r==1) {
			MoovCharacter.moovDown(character,dimMap);
			System.out.println("direction : DOWN ");
		}

		if(r==2) {
			MoovCharacter.moovLeft(character,dimMap);
			System.out.println("direction : LEFT ");
		}
	
		if(r==3) {
			MoovCharacter.moovRight(character,dimMap);
			System.out.println("direction : RIGHT ");
		}
		
		Element pos=map.getCase(character.getCoordX(),character.getCoordY());
		reward = pos.getReward();
		f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
	}
	
	/**
	 * utilisation de la QTable pour se deplacer
	 */
	public void application() {
		int oldX=getOldX();
		int oldY=getOldY();
		int reward;
		
		//recup�re l'�tat qui � la plus grande esp�rance de r�compense
		int nextDir=qTable.maxDirection(new States(dimMap).getState(oldX,oldY));

		if(nextDir==0) {
			MoovCharacter.moovUp(character,dimMap);
			System.out.println("direction : UP ");
		}
		if(nextDir==1) {
			MoovCharacter.moovDown(character,dimMap);
			System.out.println("direction : DOWN ");
		}
		if(nextDir==2) { 
			MoovCharacter.moovLeft(character,dimMap);
			System.out.println("direction : LEFT ");
		}
		if(nextDir==3) {
			MoovCharacter.moovRight(character,dimMap);
			System.out.println("direction : RIGHT ");
		}
		
		Element pos=map.getCase(character.getCoordX(),character.getCoordY());
		reward=pos.getReward();
		f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
	}
	
	public int getOldX(){
		return character.getCoordX();
	}
	
	public int getOldY() {
		return character.getCoordY();
	}
	
	/**
	 * permet de remettre les valeurs "par d�faut" pour ainsi relancer le programme 
	 */
	public void reset() {
		character.setCoordY(rand.nextInt(5));
		character.setCoordX(rand.nextInt(5));
		map.hasMooved(character.getCoordX(),character.getCoordY());
		t.setAchieved(false);
	}
	
	/**
	 * r�duit progressivement le taux d'exploration pour ainsi favoriser l'exploitation
	 */
	public void dicreasedExploration() {
		gamma-=0.8;
	}
	
	/**
	 * affiche la qtable une fois remplis
	 */
	public void result() {
		qTable.afficher();
	}
	
	/**
	 * permet de tester en mode console le programme
	 * 
	 * @param exp nombre g�n�r� al�atoirement pour d�finir le choix entre l'exploitation et l'exploration
	 */
	public void debug(double exp) {
		map.printMapQLearning();
		System.out.println("exp: "+exp+ " exploration Rate: "+ gamma);
		if(exp<gamma)
			System.out.println(">> EXPLORATION (deplacement aleatoire) <<");
		else
			System.out.println(">> EXPLOITATION (utilise la Qtable) <<");
		System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
		map.printMapQLearning();
	}
}