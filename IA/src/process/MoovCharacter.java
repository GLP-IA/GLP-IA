package process;
import data.Character;
import data.Element;
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
		Element pos;
		pos=IA.getPosition();
		pos.setCoordX(pos.getCoordX()-1);
		IA.setPosition(pos);
	}
	public void moovDown(){
		Element pos;
		pos=IA.getPosition();
		pos.setCoordX(pos.getCoordX()+1);
		IA.setPosition(pos);
	}
	public void moovRight(){
		Element pos;
		pos=IA.getPosition();
		pos.setCoordY(pos.getCoordY()+1);
		IA.setPosition(pos);
	}
	public void moovLeft(){
		Element pos;
		pos=IA.getPosition();
		pos.setCoordY(pos.getCoordY()-1);
		IA.setPosition(pos);
	}
}