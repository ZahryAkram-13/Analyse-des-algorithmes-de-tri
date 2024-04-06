package model.algorithms.statistics;

import java.util.*;

import model.algorithms.sorts.mediator.*;
import model.CustomList;

import model.algorithms.TimeDecorator;
import model.algorithms.generators.AlmostSortedGenerator;
import model.algorithms.SortName;
import model.algorithms.SortFunction;

import model.generators.*;

public class Complexity <T extends Comparable<T>>{

    public static final String SIZE = "size";
    public static final String TIME = "time";

    public String ALGO_NAME;
    public String GENERATOR_NAME;

    public Map<String, String> header;

    int repetition;
    SortFunction<T> sort;
    Mediator<T> mediator;
    //CustomList<T> list;
    TimeDecorator<T> timeDecorator;
    Generator generator;
    Map<String, List<Double>> info;

    

    public Complexity(SortFunction<T> sort, Generator generator, int repetition){
        this.header = new HashMap<String, String>();
        this.sort = sort;
        this.generator = generator;
        this.repetition = repetition;
        this.info = new HashMap<String, List<Double>>();
        this.timeDecorator = new TimeDecorator<T>(this.sort);

        
        info.put(SIZE, new ArrayList<Double>());
        info.put(TIME, new ArrayList<Double>());

        this.header.put(ALGO_NAME, this.sort.name().name);
        this.header.put(GENERATOR_NAME, this.generator.getName());
    }

    public static void test(){
        //SortCreatorMediator<Integer> mediator = new SortCreatorMediator<Integer>();
        CustomList<Integer> list = CustomList.generateIntegerList(1000);
        SortFunction<Integer> sort = new SortCreatorMediator<Integer>().create(SortName.JavaSort, list);
        GeneratorFactory geneF = new GeneratorFactory();
        Generator<?> generator = geneF.createGenerator("caesar");
        Complexity<Integer> complexity = new Complexity<Integer>(sort, generator, 10);


        System.out.println(complexity.getInfo());
    }

    public double getExecutionTime(){
        return this.timeDecorator.measureTime();
    }


    public void getInfoOneList(CustomList<T> list){

        this.sort.setList(list);
        //System.out.println(this.sort.getInfo());
        //double time = this.getExecutionTime();
        double averageTime = TimeDecorator.averageTimeExecution(list, this.sort);
        //System.out.println(list.getSize() + " " + complexity);
        this.info.get(SIZE).add(list.getSize() + 0.0);
        this.info.get(TIME).add(averageTime);
    }


    public  Map<String, List<Double>> getInfo(){
        int size = 1024;
        int increment = 1000;
        int j = 0;
        // Custom<Integer> list =  CustomList.generateUniqueIntegerList(size);
        while(j < repetition){
            CustomList<Integer> list = new AlmostSortedGenerator().generate(size);
            this.generator.disorderAlgorithm((CustomList<T>)list);
            this.getInfoOneList((CustomList<T>) list);
            size += increment;
            
            j++;
        }
        return this.info;
    }

    public Map<String, String> getHeader(){
        return this.header;
    }

    public void setSort(SortFunction<T> newsort){
        this.sort = newsort;
    }





}
