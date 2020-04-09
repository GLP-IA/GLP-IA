package process;

import data.QLearningPara;

/**
 *colonne 0: UP
 *colonne 1: DOWN
 *colonne 2: LEFT
 *colonne 3: RIGHT
 *
 *les lignes de la QTable sont definit de la maniere suivante:
 *ligne 0 <=> case 0 <=> (0,0); ligne 1 <=> case 1 <=> (0,1)...
 *
 *(la carte est supposé carré)
 */
public class QTable {
	private double qTable[][];

	public QTable() {
		qTable = new double [QLearningPara.DIM_MAP][4];
		initQTable(); 
	}

	/**
	 * set the value of the qtable cases at 0
	 * and define the border of the map at -900 
	 */
	public void initQTable() {
		String bound = "";
		int edge=(int) Math.sqrt(QLearningPara.DIM_MAP);
		
		for(int i=0;i<QLearningPara.DIM_MAP;i++){
			for(int j=0;j<4.;j++) {
				bound = String.valueOf(i);
				if(i%edge == 0 || i==0)
					qTable[i][2]=-900;//block moovLeft when we are on the left side
				if(i<edge)
					qTable[i][0]=-900; //block moovUp when we are on the top line
				if(i>=90) 
					qTable[i][1]=-900; //block moovDown when we are on the lowest line	
				if (i == edge-1)
					qTable[i][3]=-900; // block moovRight when we are on the right side of the first line
				 if (i>edge) { 
					if(bound.substring(2).equals("9"))
						qTable[i][3]=-900; //block moovRight when we are on the right side of the other lines	
				}
				else
					qTable[i][j]=0; //default value
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

	public String print() {
		String r="NextMoov:	UP	DOWN	LEFT	RIGHT\n";
		 
		for (int i = 0; i <QLearningPara.DIM_MAP; i++) {
			r+="From state " + i + ":  ";
			for (int j = 0; j <4; j++) {
				r+=qTable[i][j]+"	";
			}
			r+="\n";
		}
		
		return r;
	}
}
