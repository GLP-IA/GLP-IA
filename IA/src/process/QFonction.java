package process;

public class QFonction {
	private QTable q;
	private double gamma = 0.9; // exploration rate , détermine l'importance des futures récompenses , facteur 0 l'agent ne considéra que les récompenses actuelles, un facteur approchant 1 il visera une récompense élevée à long terme 
	private double alpha = 0.2; // learning rate : facteur 0 empêchera l'agent d'apprendre, facteur de 1 ne  considérerait que les informations les plus récentes
	private int mapWidth = 5;
	private int mapHeight = 5;
	private int mapCount = mapWidth * mapHeight;

	
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
		States s=new States(mapCount);//la dimension de la carte est supposé statique donc elle peut etre codé en dur
		int currentState=s.getState(x,y);
		int oldState=s.getState(oldX,oldY);
        double maxQ = max(currentState);
	
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
		
		//calcul de la nouvelle espérance
		esp = q.getEsp(oldState,moov) + alpha *(reward + gamma * (maxQ - (q.getEsp(oldState,moov))));  //vérifier si c'est la formule est complète
		q.setQTable(oldState,moov,esp);
	}
	
	public double max(int state) {
		double m=q.getEsp(state,0);
			for(int j=1;j<4.;j++) {
				if(m<q.getEsp(state,j))
					m=q.getEsp(state,j);
			}
		return m;
	}
	
}
