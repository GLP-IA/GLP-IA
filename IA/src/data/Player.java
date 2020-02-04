package data;

public class Player {
	
	private boolean hasCheat;
	private Score scores;
	
	
	public Player(boolean hasCheat, Score scores) {
		this.hasCheat = hasCheat;
		this.scores = scores;
	}


	public boolean isHasCheat() {
		return hasCheat;
	}


	public Score getScores() {
		return scores;
	}


	public void setHasCheat(boolean hasCheat) {
		this.hasCheat = hasCheat;
	}


	
	
	
	

}
