package process;
import data.Character;
import data.Element;

public class Grille {
	private int nbLig;
	private int nbCol;
	private Element [][]grille;
	private Character c;
	
	public Grille(int n, int p,Character c) {
		nbLig = n;
		nbCol = p;
		grille = new Element [nbLig][nbCol];
		this.c=c;
		
		for(int i = 0; i< nbLig; i ++) {
			for(int j = 0; j< nbCol; j ++) {
				grille [i][j] = null;
			}
		}
	}
	
	public Element getCase(int l, int c) {
		return grille[l][c];
	}
	
	/*public void placer (int l, int c, char t) {
		l = l - 1;
		c = c - 1;
		if(l<0 || c<0 || l>nbLig || c>nbCol) {
			System.out.println("Erreur de placement");
			return;
		}
		if(grille [l][c]== 0) {
			grille [l][c] = t;
		}
		else {
			System.out.println("Erreur, cette zone n'est pas vide");
		}
	}*/
	
	public void afficher() {
		System.out.println();
		for (int i = 0; i < nbLig; i++) {
			for (int j = 0; j < nbCol; j++) {
				if(i==c.getCoordX() && j==c.getCoordY())
					System.out.print("X");
				else if(grille[i][j]==null)
					System.out.print("-");
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.println();
	}

}
