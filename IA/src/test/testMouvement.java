package test;
import process.Grille;
import data.Character;
import data.Score;
import process.MoovCharacter;

public class testMouvement {

	public static void main(String[] args) {
		Score s = new Score();
		Character c = new Character(0,0,s);
		MoovCharacter mv =new MoovCharacter(c);
		
		Grille g1 = new Grille(15, 15, c);
		
		g1.afficher();
		mv.moovUp();
		System.out.print("(" + c.getCoordX() + ";" + c.getCoordY() + ")");
		g1.afficher();
	}

}
