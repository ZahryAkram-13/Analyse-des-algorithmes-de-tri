package model.algorithms.sorts;

import java.util.Arrays;
import java.util.List;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;

public class TimSort<T extends Comparable<T>> extends SortFunction<T> {

    // Longueur minimale d'une fusion lors du tri
    public static final int MIN_MERGE = 32;

    /**
     * Constructeur de la classe TimSort2.
     * 
     * @param list La liste à trier.
     */
    public TimSort(CustomList<T> list) {
        super(list);
    }

    /**
     * Retourne le nom de l'algorithme de tri.
     * 
     * @return Le nom de l'algorithme de tri.
     */
    @Override
    public SortName name() {
        return SortName.TimSort;
    }

    /**
     * Trie la liste en utilisant l'algorithme TimSort2.
     * 
     * @return La liste triée.
     */
    @Override
    public CustomList<T> sort() {
        // Trie les sous-tableaux individuels de taille RUN
        int minRun = minRunLength(MIN_MERGE);
        int n = list.getSize();
        for (int i = 0; i < n; i += minRun) {
            this.insertionSort(i, Math.min((i + MIN_MERGE - 1), (n - 1)));
        }

        // Fusionne les sous-tableaux à partir de la taille RUN (ou 32).
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                if (mid < right) {
                    this.merge(left, mid, right);
                }
            }
        }
        return this.list;
    }

    /**
     * Calcule la longueur minimale d'un "run" pour l'algorithme TimSort.
     * 
     * @param n La longueur de la liste.
     * @return La longueur minimale d'un "run".
     */
    public int minRunLength(int n) {
        assert n >= 0;
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    /**
     * Effectue un tri d'insertion sur une partie de la liste.
     * 
     * @param start L'indice de début.
     * @param end   L'indice de fin.
     */
    private void insertionSort(int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            T tmp = list.getCase(i);
            int j = i - 1;
            while (j >= start && list.biggerThan(list.getCase(j), tmp)) {
                list.set(j + 1, list.getCase(j));
                super.fireChange();
                j--;
            }
            list.set(j + 1, tmp);
            super.fireChange();
        }
    }

    /**
     * Fusionne deux sous-listes triées.
     * 
     * @param start  L'indice de début de la première sous-liste.
     * @param middle L'indice de milieu.
     * @param end    L'indice de fin de la deuxième sous-liste.
     */
    public void merge(int start, int middle, int end) {
        int len1 = middle - start + 1, len2 = end - middle;
        CustomList<T> left = new CustomList<T>();
        CustomList<T> right = new CustomList<T>();
        for (int x = 0; x < len1; x++) {
            left.addI(x, list.getCase(start + x));
            super.fireChange();
        }
        for (int x = 0; x < len2; x++) {
            right.addI(x, list.getCase(middle + 1 + x));
            super.fireChange();
        }

        int i = 0, j = 0, k = start;
        // Après la comparaison, nous fusionnons ces deux tableaux
        // dans le plus grand sous-tableau
        while (i < len1 && j < len2) {
            if (list.biggerThanOrEqual(right.getCase(j), left.getCase(i))) {
                list.set(k, left.getCase(i));
                i++;
                super.fireChange();
            } else {
                list.set(k, right.getCase(j));
                j++;
                super.fireChange();
            }
            k++;
        }

        // Copie des éléments restants de la gauche, s'il y en a
        while (i < len1) {
            list.set(k, left.getCase(i));
            k++;
            i++;
        }

        // Copie des éléments restants de la droite, s'il y en a
        while (j < len2) {
            list.set(k, right.getCase(j));
            k++;
            j++;
        }
    }

    public static void main(String args[]) {
        // Exemple 1
        final List<Integer> list = Arrays.asList(3, 7, 4, 8, 6, 2, 1, 5);
        CustomList<Integer> customList = new CustomList<>(list);
        TimSort<Integer> ob = new TimSort<Integer>(customList);
        System.out.print("\nListe initiale : " + list);
        System.out.println("\nListe triée    : " + ob.sort());

        // Exemple 2
        customList = CustomList.generateUniqueIntegerList(20); // pour générer une liste d'entier aléatoirement de taille 32
        ob.setList(customList);
        System.out.print("\nListe initiale : " + customList.getList());
        System.out.println("\nListe triée    : " + ob.sort());
    }
}