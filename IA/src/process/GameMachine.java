package process;

import data.MinMaxPara;
import data.Node_MinMax;

/**
 * 
 * @author Nathan Virayie
 */
public class GameMachine {
	private int nbOfCoins;
	private MinMaxCore minmaxCore;
	private Node_MinMax currentNode;
	
	public GameMachine() {
		nbOfCoins = MinMaxPara.nbOfCoins;
		minmaxCore= new MinMaxCore();
		currentNode= minmaxCore.getTreeCore().get(0);
		minmaxCore.evaluate();     
	}

	public int getNbOfCoins() {
		return nbOfCoins;
	}
		
	public Node_MinMax getCurrentNode() {
		return currentNode;
	}

	public MinMaxCore getMinmaxCore() {
		return minmaxCore;
	}

	public void KuriosTurn() {
		Node_MinMax bestChild = null;
		bestChild = minmaxCore.findBestChild(currentNode);
		nbOfCoins = bestChild.getNbOfCoins();
		currentNode = bestChild;
	}
			
	public void OpponentTurn(int choice) {
		Node_MinMax secondPlayer = null;
		secondPlayer =minmaxCore.secondPlayerTurn(choice,currentNode);
		nbOfCoins = secondPlayer.getNbOfCoins();
		currentNode = secondPlayer;
	}	
	
	public boolean whoHasWin() {
		return minmaxCore.checkWin(currentNode);
	}
}