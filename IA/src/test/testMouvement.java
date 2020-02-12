package test;
import process.Grille;
import data.Character;
import data.Element;
import data.Score;
import process.MoovCharacter;

public class testMouvement {

	public static void main(String[] args) {
		Element pos= new Element(2,1);
		Score s = new Score();
		Character c = new Character(pos,s);
		MoovCharacter mv =new MoovCharacter(c);
		
		Grille g1 = new Grille(15, 15, c);
		
		g1.afficher();
		mv.moovUp();
		System.out.print("(" + c.getPosition().getCoordX() + ";" + c.getPosition().getCoordY() + ")");
		g1.afficher();
	}

}
