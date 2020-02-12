package process;

import java.util.Arrays;

/**
 *colonne 1: UP
 *colonne 2: DOWN
 *colonne 3: LEFT
 *colonne 4: RIGHT
 *
 *les lignes sont définit de la manière suivante:
 *ligne 0 <=> case 0 <=> (0,0); ligne 1 <=> case 1 <=> (0,1)...
 *
 */
public class QTable {
	private float qTable[][];
	private int dimMap;

	public QTable(int dimMap) {
		this.dimMap = dimMap;
		
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
	 * @param x,y correspondent au coord de la case où l'on v'a modifier l'espérance
	 * @param esp espérance de la case
	 */
	public void setQTable(int x, int y, float esp) { // on peut définir que (0,0) <=> case 1 comme ça on peut passer en parametre case*
		qTable[x][y]=esp;
	}

	@Override
	public String toString() {
		System.out.println();
		for (int i = 0; i < sqrt(dimMap); i++) {
			for (int j = 0; j < sqrt(dimMap); j++) {
				System.out.print("|" + qTable[i][j]);
			}
			System.out.println("|");
		}
		System.out.println();
	}
	}
}
