package process;

import data.Tree;

/**
 * Generic tree visitor supporting all type type.
 * 
 * 
 * @author Tianxiao.Liu@u-cergy.fr
 **/
public interface TreeVisitor<T> {

	T visit(Tree node);

}