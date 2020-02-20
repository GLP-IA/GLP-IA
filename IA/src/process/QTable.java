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
			for(int j=0;j<4.;j++)
				qTable[i][j]=0;
		}
		/*definition des limites de la carte 5x5
		 * qTable[0][0]=-900;
		 * qTable[0][1]=-900;
		 * qTable[4][0]=-900;
		 * qTable[4][3]=-900;
		 * qTable[5][2]=-900;
		 * qTable[9][3]=-900;
		 * qTable[10][2]=-900;
		 * qTable[14][3]=-900;
		 * qTable[15][2]=-900;
		 * qTable[19][3]=-900;
		 * qTable[20][2]=-900;
		 * qTable[20][1]=-900;
		 * qTable[24][3]=-900;
		 * qTable[24][1]=-900;
		 */
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

	public void afficher() {
		for (int i = 0; i <dimMap; i++) {
			for (int j = 0; j <4; j++) {
				System.out.print(qTable[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
