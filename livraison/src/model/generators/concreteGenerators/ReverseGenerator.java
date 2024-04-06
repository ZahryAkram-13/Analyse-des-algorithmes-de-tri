package model.generators.concreteGenerators;
import model.generators.*;
import model.CustomList;

import java.util.*;

/**
 * Cette classe représente un générateur concret.
 * Elle étend la classe abstraite Generator et fournit une implémentation des méthodes associées.
 * Ce générateur a pour particularité de simplement renverser les éléments de la liste.
 * 
 * @author BOUTROIS Benjamin
 */
public class ReverseGenerator<T extends Comparable<T>> extends Generator<T>{

    @Override
    public void disorderAlgorithm(CustomList<T> liste){
        if (liste.getSize() <= 1) {
            // Si la liste est vide ou contient seulement un élément, ne rien faire
            return;
        }
        // Retourne la liste
        for (int k = 0, j = liste.getSize() - 1; k < j; k++) {
            List<T> tmpList = liste.getList();
            tmpList.add(k, tmpList.remove(j)); // Temps : O(n), Espace : O(1)
            liste.setList(tmpList);
        }
    }
}
