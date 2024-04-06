package model.algorithms.sorts;

import java.util.ArrayList;
import java.util.List;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class FusionSort<T extends Comparable<T>> extends SortFunction<T> {

	public FusionSort(CustomList<T> list) {
		super(list);
	}

	@Override
	public CustomList<T> sort() {
		return this.TriFusion(list);
	}

	@Override
	public SortName name() {
		return SortName.FusionSort;
	}

	public static void test() {
		CustomList<Integer> list = CustomList.generateIntegerList(10);
		SortFunction<Integer> fusionsort = new FusionSort<>(list);

	}

	
	private CustomList<T> TriFusion(CustomList<T> list) {
		int size = list.getSize();
		if (size <= 1)
			return list;
		int mid = Math.floorDiv(size, 2);
		CustomList<T> right = this.TriFusion(list.slice(0, mid - 1));
		super.fireChange();
		CustomList<T> left = this.TriFusion(list.slice(mid, size - 1));
		super.fireChange();
		return this.Fusion(right, left);

	}

	public CustomList<T> Fusion(CustomList<T> l1, CustomList<T> l2) {
		int size1 = l1.getSize();
		int size2 = l2.getSize();
		int i = 0;
		int j = 0;
		List<T> sorted = new ArrayList<>();
		while (sorted.size() < size1 + size2) { 
			if (i >= size1) {
				sorted.add(l2.getCase(j));
				super.fireChange();
				j++;
			} else if (j >= size2) {
				sorted.add(l1.getCase(i));
				super.fireChange();
				i++;
			} else if (l1.getCase(i).compareTo(l2.getCase(j)) > 0) {
				sorted.add(l2.getCase(j));
				super.fireChange();
				j++;
			} else {
				sorted.add(l1.getCase(i));
				super.fireChange();
				i++;
			}
			this.list.setComparaisonNumber(this.list.getComparaisonNumber()+1);
		}
		return new CustomList<T>(sorted);

	}

}
