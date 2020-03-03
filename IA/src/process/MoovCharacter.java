package process;
import data.Character;
/**
 * classe contenant les diff�rentes actions possible par notre IA
 * 
 *
 */
public class MoovCharacter {
	
	public static void moovUp(Character IA,int dimMap){
		if(IA.getCoordX()>0)
			IA.setCoordX(IA.getCoordX()-1);
	}
	public static void moovDown(Character IA,int dimMap){
		if(IA.getCoordX()<Math.sqrt(dimMap)-1)
			IA.setCoordX(IA.getCoordX()+1);
	}
	public static void moovLeft(Character IA,int dimMap){
		if(IA.getCoordY()>0)
			IA.setCoordY(IA.getCoordY()-1);
	}
	public static void moovRight(Character IA,int dimMap){
		if(IA.getCoordY()<Math.sqrt(dimMap)-1)
			IA.setCoordY(IA.getCoordY()+1);
	}
}