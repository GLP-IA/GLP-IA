package process;

import data.Element;
import data.ElementVisitor;
import data.EmptyCase;
import data.Hole;
import data.Obstacle;
import data.QLearningPara;
import data.Target;
import data.AStarPara;
import data.Character;

public class Map{
	//the map is supposed to be a square
	private int height=(int) Math.sqrt(QLearningPara.DIM_MAP);
	private int width=(int) Math.sqrt(QLearningPara.DIM_MAP);
	
	private Element [][]map;
	private Character character;
	
	
	public Map(Character character) {
		map = new Element [width][height];
		this.character=character;
		initEmptyMap(0);
	}
	
	private void initEmptyMap(int emptyReward) {
		for(int i = 0; i< height; i ++) {
			for(int j = 0; j< width; j ++) {
				map [i][j] = new EmptyCase(emptyReward);
			}
		}
	}

	public Element getCase(int l, int c) {
		return map[l][c];
	}
	
	/**
	 * Genere la carte pour le qLearning
	 * 
	 * @param t objectif
	 */
	public void initMapQLearning (Target t) {
		initEmptyMap(QLearningPara.EMPTY_REWARD);
		
		map[0][2]=new Obstacle (QLearningPara.MALUS);
		map[0][3]=new Obstacle (QLearningPara.MALUS);
		map[0][7]=new Obstacle (QLearningPara.MALUS);
		map[0][8]=new Obstacle (QLearningPara.MALUS);
		
		map[1][0]=new Obstacle (QLearningPara.MALUS);

		
		map[3][1]=new Obstacle (QLearningPara.MALUS);
		map[3][2]=new Obstacle (QLearningPara.MALUS);
		map[3][3]=new Obstacle (QLearningPara.MALUS);
		map[3][6]=new Obstacle (QLearningPara.MALUS);
		map[3][7]=new Obstacle (QLearningPara.MALUS);

		map[4][2]=new Obstacle (QLearningPara.MALUS);
		map[4][6]=new Obstacle (QLearningPara.MALUS);
		
		map[5][6]=new Obstacle (QLearningPara.MALUS);
		map[5][5]=new Obstacle (QLearningPara.MALUS);
		
		map[6][1]=new Obstacle (QLearningPara.MALUS);
		
		map[7][1]=new Obstacle (QLearningPara.MALUS);
		map[7][2]=new Obstacle (QLearningPara.MALUS);
		map[7][9]=new Obstacle (QLearningPara.MALUS);
		
		map[8][3]=new Obstacle (QLearningPara.MALUS);
		
		map[9][0]=new Obstacle (QLearningPara.MALUS);
		map[9][7]=new Obstacle (QLearningPara.MALUS);
		
		map[9][9]=t;
	}
	
	/**
	 * genere la carte pour A*
	 */
	public void initMapA_Star(Hole hole1, Hole hole2, Hole hole3) {
		initEmptyMap(AStarPara.EMPTY_REWARD);
		
		//set the holes
		map[hole1.getCoordX()][hole1.getCoordY()]=hole1;
		map[hole2.getCoordX()][hole2.getCoordY()]=hole2;
		map[hole3.getCoordX()][hole3.getCoordY()]=hole3;
		
		//we can add some obstacles
		map[1][0]= new Obstacle (AStarPara.MALUS);
		map[0][2]= new Obstacle (AStarPara.MALUS);
		map[3][5]= new Obstacle (AStarPara.MALUS);
		map[5][6]= new Obstacle (AStarPara.MALUS);
		map[9][7]= new Obstacle (AStarPara.MALUS);
		map[2][4]= new Obstacle (AStarPara.MALUS);

	}
	
	public int getX() {
		return character.getCoordX();
	}

	public int getY() {
		return character.getCoordY();
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Character getCharacter() {
		return character;
	}

	/**
	 * Affiche la carte selon l'environnement du QLearning
	 */
	public void printMapQLearning() {
		System.out.println();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(i==getX() && j==getY())
					System.out.print("X");
				
				else if(getCase(i,j).getReward()==-500)
					System.out.print("M");
				
				else if(getCase(i,j).getReward()==100)
					System.out.print("T");
				
				else
					System.out.print("-");
				
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * afficher la carte selon l'environnement de A*
	 */
	public void printMapA_Star() {
		System.out.println();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(i==getX() && j==getY())
					System.out.print("X");
				
				else if(getCase(i,j).getClass().getName().equals("data.Hole")) {
					if(((Hole) getCase(i,j)).getHoleType().equals("Triangle"))
						System.out.print("^"); //on considere que c'est un triangle
					if( ((Hole) getCase(i,j)).getHoleType().equals("Square"))
						System.out.print("[]"); // on considere que c'est un carre
					if( ((Hole) getCase(i,j)).getHoleType().equals("Circle"))
						System.out.print("O"); // on considere que c'est un rond
				}
				
				else if(getCase(i,j).getReward()==250)
					System.out.print("#"); //trainé
				else
					System.out.print("-");
				
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	
	public void setCase(int x, int y, Element element) {
		map[x][y]=element;
	}

	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
