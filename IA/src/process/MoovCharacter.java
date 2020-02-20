package process;
import data.Character;
/**
 * classe contenant les diff�rentes actions possible par notre IA
 * 
 *
 */
public class MoovCharacter {
	private Character IA;
	
	public MoovCharacter(Character IA) {
		this.IA = IA;
	}
	public void moovUp(){
		IA.setCoordX(IA.getCoordX()-1);
	}
	public void moovDown(){
		IA.setCoordX(IA.getCoordX()+1);
	}
	public void moovLeft(){
		IA.setCoordY(IA.getCoordY()-1);
	}
	public void moovRight(){
		IA.setCoordY(IA.getCoordY()+1);
	}
}