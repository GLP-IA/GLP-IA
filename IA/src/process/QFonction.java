package process;

public class QFonction {
	private QTable q;
	private double explorationRate;
	private double rewardMatter;
	
	public QFonction(QTable q, double explorationRate, double rewardMatter) {
		this.q = q;
		this.explorationRate = explorationRate;
		this.rewardMatter = rewardMatter;
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
		States s=new States(9);//la dimension de la carte est supposé statique donc elle peut etre codé en dur
		int currentState=s.getState(x,y);
		int oldState=s.getState(oldX,oldY);
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
		esp=(1-explorationRate)*q.getEsp(oldState,moov)+explorationRate*(reward+rewardMatter*q.getEsp(currentState,moov));//vérifier si c'est la formule est complète
		q.setQTable(oldState,moov,esp);
	}
	
}
