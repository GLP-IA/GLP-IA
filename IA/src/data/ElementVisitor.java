package data;

import process.Map;

/**
 * Generic elements visitor supporting all type type.
 * 
 * 
 * @author Nathan VIRAYIE
 */
public interface ElementVisitor<E> {

	E visit(Target elem);

	E visit(Hole elem);

	E visit(Obstacle elem);

	E visit(EmptyCase elem);

	E visit(Character elem);

	E visit(Map elem);
}
