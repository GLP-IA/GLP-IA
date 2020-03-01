package test;

import process.Grille;

public class TestA_Star {
	private static int mapWidth = 10;
	private static int mapHeight = 10;
	
	
	public static void main(String[] args) {
		Grille g = new Grille(mapWidth, mapHeight,0,0); 
		
		g.initMapA_Star();//initalise la carte
		
		g.printMapA_Star();
	}
}
