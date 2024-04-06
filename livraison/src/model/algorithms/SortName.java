package model.algorithms;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération des noms des algorithmes de tri.
 * @author Zahry Akram
 */
public enum SortName {

    // Noms des algorithmes de tri avec leurs valeurs associées
    UnkownSort("UnkownSort"),

    BinaryTreeSort("BinaryTreeSort"), HeapSort("HeapSort"),
    BitonicSort("BitonicSort"), ShellSort("ShellSort"), TimSort("TimSort"), CombSort("CombSort"),
    FusionSort("FusionSort"), QuickSort("QuickSort"),
    InsertionSort("InsertionSort"), SelectionSort("SelectionSort"), BubbleSort("BubbleSort"),

    StoogeSort("StoogeSort"), 
    JavaSort("JavaSort"),
    ;

    /**
     * Liste des noms des algorithmes de tri.
     */
    public static final List<SortName> SORTSNAMES = Arrays.asList(
            BinaryTreeSort, HeapSort,
            BitonicSort, ShellSort, TimSort, CombSort,
            FusionSort, QuickSort,
            InsertionSort, SelectionSort, BubbleSort,
            StoogeSort,
            JavaSort
                                                                  );
    /**
     * fonction pour retourner le nom d'un algo de tri
     */
    public static SortName getSortByName(String name){
        for(int i = 0; i < SORTSNAMES.size(); i++){
            SortName sort = SORTSNAMES.get(i);
            if(sort.name.equals(name)) return sort;
        }
        return UnkownSort;
    }

    /**
     * Nom de l'algorithme.
     */
    public String name;

    /**
     * Constructeur de l'énumération `SortName`.
     *
     * @param name Nom de l'algorithme.
     */
    SortName(String name) {
        this.name = name;
    }
}

