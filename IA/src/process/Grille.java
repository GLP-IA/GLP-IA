package process;

import data.Element;
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
	 * Génère la carte
	 * 
	 * @param x coord en X de l'objectif
	 * @param y coord en Y de l'objectif
	 * @param t objectif
	 */
	public void initMap (int x, int y,Target t) {
		/*if(l<0 || c<0 || l>nbLig || c>nbCol) {
			System.out.println("Erreur de placement");
		}
		if(getCase(l,c).getReward()== 0) {
			grille [l][c] = e;
		}
		else {
			System.out.println("Erreur, cette zone n'est pas vide");
		}*/
		
		//pacement des obstacles
		grille[0][1]=new Obstacle (malus,"wall");
		grille[1][1]=new Obstacle (malus,"wall");
		grille[1][3]=new Obstacle (malus,"wall");
		grille[2][3]=new Obstacle (malus,"wall");
		grille[3][0]=new Obstacle (malus,"wall");
		grille[3][2]=new Obstacle (malus,"wall");
		grille[3][3]=new Obstacle (malus,"wall");
		grille[x][y]=t;
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

	public void afficher() {
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

}
