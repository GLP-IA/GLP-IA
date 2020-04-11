package data;

import process.Map;

public interface ElementVisitor<E> {

	E visit(Target elem);

	E visit(Hole elem);

	E visit(Obstacle elem);

	E visit(EmptyCase elem);

	E visit(Character elem);

	E visit(Map elem);

	E visit(GoodBox trail);

	E visit(WrongBox trail);
	
	E visit(AnalyzedBox trail);
	
	E visit(PathAstar path);
	
}