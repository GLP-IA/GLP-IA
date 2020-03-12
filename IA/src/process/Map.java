package process;

import data.Element;
import data.Hole;
import data.Obstacle;
import data.QLearningPara;
import data.Target;

public class Map {
	private int height=(int) Math.sqrt(QLearningPara.DIM_MAP);
	private int width=(int) Math.sqrt(QLearningPara.DIM_MAP);
	private Element [][]map;
	private int x;
	private int y;
	
	
	public Map(int x, int y) {
		map = new Element [width][height];
		this.x=x;
		this.y=y;
		initEmptyMap();
	}
	
	private void initEmptyMap() {
		for(int i = 0; i< height; i ++) {
			for(int j = 0; j< width; j ++) {
				map [i][j] = new Element(0);
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
		initEmptyMap();
		
		map[0][2]=new Obstacle (QLearningPara.MALUS,"wall");
		map[0][3]=new Obstacle (QLearningPara.MALUS,"wall");
		map[0][7]=new Obstacle (QLearningPara.MALUS,"wall");
		map[0][8]=new Obstacle (QLearningPara.MALUS,"wall");
		
		map[1][0]=new Obstacle (QLearningPara.MALUS,"wall");

		
		map[3][1]=new Obstacle (QLearningPara.MALUS,"wall");
		map[3][2]=new Obstacle (QLearningPara.MALUS,"wall");
		map[3][3]=new Obstacle (QLearningPara.MALUS,"wall");
		map[3][6]=new Obstacle (QLearningPara.MALUS,"wall");
		map[3][7]=new Obstacle (QLearningPara.MALUS,"wall");

		map[4][2]=new Obstacle (QLearningPara.MALUS,"wall");
		map[4][6]=new Obstacle (QLearningPara.MALUS,"wall");
		
		map[5][6]=new Obstacle (QLearningPara.MALUS,"wall");
		map[5][5]=new Obstacle (QLearningPara.MALUS,"wall");
		
		map[6][1]=new Obstacle (QLearningPara.MALUS,"wall");
		
		map[7][1]=new Obstacle (QLearningPara.MALUS,"wall");
		map[7][2]=new Obstacle (QLearningPara.MALUS,"wall");
		map[7][9]=new Obstacle (QLearningPara.MALUS,"wall");
		
		map[8][3]=new Obstacle (QLearningPara.MALUS,"wall");
		
		map[9][0]=new Obstacle (QLearningPara.MALUS,"wall");
		map[9][7]=new Obstacle (QLearningPara.MALUS,"wall");
		
		map[9][9]=t;
	}
	
	/**
	 * genere la carte pour A*
	 */
	public void initMapA_Star() {
		initEmptyMap();
		
		map[6][5]=new Hole(6,5,"Triangle");
		map[5][3]=new Hole(5,3,"Square");
		map[2][4]=new Hole(2,4,"Circle");
	}
	
	public void hasMooved(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	/**
	 * Affiche la carte selon l'environnement du QLearning
	 */
	public void printMapQLearning() {
		System.out.println();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(i==x && j==y)
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
	 * afficher la carte selon les parametre de l'environnement de A*
	 */
	public void printMapA_Star() {
		System.out.println();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(i==x && j==y)
					System.out.print("X");
				
				else if(i==6 && j==5)
					System.out.print("^"); //on considere que c'est un triangle
				
				else if(i==5 && j==3)
					System.out.print("[]"); // on considere que c'est un carre
				
				else if(i==2 && j==4)
					System.out.print("O"); // on considere que c'est un rond
				
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

	
	public void setCase(int x2, int y2, Element element) {
		map[x2][y2]=element;
	}
}
