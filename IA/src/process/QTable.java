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
	private float qTable[][];
	private int dimMap;

	public QTable(int dimMap) {
		this.dimMap = dimMap;
		qTable = new float [dimMap][4];
		for(int i=0;i<dimMap;i++){
			for(int j=0;j<4.;j++)
				qTable[i][j]=0;
		}
	}

	public float[][] getQTable() {
		return qTable;
	}
	/**
	 * 
	 * @param x,y correspondent au coord de la case o� l'on v'a modifier l'esp�rance
	 * @param esp esp�rance de la case
	 */
	public void setQTable(int x, int y, float esp) { // on peut d�finir que (0,0) <=> case 1 comme �a on peut passer en parametre case*
		qTable[x][y]=esp;
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
