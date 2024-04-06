package tests.model.generators.concreteGenerators;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Test;
import model.generators.*;
import model.CustomList;

/**
 * Classe de tests unitaires pour la classe AlmostReverseGenerator.
 * 
 * @author BOUTROIS Benjamin
 */
public class AlmostReverseGeneratorTest {
    
    @Test
    public void testDisorderAlgorithm() throws Exception{
        // Test le désordre de la liste
        Generator<Integer> myGen = new GeneratorFactory<Integer>().createGenerator("almost_reverse");
        Generator<Integer> myGenReverse = new GeneratorFactory<Integer>().createGenerator("reverse");
        CustomList<Integer> myList = CustomList.generateIntegerList(10);
        CustomList<Integer> disorderedList = myList.clone();
        myGen.disorderAlgorithm(disorderedList);
        //La méthode a modifié la liste
        assertFalse(myList.getList().equals(disorderedList.getList()));

        CustomList<Integer> myReversedList = myList.clone();
        myGenReverse.disorderAlgorithm(myReversedList);
        //Vérifie qu'un seul swap ait été effectué
        assertTrue(hamming_distance(disorderedList.getList(), myReversedList.getList()) == 2);

        // Test de présence de tous les éléments
        myList = new CustomList<>();
        for (int i = 1; i <= 10; i++) {
            myList.add(i);
        }
        disorderedList = myList.clone();
        myGen.disorderAlgorithm(disorderedList);
        assertTrue(myList.containsAll(disorderedList));
        //La double vérification teste aussi l'égalité des longueurs
        assertTrue(disorderedList.containsAll(myList));

        // Test avec une liste vide
        CustomList<Integer> emptyList = new CustomList<>();
        CustomList<Integer> emptyDisorderedList = emptyList.clone();
        myGen.disorderAlgorithm(emptyDisorderedList);
        //La liste "presque retournée" d'une liste vide est vide.
        assertTrue(emptyDisorderedList.isEmpty());
        
        //Test avec un seul élément
        CustomList<Integer> oneElementList = CustomList.generateIntegerList(1);
        CustomList<Integer> oneElementDisorderedList = oneElementList.clone();
        myGen.disorderAlgorithm(oneElementDisorderedList);
        //La liste "presque retournée" à un élément est égale à elle même.
        assertTrue(oneElementList.getList().equals(oneElementDisorderedList.getList()));

        System.out.println("AlmostReverseGeneratorTest passed");
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
