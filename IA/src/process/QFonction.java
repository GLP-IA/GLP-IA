package process;

public class QFonction {
	private QTable q;
	private double gamma = 0.9; // exploration rate , d�termine l'importance des futures r�compenses , facteur 0 l'agent ne consid�ra que les r�compenses actuelles, un facteur approchant 1 il visera une r�compense �lev�e � long terme 
	private double alpha = 0.2; // learning rate : facteur 0 emp�chera l'agent d'apprendre, facteur de 1 ne  consid�rerait que les informations les plus r�centes
	private int mapWidth = 5;
	private int mapHeight = 5;
	private int mapCount = mapWidth * mapHeight;

	
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
		States s=new States(mapCount);//la dimension de la carte est suppos� statique donc elle peut etre cod� en dur
		int currentState=s.getState(x,y);
		int oldState=s.getState(oldX,oldY);
        double maxQ = max(currentState);
	
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
		
		//calcul de la nouvelle esp�rance
		esp = q.getEsp(oldState,moov) + alpha *(reward + gamma * (maxQ - (q.getEsp(oldState,moov))));  //v�rifier si c'est la formule est compl�te
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
