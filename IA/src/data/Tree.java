package data;

import process.TreeVisitor;

/**
 * This class represents all arithmetic operations including Addition,
 * Subtraction and Multiplication. All these operations need two operands
 * (sub-trees) to process the calculation.
 * 
 * The class uses Template Method design pattern. The common part (left operand
 * and right operand) is defined in this class and is shared in the sub-classes.
 * 
 * No default toString() defined.
 * 
 * @author Nathan Virayie
 */
public class Tree {
	private int value;
	/**
	 * Left operand of the operation (sub-tree)
	 */
	private Tree leftNode;

	/**
	 * Right operand of the operation (sub-tree)
	 */
	private Tree rightNode;

	/**
	 * 
	 * @param leftOperand
	 *            left sub-tree
	 * @param rightOperand
	 *            right sub-tree
	 */
	public Tree(int value, Tree leftNode, Tree rightNode) {
		this.value=value;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public Tree getLeftNode() {
		return leftNode;
	}

	public Tree getRightNode() {
		return rightNode;
	}

	public void setRightNode(Tree rightOperand) {
		this.rightNode = rightOperand;
	}

	public void setLeftNode(Tree leftOperand) {
		this.leftNode = leftOperand;
	}

	public <T> T accept(TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
