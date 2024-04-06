package model.generators.concreteGenerators;
import model.generators.*;
import model.CustomList;

import java.util.*;

/**
 * Cette classe représente un générateur concret.
 * Elle étend la classe abstraite Generator et fournit une implémentation des méthodes associées.
 * Ce générateur a pour particularité de décaler tous les index de 1.
 * 
 * @author BOUTROIS Benjamin
 */
public class CaesarGenerator<T extends Comparable<T>> extends Generator<T>{

    @Override
    public void disorderAlgorithm(CustomList<T> liste){
        if (liste.getSize() <= 1) {
            // Si la liste est vide ou contient seulement un élément, ne rien faire
            return;
        }
        // On decale tous les elements de 1 vers la droite. Le dernier element se retrouve en premiere place.
        List<T> tmpList = liste.getList();
        T lastElement = tmpList.get(tmpList.size() - 1);
        for (int i = tmpList.size() - 1; i >= 1; i--) {
            tmpList.set(i, tmpList.get(i - 1));
        }
        tmpList.set(0, lastElement);
        liste.setList(tmpList);
    }
}
