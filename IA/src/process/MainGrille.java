package process;

import process.Grille;

public class MainGrille {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Grille g1 = new Grille(15, 15);
		
		g1.placer(3, 7, 'X');
		
		g1.afficher();
		g1.placer(3, 7, 'O');
		g1.afficher();
	}

}
