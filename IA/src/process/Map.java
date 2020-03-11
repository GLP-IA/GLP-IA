package process;

import data.Element;
import data.Hole;
import data.Obstacle;
import data.QLearningPara;
import data.Target;

public class Map {
	private int nbLig;
	private int nbCol;
	private Element [][]grille;
	private int x;
	private int y;
	
	
	public Map(int x, int y) {
		nbLig=(int) Math.sqrt(QLearningPara.DIM_MAP);
		nbCol=nbLig;
		grille = new Element [nbCol][nbLig];
		this.x=x;
		this.y=y;
		
		for(int i = 0; i< nbLig; i ++) {
			for(int j = 0; j< nbCol; j ++) {
				grille [i][j] = new Element(0);
			}
		}
	}
	
	public Element getCase(int l, int c) {
		return grille[l][c];
	}
	
	/**
	 * Genere la carte pour le qLearning
	 * 
	 * @param t objectif
	 */
	public void initMapQLearning (Target t) {
	
		
		grille[0][2]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[0][3]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[0][7]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[0][8]=new Obstacle (QLearningPara.MALUS,"wall");
		
		grille[1][0]=new Obstacle (QLearningPara.MALUS,"wall");

		
		grille[3][1]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[3][2]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[3][3]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[3][6]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[3][7]=new Obstacle (QLearningPara.MALUS,"wall");

		grille[4][2]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[4][6]=new Obstacle (QLearningPara.MALUS,"wall");
		
		grille[5][6]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[5][5]=new Obstacle (QLearningPara.MALUS,"wall");
		
		grille[6][1]=new Obstacle (QLearningPara.MALUS,"wall");
		
		grille[7][1]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[7][2]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[7][9]=new Obstacle (QLearningPara.MALUS,"wall");
		
		grille[8][3]=new Obstacle (QLearningPara.MALUS,"wall");
		
		grille[9][1]=new Obstacle (QLearningPara.MALUS,"wall");
		grille[9][7]=new Obstacle (QLearningPara.MALUS,"wall");
		
		grille[9][9]=t;
	}
	
	/**
	 * genere la carte pour A*
	 */
	public void initMapA_Star() {
		grille[6][5]=new Hole(6,5,"Triangle");
		grille[5][3]=new Hole(5,3,"Square");
		grille[2][4]=new Hole(2,4,"Circle");
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
		return nbCol;
	}
	
	public int getHeight() {
		return nbLig;
	}

	/**
	 * Affiche la carte selon l'environnement du QLearning
	 */
	public void printMapQLearning() {
		System.out.println();
		for (int i = 0; i < nbLig; i++) {
			for (int j = 0; j < nbCol; j++) {
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
		for (int i = 0; i < nbLig; i++) {
			for (int j = 0; j < nbCol; j++) {
				if(i==x && j==y)
					System.out.print("X");
				
				else if(i==6 && j==5)
					System.out.print("^"); //on considere que c'est un triangle
				
				else if(i==5 && j==3)
					System.out.print("[]"); // on considere que c'est un carre
				
				else if(i==2 && j==4)
					System.out.print("O"); // on considere que c'est un rond
				
				else
					System.out.print("-");
				
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
