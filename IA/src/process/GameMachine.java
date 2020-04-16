package process;

import data.MinMaxPara;
import data.Node_MinMax;

public class GameMachine {
	private int nbOfCoins;
	private MinMaxCore minmaxCore;
	private Node_MinMax currentNode;
	
	public GameMachine() {
		nbOfCoins = MinMaxPara.nbOfCoins;
		minmaxCore= new MinMaxCore();
		currentNode= minmaxCore.getTreeCore().get(0);
		minmaxCore.setWinner();
	     
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

	/**
	 * 
	 */
	public void KuriosTurn() {
		Node_MinMax bestChild = null;
			System.out.print("COINS = " + nbOfCoins+ "");
			//tour de Kurios
			bestChild = minmaxCore.findBestChild(currentNode);
			nbOfCoins = bestChild.getNbOfCoins();
			currentNode = bestChild;
		
			System.out.println(" after Kurios Turn COINS = " + nbOfCoins);
			if (minmaxCore.checkWin(currentNode))
				System.out.println("Kurios Win !");
	}
			// Tour de l'adversaire
			
	public void OpponentTurn(int choice) {
		System.out.print("COINS = " + nbOfCoins+ "");
		Node_MinMax secondPlayer = null;
		secondPlayer =minmaxCore.secondPlayerTurn(choice,currentNode);
		nbOfCoins = secondPlayer.getNbOfCoins();
		currentNode = secondPlayer;
		System.out.println(" after Opponent Turn COINS = " + nbOfCoins);
		
		if (minmaxCore.checkWin(currentNode))
			System.out.println("Kurios Win !");
	}		
				
		
	
		
	
}

	




