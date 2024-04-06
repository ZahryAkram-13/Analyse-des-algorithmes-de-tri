package model.algorithms.sorts;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class SelectionSort <T extends Comparable<T>> extends SortFunction<T> {

	public SelectionSort(CustomList<T> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomList<T> sort() {
		int n = this.list.getSize();
		for(int i = 0; i < n-1; i++) {
			int minIndex = i;
			for(int j = i+1; j < n; j++) {
				if(this.list.biggerThan(list.getCase(minIndex), this.list.getCase(j))) 
					minIndex = j;
					super.fireChange();
			}
			this.list.swap(minIndex, i);
			super.fireChange();
		}
		return this.list;
	}

	@Override
	public SortName name() {
		// TODO Auto-generated method stub
		return SortName.SelectionSort;
	}
	

}
