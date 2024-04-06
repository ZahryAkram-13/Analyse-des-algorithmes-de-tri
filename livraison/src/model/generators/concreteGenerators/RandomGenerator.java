package model.generators.concreteGenerators;
import model.generators.*;
import model.CustomList;

import java.util.Collections;
import java.util.*;

/**
 * Cette classe représente un générateur concret.
 * Elle étend la classe abstraite Generator et fournit une implémentation des méthodes associées.
 * Ce générateur a pour particularité de mélanger aléatoirement la liste.
 * 
 * @author BOUTROIS Benjamin
 *  */
public class RandomGenerator<T extends Comparable<T>> extends Generator<T>{

    @Override
    public void disorderAlgorithm(CustomList<T> liste){
        if (liste.getSize() <= 1) {
            // Si la liste est vide ou contient seulement un élément, ne rien faire
            return;
        }
        List<T> tmpList = liste.getList();
        Collections.shuffle(tmpList);
        liste.setList(tmpList);
    }
}
