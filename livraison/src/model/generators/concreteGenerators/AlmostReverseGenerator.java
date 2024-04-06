package model.generators.concreteGenerators;

import model.CustomList;
import model.generators.*;
import java.util.*;

/**
 * Cette classe représente un générateur concret.
 * Elle étend la classe abstraite Generator et fournit une implémentation des méthodes associées.
 * Ce générateur a pour particularité de renverser la liste puis de swap deux éléments.
 * 
 * @author BOUTROIS Benjamin
 */
public class AlmostReverseGenerator<T extends Comparable<T>> extends Generator<T>{

    @Override
    public void disorderAlgorithm(CustomList<T> liste) {
        if (liste.getSize() <= 1) {
            // Si la liste est vide ou contient seulement un élément, ne rien faire
            return;
        }
        // Dans un premier temps on reverse la liste
        Generator<T> reverseGenerator = new ReverseGenerator<T>();
        reverseGenerator.disorderAlgorithm(liste);
    
        // Puis on switch deux elements d'index aleatoires.
        Random random = new Random();
        int firstRandIndex = random.nextInt(liste.getSize());
        int secRandIndex = random.nextInt(liste.getSize());
        while (firstRandIndex == secRandIndex) {
            secRandIndex = random.nextInt(liste.getSize());
        }

        liste.swap(firstRandIndex, secRandIndex);
    }
}
