package model.algorithms.sorts;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class CombSort<T extends Comparable<T>> extends SortFunction<T> {

	public CombSort(CustomList<T> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomList<T> sort() {
		// TODO Auto-generated method stub
		return this.Comb(this.list);
	}

	@Override
	public SortName name() {
		// TODO Auto-generated method stub
		return SortName.CombSort;
	}
	
	public static void test() {
		
	}

	private CustomList<T> Comb(CustomList<T> list){
        int n = list.getSize();
 
        // initialize gap
        int gap = n;
 
        // Initialize swapped as true to make sure that
        // loop runs
        boolean swapped = true;
 
        // Keep running while gap is more than 1 and last
        // iteration caused a swap
        while (gap != 1 || swapped == true){
            // Find next gap
            gap = this.getNextGap(gap);
 
            // Initialize swapped as false so that we can
            // check if swap happened or not
            swapped = false;
 
            // Compare all elements with current gap
            for (int i=0; i < n-gap; i++) {
                if (this.list.biggerThan(this.list.getCase(i), this.list.getCase(i+gap))){
                    // Swap arr[i] and arr[i+gap]
                    T temp = this.list.getCase(i);
                    this.list.set(i, this.list.getCase(i+gap));
                    super.fireChange();
                    this.list.set(i+gap, temp);
                    super.fireChange();
 
                    // Set swapped
                    swapped = true;
                }
            }
        }
        return this.list;
    }

	private int getNextGap(int gap) {
		// Shrink gap by Shrink factor
		gap = (gap * 10) / 13;
		if (gap < 1)
			return 1;
		return gap;
	}

}
