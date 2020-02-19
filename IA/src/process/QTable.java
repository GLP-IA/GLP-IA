package process;

/**
 *colonne 1: UP
 *colonne 2: DOWN
 *colonne 3: LEFT
 *colonne 4: RIGHT
 *
 *les lignes sont dï¿½finit de la maniï¿½re suivante:
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
	}

	public double getEsp(int square,int moov) {
		return qTable[square][moov];
	}
	/**
	 * 
	 * @param case on peut dï¿½finir que (0,0) <=> case 1 comme ï¿½a on peut passer en parametre case*
	 * @param moov correspond au déplacement choisis (voir au dessus comment est définis chaque mouvement)
	 * @param esp espï¿½rance de la case
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
