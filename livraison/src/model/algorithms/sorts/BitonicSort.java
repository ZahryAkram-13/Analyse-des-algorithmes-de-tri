package model.algorithms.sorts;

import java.util.Arrays;
import java.util.List;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

/**
 * On s'est inspiré de ce lien 
 * @see https://www.geeksforgeeks.org/bitonic-sort/
 * 
 * Pour que cet algorithme fonctionne, il faut lui passer une liste où la taille est une 
 * puissance de 2.
 * Exemple une liste de taille de 2 ou 4 ou 8 ou 16 ou 32 
 */
public class BitonicSort<T extends Comparable<T>> extends SortFunction<T> {

    /**
     * Constructeur de la classe BitonicSort.
     *
     * @param list La liste à trier.
     */
    public BitonicSort(CustomList<T> list) {
        super(list);
    }

    /**
     * Méthode de tri principal. Trie la liste en utilisant le tri bitonique.
     * 
     * @return La liste triée.
     */
    @Override
    public CustomList<T> sort() {
        return bitonicSort(this.list, 0, list.getSize(), 1);
    }

    /**
     * Retourne le nom de l'algorithme de tri.
     * 
     * @return Le nom de l'algorithme de tri.
     */
    @Override
    public SortName name() {
        return SortName.BitonicSort;
    }

    /**
     * Compare et échange deux éléments de la liste selon la direction spécifiée.
     * 
     * @param list La liste.
     * @param i    Indice du premier élément.
     * @param j    Indice du deuxième élément.
     * @param dir  Direction de comparaison (1 pour croissant, 0 pour décroissant).
     */
    private void compaireAndSwap(CustomList<T> list, int i, int j, int dir) {
        boolean c1 = this.list.biggerThan(this.list.getCase(i), this.list.getCase(j)) && dir == 1;
        boolean c2 = this.list.biggerThan(this.list.getCase(j), this.list.getCase(i)) && dir == 0;
        if (c1 || c2) {
            this.list.swap(i, j);
            super.fireChange();
        }
    }

    /**
     * Fusionne deux sous-listes bitoniques.
     * 
     * @param list La liste.
     * @param low  Indice de début de la première sous-liste.
     * @param cnt  Nombre d'éléments à fusionner.
     * @param dir  Direction de fusion (1 pour croissant, 0 pour décroissant).
     */
    private void bitonicMerge(CustomList<T> list, int low, int cnt, int dir) {
        if (cnt > 1) {
            int k = cnt / 2;
            for (int i = low; i < low + k; i++) {
                this.compaireAndSwap(this.list, i, i + k, dir);
                super.fireChange();
            }
            this.bitonicMerge(this.list, low, k, dir);
            super.fireChange();
            this.bitonicMerge(this.list, low + k, k, dir);
            super.fireChange();
        }
    }

    /**
     * Trie récursivement une sous-liste de manière bitonique.
     * 
     * @param list La liste.
     * @param low  Indice de début de la sous-liste.
     * @param cnt  Nombre d'éléments à trier.
     * @param dir  Direction de tri (1 pour croissant, 0 pour décroissant).
     * @return La sous-liste triée.
     */
    private CustomList<T> bitonicSort(CustomList<T> list, int low, int cnt, int dir) {
        if (cnt > 1) {
            int k = cnt / 2;
            this.bitonicSort(list, low, k, 1); // Tri croissant
            this.bitonicSort(list, low + k, k, 0); // Tri décroissant
            this.bitonicMerge(list, low, cnt, dir);
        }

        return list;
    }

    /**
     * Méthode principale pour tester le tri bitonique.
     * 
     * @param args Arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String args[]) {
        // Exemple 1
        final List<Integer> list = Arrays.asList(3, 7, 4, 8, 6, 2, 1, 5);
        CustomList<Integer> customList = new CustomList<>(list);
        BitonicSort<Integer> ob = new BitonicSort<Integer>(customList);
        System.out.print("\nListe initiale : " + list);
        System.out.println("\nListe triée    : " + ob.sort());

        // Exemple 2
        customList = CustomList.generateUniqueIntegerList(32); // pour générer une liste d'entier aléatoirement de taille 32
        ob.setList(customList);
        System.out.print("\nListe initiale : " + customList.getList());
        System.out.println("\nListe triée    : " + ob.sort());
    }
}
