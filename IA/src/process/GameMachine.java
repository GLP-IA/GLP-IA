package process;

import data.Player;

public class GameMachine {
	private Player player1;
	private Player player2;
	
	public GameMachine(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	/**
	 * Définit les règles du jeu 
	 */
	public void rules() {
		int score1=player1.getScores();
		int score2=player2.getScores();
		
		if(player1.cheat() && !player2.cheat()) {
			player1.setScore(score1+2);
			player2.setScore(score2-1);
		}
		else if(!player1.cheat() && player2.cheat()) {
			player1.setScore(score1-1);
			player2.setScore(score2+2);
		}
		else if(!player1.cheat() && !player2.cheat()) {
			player1.setScore(score1+1);
			player2.setScore(score2+1);
		}
	}
}
