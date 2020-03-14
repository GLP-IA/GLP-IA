package process;

public class QFonction {
	private QTable q;
	private double gamma; // exploration rate , détermine l'importance des futures récompenses , facteur 0 l'agent ne considéra que les récompenses actuelles, un facteur approchant 1 il visera une récompense élevée à long terme 
	private double alpha; // learning rate : facteur 0 empêchera l'agent d'apprendre, facteur de 1 ne  considérerait que les informations les plus récentes

	
	public QFonction(QTable q, double gamma, double alpha) {
		this.q = q;
		this.gamma = gamma;
		this.alpha = alpha;
	}
	
	/**
	 * Met à jour la QTable
	 * x et y sont les coord actuelle du perso
	 * @param x
	 * @param y
	 * oldX et oldY sont les anciennes coordonné du perso
	 * @param oldX
	 * @param oldY
	 * 
	 * @param reward récompense reçu lors du déplacement
	 */
	public void update(int x, int y,int oldX, int oldY,int reward) {
		int moov =0;
		int currentState=States.getState(x,y);
		int oldState=States.getState(oldX,oldY);
        double maxQ = q.max(currentState);
	
		//System.out.println(currentState+" "+oldState);
		double esp;
		//on suppose que le surplace est impossible
		if(x<oldX)
			moov=0;//a bougé en haut
		if(x>oldX)
			moov=1;//a bougé en bas
		if(y<oldY)
			moov=2;//a bougé à gauche
		if(y>oldY)
			moov=3;//a bougé à droite
		
		/*
		 * calcul de la nouvelle espérance :
		 * Esp = Q (ancien état, action réalisée) + alpha * (R + gamma * Max ( état actuel , toutes les actions) - Q (ancien état, action réalisée))
		 * R : récompense immediate (stoqué dans la grille elle meme)
		 * Esp : c’est la valeur de l’état dans la Qtable
		 */
		
		esp = q.getEsp(oldState,moov) + alpha *(reward + gamma * (maxQ - (q.getEsp(oldState,moov))));  
		q.setQTable(oldState,moov,esp);
	}
	
	
	
}
