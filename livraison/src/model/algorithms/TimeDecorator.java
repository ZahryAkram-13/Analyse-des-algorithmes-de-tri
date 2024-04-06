package model.algorithms;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import model.algorithms.sorts.*;
import model.algorithms.sorts.mediator.SortCreatorMediator;
import model.CustomList;


/**
 * Cette classe représente un décorateur de temps pour les algorithmes de tri. Elle mesure le temps
 * d'exécution d'un algorithme de tri spécifié, enregistre des informations sur le processus de tri,
 * et fournit des représentations textuelles de ces informations.
 *
 * @param <T> le type des éléments à trier, doit être Comparable
 * @author Zahry Akram
 */
public class TimeDecorator<T extends Comparable<T>> {

    private double time;                 // Execution time in milliseconds
    private SortName name;               // Name of the sorting algorithm
    private String formattedTime;        // Formatted execution time as a string
    private CustomList<T> sorted;        // Sorted list produced by the sorting algorithm

    private SortFunction<T> sortFunction; // The sorting algorithm to be decorated

  
    public TimeDecorator(SortFunction<T> sortFunction) {
        this.time = -113;                 // Default value, indicating uninitialized

        this.sorted = null;                // Default value, indicating uninitialized

        this.sortFunction = sortFunction;
        this.name = this.sortFunction.name();  // nom d'algo
    }

      /**
     * Mesure le temps d'exécution de l'algorithme de tri et enregistre d'autres informations pertinentes.
     *
     * @return le temps d'exécution en millisecondes
     */
    public double measureTime() {
        this.name = sortFunction.name();
        double startTime = System.nanoTime();
        this.sorted = sortFunction.sort();
        double endTime = System.nanoTime();
        double time = (endTime - startTime) / 1_000_000.0;
        DecimalFormat df = new DecimalFormat("#.########");
        this.formattedTime = df.format(time);
        this.time = time;
        return this.time;
    }

    /**
     * Méthode statique pour tester le TimeDecorator avec un algorithme de tri spécifique.
     */
    public static void test() {
        CustomList<Integer> list = new CustomList<Integer>(CustomList.LISTTEST);
        list = CustomList.generateUniqueIntegerList(6);
        SortCreatorMediator<Integer> mediator = new SortCreatorMediator<Integer>();
        SortFunction<Integer> sort = mediator.create(SortName.HeapSort, list);
        System.out.println(list);
        TimeDecorator<Integer> deco = new TimeDecorator<Integer>(sort);
        System.out.println(deco);
        System.out.println(sort.sort());
        //deco.measureTime();

        //System.out.print(deco.getInfo());
        //System.out.println(TimeDecorator.averageTimeExecution(list, sort));
        //sort.destroyList();

    }

    /**
     * Obtient les informations formatées sur le processus de tri.
     *
     * @return une chaîne contenant des informations sur le processus de tri
     */
    public String getInfo() {
        return "\n------------------------Deco Informations--------------------:\n" + 
               "algorithm: " + this.getName() + "\n" +
               //"sort list: " + this.sortFunction.getList() + "\n" + 
               "list size: " + this.sortFunction.getList().getSize() + "\n" +
               "access :  " +  this.sortFunction.getList().getComparaisonNumber()+ 
               "execute time: " + this.time + " milliseconds\n" +
               "---------------------------------------------------------------:\n";
    }

    public static double average(List<Double> list){
        double sum = 0;
        int size = list.size();
        for(int i = 0; i < size; i++) sum += list.get(i);
        return sum/size;
    }

    public static <T extends Comparable<T>> double averageTimeExecution(CustomList<T> list, SortFunction<T> sort){
        //System.out.println("------------------------avaregeTimeExecution");
        TimeDecorator<T> deco = new TimeDecorator<T>(sort);
        double avaregeTime = -113;
        final int ITERATIONS = 100;
        List<Double> times = new ArrayList<Double>();
        for(int i = 0; i < ITERATIONS; i++){
            //System.out.println(list);
            sort.setList(list.clone());
            times.add(deco.measureTime());
            // System.out.println(deco.getInfo());
            //System.out.println(sort.getInfo());

        }
        //System.out.println(times);
        //System.out.println("------------------------end\n\n");
        return TimeDecorator.average(times);
    }

 
    @Override
    public String toString() {
        return "sort: " + this.sortFunction.name() + "\n";
    }

  
    public double getTime() {
        return this.time;
    }

    public SortFunction<T> getSortFunction(){
        return this.sortFunction;
    }

    public void setSortFunction(SortFunction<T> sortFunction){
        this.sortFunction = sortFunction;
    }

  
    public SortName getName() {
        return name;
    }
}
