package process;

import data.QLearningPara;

/**
 * retourne le numéro d'un case dont on connais l'abscisse et l'ordonné
 *
 */
public class States {
	/**
	 * @param x abscisse de la case
	 * @param y  ordonné de la case
	 * @return la case en numéroté
	 */
	public static int getState(int x, int y) {
		int state=0;
		int max=(int)Math.sqrt(QLearningPara.DIM_MAP);
		for(int i=0; i<max;i++) {
			for(int j=0; j<max;j++) {
				if(i==x && j==y)
					return state;
				else
					state++;
			}
		}
		return state;
	}
}