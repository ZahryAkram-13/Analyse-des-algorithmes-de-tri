package tests.model.generators.concreteGenerators;

import org.junit.Test;
import static org.junit.Assert.*;

import model.CustomList;
import model.generators.*;

/**
 * Classe de tests unitaires pour la classe ReverseGenerator.
 * 
 * @author BOUTROIS Benjamin
 */
public class ReverseGeneratorTest {
    
    @Test
    public void testDisorderAlgorithm() throws Exception {
        //Test le désordre de la liste
        //INITIALISATION
        Generator<Integer> myGen = new GeneratorFactory<Integer>().createGenerator("reverse");
        CustomList<Integer> sortedList = CustomList.generateIntegerList(10);
        CustomList<Integer> reversedList = sortedList.clone();

        //MÉTHODE DE DÉSORDRE
        myGen.disorderAlgorithm(reversedList);

        //DÉSORDRE MANUEL
        CustomList<Integer> myReversedList = sortedList.clone();
        int size = myReversedList.getSize();
        for (int i = 0; i < size / 2; i++) { 
            int temp = myReversedList.getCase(i);
            myReversedList.set(i, myReversedList.getCase(size - i - 1));
            myReversedList.set(size - i - 1, temp);
        }
        //Comparer la méthode de désordre avec un désordre manuel pour vérifier l'exactitude.
        assertTrue(reversedList.getList().equals(myReversedList.getList()));

        // Test de présence de tous les éléments
        CustomList<Integer> myList = CustomList.generateIntegerList(10);
        CustomList<Integer> disorderedList = myList.clone();

        myGen.disorderAlgorithm(disorderedList);
        assertTrue(myList.containsAll(disorderedList));
        //La double vérification teste aussi l'égalité des longueurs
        assertTrue(disorderedList.containsAll(myList));

        // Test avec une liste vide
        CustomList<Integer> emptyList = new CustomList<>();
        CustomList<Integer> emptyDisorderedList = emptyList.clone();
        myGen.disorderAlgorithm(emptyDisorderedList);
        //La liste "retournée" d'une liste vide est vide.
        assertTrue(emptyDisorderedList.isEmpty());
        
        //Test avec un seul élément
        CustomList<Integer> oneElementList = CustomList.generateIntegerList(1);
        CustomList<Integer> oneElementDisorderedList = oneElementList.clone();
        myGen.disorderAlgorithm(oneElementDisorderedList);
        //La liste "retournée" à un élément est égale à elle même.
        assertTrue(oneElementList.getList().equals(oneElementDisorderedList.getList()));

        System.out.println("ReverseGeneratorTest passed");
    }   
}
