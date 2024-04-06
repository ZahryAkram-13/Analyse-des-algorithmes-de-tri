package model.algorithms.sorts;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class BubbleSort <T extends Comparable<T>> extends SortFunction<T> {

	public BubbleSort(CustomList<T> list) {
		super(list);
	}

	@Override
	public CustomList<T> sort() {
		int size = list.getSize();
		for (int i = 0; i < size; i++) {
			boolean changed = false;
			for (int j = 1; j < size - i; j++) {
				if (list.biggerThan(list.getCase(j - 1), list.getCase(j))) {
					list.swap(j - 1, j);
					super.fireChange();
					changed = true;
				}
			}
			if (!changed)
				break;
		}
		return list;
	}

	@Override
	public SortName name() {
		// TODO Auto-generated method stub
		return SortName.BubbleSort;
	}

}
