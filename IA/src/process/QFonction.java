package process;

public class QFonction {
	private QTable q;
	private double gamma; // exploration rate , d�termine l'importance des futures r�compenses , facteur 0 l'agent ne consid�ra que les r�compenses actuelles, un facteur approchant 1 il visera une r�compense �lev�e � long terme 
	private double alpha; // learning rate : facteur 0 emp�chera l'agent d'apprendre, facteur de 1 ne  consid�rerait que les informations les plus r�centes

	
	public QFonction(QTable q, double gamma, double alpha) {
		this.q = q;
		this.gamma = gamma;
		this.alpha = alpha;
	}
	
	/**
	 * Met � jour la QTable
	 * x et y sont les coord actuelle du perso
	 * @param x
	 * @param y
	 * oldX et oldY sont les anciennes coordonn� du perso
	 * @param oldX
	 * @param oldY
	 * 
	 * @param reward r�compense re�u lors du d�placement
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
			moov=0;//a boug� en haut
		if(x>oldX)
			moov=1;//a boug� en bas
		if(y<oldY)
			moov=2;//a boug� � gauche
		if(y>oldY)
			moov=3;//a boug� � droite
		
		/*
		 * calcul de la nouvelle esp�rance :
		 * Esp = Q (ancien �tat, action r�alis�e) + alpha * (R + gamma * Max (��tat actuel�, toutes les actions) - Q (ancien �tat, action r�alis�e))
		 * R : r�compense immediate (stoqu� dans la grille elle meme)
		 * Esp : c�est la valeur de l��tat dans la Qtable
		 */
		
		esp = q.getEsp(oldState,moov) + alpha *(reward + gamma * (maxQ - (q.getEsp(oldState,moov))));  
		q.setQTable(oldState,moov,esp);
	}
	
	
	
}
