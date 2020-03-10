package process;

import data.QLearningPara;

/**
 *colonne 0: UP
 *colonne 1: DOWN
 *colonne 2: LEFT
 *colonne 3: RIGHT
 *
 *les lignes sont dï¿½finit de la maniï¿½re suivante:
 *ligne 0 <=> case 0 <=> (0,0); ligne 1 <=> case 1 <=> (0,1)...
 * 
 */
public class QTable {
	private double qTable[][];

	public QTable() {
		qTable = new double [QLearningPara.DIM_MAP][4];
		String test = "";
		for(int i=0;i<QLearningPara.DIM_MAP;i++){
			for(int j=0;j<4.;j++) {
				test = String.valueOf(i);
				if(i%10 == 0 || i==0)
					qTable[i][2]=-900;//moovLeft quand on est a gauche
				if(i<10)
					qTable[i][0]=-900; //moovUp quand on est en haut
				if(i>=90) 
					qTable[i][1]=-900; //moovDown quand on est en bas	
				if (i == 9)
					qTable[i][3]=-900; //moovRight quand on est a droite
				 if (i>10) { 
					if(test.substring(2).equals("9"))
						qTable[i][3]=-900; //moovRight quand on est a droite	
				}
				else
					qTable[i][j]=0;
			}
		}
		
	}

	public double getEsp(int square,int moov) {
		return qTable[square][moov];
	}
	/**
	 * 
	 * @param case on peut dï¿½finir que (0,0) <=> case 0 comme ï¿½a on peut passer en parametre case*
	 * @param moov correspond au déplacement choisis (voir au dessus comment est définis chaque mouvement)
	 * @param esp espï¿½rance de la case
	 */
	public void setQTable(int square, int moov, double esp) {
		qTable[square][moov]=esp;
	}
	
	/**
	 * Donne l'espérance max à un état(case) donné
	 * 
	 * @param state case où l'on se trouve
	 * @return l'espérance maximal à l'état donné
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
	 * Permet de savoir dans quel direction il faut aller pour avoir la meilleur espérance
	 * 
	 * @param state case où l'on se trouve
	 * @return la direction où l'espérance est la plus élevé
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
		for (int i = 0; i <QLearningPara.DIM_MAP; i++) {
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
