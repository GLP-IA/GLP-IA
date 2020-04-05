package data;

import process.TreeVisitor;

/**
 * General definition of a Tree node.
 * 
 * @author Nathan Virayie
 */
public interface Tree {
	/**
	 * Gets the left sub-tree of the node.
	 * 
	 * @return the left sub-tree
	 */
	Tree getLeftNode();

	/**
	 * Gets the right sub-tree of the node.
	 * 
	 * @return the right sub-tree
	 */
	Tree getRightNode();

	/**
	 * A tree node can accept the visit of any {@link TreeVisitor}.
	 * 
	 * @param visitor
	 *            the tree visitor of generic type
	 * @return the generic return type that depends on the concret return type
	 *         of the tree visitor
	 * 
	 */
	<T> T accept(TreeVisitor<T> visitor);
}
