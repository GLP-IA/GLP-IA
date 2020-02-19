package process;
/**
 * retourne le numéro d'un case donr on connais l'abscisse et l'ordonné
 *
 */
public class States {
	private int dimMap;

	public States(int dimMap) {
		this.dimMap = dimMap;
	}
	/**
	 * 
	 * @param x abscisse de la case
	 * @param y  ordonné de la case
	 * @return la case en numéroté
	 */
	public int getState(int x, int y) {
		int state=0;
		int max=(int)Math.sqrt(dimMap);
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