package tests.model.generators.concreteGenerators;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import model.CustomList;
import model.generators.*;

/**
 * Classe de tests unitaires pour la classe RandomGenerator.
 * 
 * @author BOUTROIS Benjamin
 */
public class RandomGeneratorTest {
    
    @Test
    public void testDisorderAlgorithm() throws Exception {
        //Test le désordre de la liste
        //INITIALISATION
        Generator<Integer> myGen = new GeneratorFactory<Integer>().createGenerator("random");
        CustomList<Integer> myList = CustomList.generateIntegerList(10);
        CustomList<Integer> disorderedList = myList.clone();

        myGen.disorderAlgorithm(disorderedList);
        //Vérifie la modification de la liste
        assertFalse(myList.equals(disorderedList));
        //Test de présence de tous les éléments
        assertTrue(myList.containsAll(disorderedList));
        //La double vérification teste aussi l'égalité des longueurs
        assertTrue(disorderedList.containsAll(myList));

        // Test avec une liste vide
        CustomList<Integer> emptyList = new CustomList<>();
        CustomList<Integer> emptyDisorderedList = emptyList.clone();
        myGen.disorderAlgorithm(emptyDisorderedList);
        //La liste "random" d'une liste vide est vide.
        assertTrue(emptyDisorderedList.isEmpty());
        
        //Test avec un seul élément
        CustomList<Integer> oneElementList = CustomList.generateIntegerList(1);
        CustomList<Integer> oneElementDisorderedList = oneElementList.clone();
        myGen.disorderAlgorithm(oneElementDisorderedList);
        //La liste "random" à un élément est égale à elle même.
        assertTrue(oneElementList.getList().equals(oneElementDisorderedList.getList()));

        //Test avec des lettres
        Generator<String> myGenString = new GeneratorFactory<String>().createGenerator("random");
        //Création du générateur  pour des chaînes de caractères
        List<String> alphabet = Arrays.asList("a","b","c","d");
        CustomList<String> sortedAlphabeticList = new CustomList<>(alphabet);
        CustomList<String> disorderedAlphabeticList = sortedAlphabeticList.clone();
        myGenString.disorderAlgorithm(disorderedAlphabeticList);
        //Teste la présence de tous les éléments
        assertTrue(sortedAlphabeticList.containsAll(disorderedAlphabeticList));
        //La double vérification teste aussi l'égalité des longueurs
        assertTrue(disorderedAlphabeticList.containsAll(sortedAlphabeticList));

        System.out.println("RandomGeneratorTest passed");
    }   
}
