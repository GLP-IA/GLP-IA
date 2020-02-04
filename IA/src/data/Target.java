package data;

public class Target extends Element{
	private boolean achieved;


	public Target(int coordX, int coordY, boolean achieved) {
		super(coordX, coordY);
		this.achieved = achieved;
	}


	public boolean isAchieved() {
		return achieved;
	}


	public void setAchieved(boolean achieved) {
		this.achieved = achieved;
	}

	
	
	

}
