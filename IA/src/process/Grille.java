package process;

import data.Element;

public class Grille {
	private int nbLig;
	private int nbCol;
	private Element [][]grille;
	private int x;
	private int y;
	
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
	
	public void placer (int l, int c, Element e) {
		if(l<0 || c<0 || l>nbLig || c>nbCol) {
			System.out.println("Erreur de placement");
		}
		if(getCase(l,c).getReward()== 0) {
			grille [l][c] = e;
		}
		else {
			System.out.println("Erreur, cette zone n'est pas vide");
		}
	}
	
	public void hasMooved(int x, int y) {
		this.x=x;
		this.y=y;
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
