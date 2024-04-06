package tests.model.generators.concreteGenerators;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import model.CustomList;
import model.generators.*;

/**
 * Classe de tests unitaires pour la classe AlmostSameGenerator.
 * 
 * @author BOUTROIS Benjamin
 */
public class AlmostSameGeneratorTest {
    
    @Test
    public void testDisorderAlgorithm() throws Exception {
        //Test le désordre de la liste
        //INITIALISATION
        Generator<Integer> myGen = new GeneratorFactory<Integer>().createGenerator("almost_same");
        CustomList<Integer> sortedList = CustomList.generateIntegerList(10);
        CustomList<Integer> disorderedList = sortedList.clone();
        myGen.disorderAlgorithm(disorderedList);
        //Vérifie qu'un seul swap ait été effectué
        assertTrue(hamming_distance(sortedList.getList(), disorderedList.getList()) <= 2);


        // Test de présence de tous les éléments
        CustomList<Integer> myList = CustomList.generateIntegerList(10);
        disorderedList = myList.clone();

        myGen.disorderAlgorithm(disorderedList);
        assertTrue(myList.containsAll(disorderedList));
        //La double vérification teste aussi l'égalité des longueurs
        assertTrue(disorderedList.containsAll(myList));

        // Test avec une liste vide
        CustomList<Integer> emptyList = new CustomList<>();
        CustomList<Integer> emptyDisorderedList = emptyList.clone();
        myGen.disorderAlgorithm(emptyDisorderedList);
        //La liste "presque identique" d'une liste vide est vide.
        assertTrue(emptyDisorderedList.isEmpty());
        
        //Test avec un seul élément
        CustomList<Integer> oneElementList = CustomList.generateIntegerList(1);
        CustomList<Integer> oneElementDisorderedList = oneElementList.clone();
        myGen.disorderAlgorithm(oneElementDisorderedList);
        //La liste "presque identique" à un élément est égale à elle même.
        assertTrue(oneElementList.getList().equals(oneElementDisorderedList.getList()));

        //Test avec des lettres
        Generator<String> myGenString = new GeneratorFactory<String>().createGenerator("almost_same");
        List<String> alphabet = Arrays.asList("a","b","c","d");
        CustomList<String> sortedAlphabeticList = new CustomList<>(alphabet);
        CustomList<String> disorderedAlphabeticList = sortedAlphabeticList.clone();
        myGenString.disorderAlgorithm(disorderedAlphabeticList);
        //Teste la présence de tous les éléments
        assertTrue(sortedAlphabeticList.containsAll(disorderedAlphabeticList));
        //La double vérification teste aussi l'égalité des longueurs
        assertTrue(disorderedAlphabeticList.containsAll(sortedAlphabeticList));
        //Vérifie qu'un seul swap ait été effectué
        assertTrue(hamming_distance(sortedAlphabeticList.getList(), disorderedAlphabeticList.getList()) == 2);

        System.out.println("AlmostSameGeneratorTest passed");
    }   

    public static <T extends Comparable<T>> int hamming_distance(List<T> list1, List<T> list2) {
        // Vérifie que les listes ont la même taille
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Les listes doivent avoir la même taille.");
        }
        
        int distance = 0;
        // Parcours les deux listes
        for (int i = 0; i < list1.size(); i++) {
            T elem1 = list1.get(i);
            T elem2 = list2.get(i);
            // Compare les éléments aux mêmes positions dans les deux listes
            if (!elem1.equals(elem2)) {
                distance++;
            }
        }
        return distance;
    }
}
