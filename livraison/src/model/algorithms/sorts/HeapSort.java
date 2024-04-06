package model.algorithms.sorts;

import java.util.ArrayList;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;
import model.algorithms.structures.Heap;

public class HeapSort<T extends Comparable<T>> extends SortFunction<T> {

	public HeapSort(CustomList<T> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomList<T> sort() {
		Heap<T> heap = new Heap<>(list);
		return heap.sort();
	}

	@Override
	public SortName name() {
		return SortName.HeapSort;
	}

}
