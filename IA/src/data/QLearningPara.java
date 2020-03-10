package data;

public class QLearningPara {
	public static final int DIM_MAP =100;
	
	public static double GAMMA = 0.9; // exploration rate , d�termine l'importance des futures r�compenses , facteur 0 l'agent ne consid�ra que les r�compenses actuelles, un facteur approchant 1 il visera une r�compense �lev�e � long terme 
	
	public static double ALPHA = 0.2; // learning rate : facteur 0 emp�chera l'agent d'apprendre, facteur de 1 ne  consid�rerait que les informations les plus r�centes

	public static final int MALUS = -500;
	
	public static final int REWARD = 100;
}
