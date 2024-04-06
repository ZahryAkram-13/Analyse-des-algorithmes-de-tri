package model.algorithms.sorts;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class QuickSort<T extends Comparable<T>> extends SortFunction<T> {

	public QuickSort(CustomList<T> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomList<T> sort() {
		return this.ActualQuickSort(list, 0, list.getSize() - 1);
	}

	@Override
	public SortName name() {
		return SortName.QuickSort;
	}

	private CustomList<T> ActualQuickSort(CustomList<T> list, int start, int end) {
		if (start < end) {
			int partitionIndex = this.partition(list, start, end);
			ActualQuickSort(list, start, partitionIndex - 1);
			ActualQuickSort(list, partitionIndex + 1, end);
		}
		return list;
	}

	private int partition(CustomList<T> list, int start, int end) {
		T pivot = list.getCase(end);
		int partitionIndex = start;
		for (int i = start; i < end; i++) {
			if (list.biggerThan(pivot, list.getCase(i))) {
				list.swap(partitionIndex, i);
				super.fireChange();
				partitionIndex++;
			}
		}
		list.swap(partitionIndex, end);
		return partitionIndex;

	}

}
