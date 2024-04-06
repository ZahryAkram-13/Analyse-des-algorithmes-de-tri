package model.algorithms.sorts;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class ShellSort <T extends Comparable<T>> extends SortFunction<T> {

	public ShellSort(CustomList<T> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomList<T> sort() {
		int size = list.getSize();
		int n = 0;

		while (n < size) {
			n = 3 * n + 1;
		}
		while (n != 0) {
			n = n / 3;
			for (int i = n; i < size; i++) {
				T value = list.getCase(i);
				int j = i;
				while ((j > (n - 1)) && (list.biggerThan(list.getCase(j-n), value))){
					list.set(j, list.getCase(j - n));
					super.fireChange();
					j = j - n;
				}
				list.set(j, value);
				super.fireChange();
			}
		}
		return list;
	}

	@Override
	public SortName name() {
		return SortName.ShellSort;
	}
	

}
