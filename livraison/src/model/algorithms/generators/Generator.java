package model.algorithms.generators;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import model.CustomList;




public abstract class Generator {

    protected String name;
    protected static final Random random = new Random();

    public Generator(String name){
        this.name = name;
    }
    

    public abstract CustomList<Integer> generate(int n);


    public static void test(){
        System.out.println(new AlmostSortedGenerator().generate(10));
        
    }

    public static CustomList<Integer> generateIncreasingIntegerSerie(int n){
        if(n <= 0 )  throw new IllegalArgumentException("can't generate list");
        List<Integer> serie = new ArrayList<>();
        int size = serie.size();
        int variation = Generator.generateRandomIntegerBetween(1,Generator.generateRandomIntegerBetween(3, 50));
        
        serie.add(variation);
        while(size < n){
            variation = Generator.generateRandomIntegerBetween(1, 10);
            int last = serie.get(serie.size()-1);
            int elem = Generator.generateRandomIntegerBetween(last, last+ variation);
            if(elem != serie.get(serie.size()-1)) serie.add(elem);
            size = serie.size();

        }
        return new CustomList<Integer>(serie);
    }

    public static int generateRandomIntegerBetween(int a, int b){
        if(a < b){
            return a + random.nextInt(b - a +1);
        }
        throw new IllegalArgumentException("Invalid range: a must be less than or equal to b");
    }

    public static int percent(int size, int percent){
        return (percent*size)/100;
    }

    public String name(){
        return this.name;
    }

}
