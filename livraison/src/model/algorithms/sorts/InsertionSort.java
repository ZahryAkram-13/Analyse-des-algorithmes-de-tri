package model.algorithms.sorts;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class InsertionSort<T extends Comparable<T>> extends SortFunction<T> {

	public InsertionSort(CustomList<T> list) {
		super(list);
	}

	@Override
	public CustomList<T> sort() {
		int i = 1;
		while (i < list.getSize()) {
			int j = i - 1;
			int target = i;
			while (j >= 0 && list.biggerThan(list.getCase(j), list.getCase(target))) {
				list.swap(target, j);
				super.fireChange();
				target = j;
				j--;
			}
			i++;
		}

		return list;
	}

	@Override
	public SortName name() {
		// TODO Auto-generated method stub
		return SortName.InsertionSort;
	}

}
