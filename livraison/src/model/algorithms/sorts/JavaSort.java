package model.algorithms.sorts;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;
import java.util.Collections;

public class JavaSort<T extends Comparable<T>> extends SortFunction<T> {

	public JavaSort(CustomList<T> list) {
		super(list);
	}

	@Override
	public CustomList<T> sort() {
		Collections.sort(list.getList());
		super.fireChange();
		return this.list;
	}

	@Override
	public SortName name() {
		return SortName.JavaSort;
	}

}
