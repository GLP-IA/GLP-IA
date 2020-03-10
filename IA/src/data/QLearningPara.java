package data;

public class QLearningPara {
	public static final int DIM_MAP =100;
	
	public static double GAMMA = 0.9; // exploration rate , détermine l'importance des futures récompenses , facteur 0 l'agent ne considéra que les récompenses actuelles, un facteur approchant 1 il visera une récompense élevée à long terme 
	
	public static double ALPHA = 0.2; // learning rate : facteur 0 empêchera l'agent d'apprendre, facteur de 1 ne  considérerait que les informations les plus récentes

	public static final int MALUS = -500;
	
	public static final int REWARD = 100;
}
