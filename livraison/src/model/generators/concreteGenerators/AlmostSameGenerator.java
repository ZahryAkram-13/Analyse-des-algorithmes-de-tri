package model.generators.concreteGenerators;
import model.generators.*;
import model.CustomList;

import java.util.*;

/**
 * Cette classe représente un générateur concret.
 * Elle étend la classe abstraite Generator et fournit une implémentation des méthodes associées.
 * Ce générateur a pour particularité de simplement swap deux éléments de la liste.
 * 
 * @author BOUTROIS Benjamin
 */
public class AlmostSameGenerator<T extends Comparable<T>> extends Generator<T>{

    @Override
    public void disorderAlgorithm(CustomList<T> liste){
        if (liste.getSize() <= 1) {
            // Si la liste est vide ou contient seulement un élément, ne rien faire
            return;
        }
        // On switch deux elements d'index aleatoires.
        Random random = new Random();
        int firstRandIndex = random.nextInt(liste.getSize());
        int secRandIndex = random.nextInt(liste.getSize());
        while (firstRandIndex == secRandIndex) {
            secRandIndex = random.nextInt(liste.getSize());
        }
    
        liste.swap(firstRandIndex, secRandIndex);
    }
}
