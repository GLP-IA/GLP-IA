package data;

public class Character {
	private Element position;
	private Score scores;
	
	public Character(Element position, Score scores) {
		super();
		this.position = position;
		this.scores = scores;
	}
	
	public Element getPosition() {
		return position;
	}

	public Score getScores() {
		return scores;
	}

	public void setPosition(Element position) {
		this.position = position;
	}

}
