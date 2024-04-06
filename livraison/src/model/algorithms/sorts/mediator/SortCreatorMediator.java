package model.algorithms.sorts.mediator;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

import model.algorithms.sorts.*;

/**
 * Médiateur pour la création d'instances d'algorithmes de tri en fonction du nom de l'algorithme.
 *
 * @param <T> Le type d'éléments que les algorithmes de tri doivent trier (doit être comparable).
 * @author zahry akram
 */
public class SortCreatorMediator <T extends Comparable<T>> implements Mediator<T>{

	public SortCreatorMediator() {
	}

	
	 /**
     * Crée une instance d'un algorithme de tri en fonction du nom de l'algorithme spécifié.
     *
     * @param name Le nom de l'algorithme de tri.
     * @param list La liste à trier.
     * @return Une instance de l'algorithme de tri correspondant, ou null si le nom n'est pas reconnu.
     */
	@Override
	public SortFunction<T> create(SortName name, CustomList<T> list) {
		if(list == null) throw new NullPointerException(CustomList.LIST_IS_NULL);
		if(name.equals(SortName.BinaryTreeSort)) return new BinaryTreeSort<T>(list);
		if(name.equals(SortName.HeapSort)) return new HeapSort<T>(list);
		if(name.equals(SortName.FusionSort)) return new FusionSort<T>(list);
		if(name.equals(SortName.QuickSort)) return new QuickSort<T>(list);
		if(name.equals(SortName.BubbleSort)) return new BubbleSort<T>(list);
		if(name.equals(SortName.InsertionSort)) return new InsertionSort<T>(list);
		if(name.equals(SortName.SelectionSort)) return new SelectionSort<T>(list);
		if(name.equals(SortName.CombSort)) return new CombSort<T>(list);
		if(name.equals(SortName.ShellSort)) return new ShellSort<T>(list);
		if(name.equals(SortName.BitonicSort)) return new BitonicSort<T>(list);
		if(name.equals(SortName.TimSort)) return new TimSort<T>(list);
		if(name.equals(SortName.StoogeSort)) return new StoogeSort<T>(list);
		if(name.equals(SortName.JavaSort)) return new JavaSort<T>(list);
		return null;
	}
	 

}
