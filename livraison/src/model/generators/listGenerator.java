package model.generators;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import model.CustomList;

/**
 * Cette classe permet d'instancier une nouvelle customList.
 * 
 * @author BOUTROIS Benjamin
 * @author Akram Zahry
 */
public class listGenerator {

    protected static final Random random = new Random();

    /**
     * Genere une nouvelle instance de CustomList a partir d'une taille de liste triee avec un gap variable entre les elements.
     * 
     * @param n La taille de la liste a generer.
     * 
     * @return Une nouvelle instance de CustomList contenant une liste triee de n elements.
     */
    public static CustomList<Integer> generateIncreasingIntegerSerie(int n){
        if(n <= 0 )  throw new IllegalArgumentException("can't generate list");
        List<Integer> serie = new ArrayList<>();
        int size = serie.size();
        int variation = generateRandomIntegerBetween(1, generateRandomIntegerBetween(3, 10)); //Valeurs à modifier pour changer le pas entre chaque élément
        serie.add(variation);
        while(size < n){
            variation = generateRandomIntegerBetween(1, 10);
            int last = serie.get(serie.size()-1);
            int elem = generateRandomIntegerBetween(last, last+ variation);
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
}
