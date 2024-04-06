package model.algorithms.sorts;

import java.util.ArrayList;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;
import model.algorithms.structures.BinaryTree;


public class BinaryTreeSort <T extends Comparable<T>> extends SortFunction<T> {

	public BinaryTreeSort(CustomList<T> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public CustomList<T> sort() {
		BinaryTree<T> tree = BinaryTree.builtTree(list);
		tree.traverseInOrder(tree.getRoot());
		return tree.getSorted();
	}

	@Override
	public SortName name() {
		return SortName.BinaryTreeSort;
	}

}
