package model.algorithms.sorts;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class StoogeSort <T extends Comparable<T>> extends SortFunction<T> {

	public StoogeSort(CustomList<T> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomList<T> sort() {
		// TODO Auto-generated method stub
		return this.ActualStoogeSort(list, 0, list.getSize() - 1);
	}
	
	

	@Override
	public SortName name() {
		// TODO Auto-generated method stub
		return SortName.StoogeSort;
	}
	
	private CustomList<T> ActualStoogeSort(CustomList<T> list, int i, int j) { // O(n^2.7)
		T ci = list.getCase(i);
		T cj = list.getCase(j);
		if (list.biggerThan(ci, cj)) {
			list.swap(i, j);
			super.fireChange();
		}
		if (j - i + 1 > 2) {
			int quarter = Math.floorDiv(j - i + 1, 3);
			this.ActualStoogeSort(list, i, j - quarter);
			super.fireChange();
			this.ActualStoogeSort(list, i + quarter, j);
			super.fireChange();
			this.ActualStoogeSort(list, i, j - quarter);
			super.fireChange();
		}

		return list;

	}

}
