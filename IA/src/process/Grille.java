package process;

import data.Element;
import data.Hole;
import data.Obstacle;
import data.Target;

public class Grille {
	private int nbLig;
	private int nbCol;
	private Element [][]grille;
	private int x;
	private int y;
	private static int malus = -500;
	
	public Grille(int n, int p,int x, int y) {
		nbLig=n;
		nbCol=p;
		grille = new Element [n][p];
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
	 * Génère la carte pour le qLearning
	 * 
	 * @param x coord en X de l'objectif
	 * @param y coord en Y de l'objectif
	 * @param t objectif
	 */
	public void initMapQLearning (Target t) {
		grille[0][1]=new Obstacle (malus,"wall");
		grille[1][1]=new Obstacle (malus,"wall");
		grille[1][3]=new Obstacle (malus,"wall");
		grille[2][3]=new Obstacle (malus,"wall");
		grille[3][0]=new Obstacle (malus,"wall");
		grille[3][2]=new Obstacle (malus,"wall");
		grille[3][3]=new Obstacle (malus,"wall");
		grille[x][y]=t;
	}
	
	public void initMapA_Star() {
		grille[6][5]=new Hole(6,5,"Triangle");
		grille[5][3]=new Hole(5,3,"Square");
		grille[2][4]=new Hole(2,4,"Circle");
	}
	
	public void hasMooved(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	/**
	 * 
	 * @return pos en x du perso
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return pos en Y du perso
	 */
	public int getY() {
		return y;
	}

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

	public void printMapA_Star() {
		System.out.println();
		for (int i = 0; i < nbLig; i++) {
			for (int j = 0; j < nbCol; j++) {
				if(i==x && j==y)
					System.out.print("X");
				
				else if(i==6 && j==5)
					System.out.print("^"); //on considere que c'est un triangle
				
				else if(i==5 && j==3)
					System.out.print("[]"); // on considere que c'est un carré
				
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
