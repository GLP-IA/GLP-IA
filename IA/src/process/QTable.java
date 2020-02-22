package process;

/**
 *colonne 1: UP
 *colonne 2: DOWN
 *colonne 3: LEFT
 *colonne 4: RIGHT
 *
 *les lignes sont d�finit de la mani�re suivante:
 *ligne 0 <=> case 0 <=> (0,0); ligne 1 <=> case 1 <=> (0,1)...
 * 
 */
public class QTable {
	private double qTable[][];
	private int dimMap;

	public QTable(int dimMap) {
		this.dimMap = dimMap;
		qTable = new double [dimMap][4];
		for(int i=0;i<dimMap;i++){
			for(int j=0;j<4.;j++) {
				if(i==5%2 || i==0)
					qTable[i][2]=-900;//moovLeft quand on est a gauche
				if(i<5)
					qTable[i][0]=-900; //moovUp quand on est en haut
				if(i>=20)
					qTable[i][1]=-900; //moovDown quand on est en bas
				else
					qTable[i][j]=0;
			}
		}
		//definition des limites de la carte 5x5
		qTable[4][3]=-900;
		qTable[9][3]=-900;
		qTable[14][3]=-900;
		qTable[24][3]=-900;
	}

	public double getEsp(int square,int moov) {
		return qTable[square][moov];
	}
	/**
	 * 
	 * @param case on peut d�finir que (0,0) <=> case 0 comme �a on peut passer en parametre case*
	 * @param moov correspond au d�placement choisis (voir au dessus comment est d�finis chaque mouvement)
	 * @param esp esp�rance de la case
	 */
	public void setQTable(int square, int moov, double esp) {
		qTable[square][moov]=esp;
	}
	
	/**
	 * Donne l'esp�rance max � un �tat(case) donn�
	 * 
	 * @param state case o� l'on se trouve
	 * @return l'esp�rance maximal � l'�tat donn�
	 */
	public double max(int state) {
		double m=qTable[state][0];
			for(int j=1;j<4.;j++) {
				if(m<qTable[state][j])
					m=qTable[state][j];
			}
		return m;
	}

	/**
	 * Permet de savoir dans quel direction il faut aller pour avoir la meilleur esp�rance
	 * 
	 * @param state case o� l'on se trouve
	 * @return la direction o� l'esp�rance est la plus �lev�
	 */
	public int maxDirection(int state) {
		int j=0;
		while(qTable[state][j]!=max(state))
			j++;
	return j;
	}
	
	public void afficher() {
		 System.out.printf("%10s", "NextMoov: ");
	            System.out.printf("%10s", "UP");
	            System.out.printf("%8s", "DOWN");
	            System.out.printf("%8s", "LEFT");
	            System.out.printf("%8s", "RIGHT");

	        
	        System.out.println();
		for (int i = 0; i <dimMap; i++) {
			System.out.print("From state " + i + ":  ");
			for (int j = 0; j <4; j++) {
				//System.out.print(qTable[i][j]+" ");
				System.out.printf("%6.2f ",(qTable[i][j]));
			}
			System.out.println();
		}
		
		System.out.printf("%10s", "NextMoov: ");
        System.out.printf("%10s", "UP");
        System.out.printf("%8s", "DOWN");
        System.out.printf("%8s", "LEFT");
        System.out.printf("%8s", "RIGHT");
        System.out.println();
	}
}
