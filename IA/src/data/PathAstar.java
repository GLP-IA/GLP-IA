package data;

import java.util.ArrayList;

/**
 * Cette classe sert d'historique des noeuds analyser par Astar
 * 
 * @author Nathan Virayie
 */
public class PathAstar {
	private ArrayList<Trail> path;

	public PathAstar() {
		path = new ArrayList<Trail>();
	}

	public ArrayList<Trail> getPath() {
		return path;
	}

	public void addToPath(Trail trail) {
		path.add(trail);
	}
	
	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
