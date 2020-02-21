package test;
import process.Grille;
import data.Character;
import data.Score;
import process.MoovCharacter;

public class testMouvement {

	public static void main(String[] args) {
		Score s = new Score();
		Character c = new Character(0,0,s);
		MoovCharacter mv =new MoovCharacter(c,25);
		
		Grille g1 = new Grille(5, 5,c.getCoordX(),c.getCoordY());
		
		g1.afficher();
		mv.moovRight();
		g1.hasMooved(c.getCoordX(),c.getCoordY());
		System.out.print("(" + c.getCoordX() + ";" + c.getCoordY() + ")");
		g1.afficher();
	}

}
