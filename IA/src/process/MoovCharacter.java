package process;
import data.Character;
/**
 * classe contenant les diff�rentes actions possible par notre IA
 * 
 *
 */
public class MoovCharacter {
	private Character IA;
	private int dimMap;
	
	public MoovCharacter(Character IA,int dimMap) {
		this.IA = IA;
		this.dimMap=dimMap;
	}
	public void moovUp(){
		if(IA.getCoordX()>0)
			IA.setCoordX(IA.getCoordX()-1);
	}
	public void moovDown(){
		if(IA.getCoordX()<Math.sqrt(dimMap)-1)
			IA.setCoordX(IA.getCoordX()+1);
	}
	public void moovLeft(){
		if(IA.getCoordY()>0)
			IA.setCoordY(IA.getCoordY()-1);
	}
	public void moovRight(){
		if(IA.getCoordY()<Math.sqrt(dimMap)-1)
			IA.setCoordY(IA.getCoordY()+1);
	}
}