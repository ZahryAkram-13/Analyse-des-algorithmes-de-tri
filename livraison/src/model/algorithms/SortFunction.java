package model.algorithms;

import model.CustomList;
import util.*;

/**
 * Représente une classe abstraite pour les algorithmes de tri.
 * Les classes d'algorithmes de tri étendent cette classe et implémentent la méthode sort(),
 * qui trie l'attribut list de la classe.
 *
 * @param <T> Le type des éléments à trier.
 * @author Zahry Akram
 */
public abstract class SortFunction<T extends Comparable<T>> extends ListenableModel {

    // Liste à trier
    protected CustomList<T> list;

    /**
     * Constructeur de la classe SortFunction.
     *
     * @param list La liste à trier.
     */
    public SortFunction(CustomList<T> list) {
        this.list = list;
    }

    /**
     * Trie la liste.
     *
     * @return La liste triée.
     */
    public abstract CustomList<T> sort();

    /**
     * Renvoie le nom de l'algorithme de tri.
     *
     * @return Le nom de l'algorithme de tri.
     */
    public abstract SortName name();

    /**
     * Méthode de test permettant de vérifier le fonctionnement de l'algorithme de tri.
     */
    public static void test() {
        CustomList<Integer> list = CustomList.generateIntegerList(10);
    }

    /**
     * Renvoie des informations sur l'algorithme de tri et la liste.
     *
     * @return Les informations sur l'algorithme de tri et la liste.
     */
    public String getInfo() {
        return "----------------- SortFunction ------------------------------\n" +
                "Algo: " + this.name() + "\n" +
                //"List: " + this.list + "\n" +
                "AccessCaseNumber: " + this.list.getAccessCaseNumber() + "\n" +
                "ComparaisonNumber: " + this.list.getComparaisonNumber() + "\n" +
                "------------------------------------------------------------------\n";
    }
    
    public void destroyList() {
    	this.list.destroyList();
    }

    public CustomList<T> getList() {
        return list;
    }

    public void setList(CustomList<T> list) {
        this.list = list;
    }

    public void fireChange() {
        for (Listener l : super.listeners) {
            l.update(this);
            
        }
    }

}
