package process;

import data.TreeNode;

/**
 * Generic tree visitor supporting all type type.
 * 
 * 
 * @author Tianxiao.Liu@u-cergy.fr
 **/
public interface TreeVisitor<T> {

	T visit(TreeNode node);

}