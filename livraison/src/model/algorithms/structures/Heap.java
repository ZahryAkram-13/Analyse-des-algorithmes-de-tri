package model.algorithms.structures;

import java.util.ArrayList;
import java.util.List;

import model.CustomList;
/**
 * Représentation d'un tas binaire.
 *
 * @param <T> Le type des éléments dans le tas binaire, doit être comparable.
 * @author Zahry Akram
 */
public class Heap<T extends Comparable<T>> {

    /** Liste représentant le tas binaire. */
    private CustomList<T> heapArray;

    /** Taille maximale du tas binaire. */
    private int maxSize;

    /** Taille actuelle du tas binaire. */
    private int currentSize;

    /**
     * Constructeur du tas binaire avec une liste spécifiée.
     *
     * @param heapArray La liste représentant le tas binaire.
     */
    public Heap(CustomList<T> heapArray) {
        this.heapArray = heapArray;
        this.maxSize = heapArray.getSize();
        this.currentSize = heapArray.getSize();
    }

    
    /**
     * Vérifie si le tas binaire est vide.
     *
     * @return {@code true} si le tas binaire est vide, sinon {@code false}.
     */
    public boolean isEmpty() {
        return this.heapArray.getList().isEmpty();
    }

    /**
     * Méthode de test pour le tas binaire.
     */
    public static void test() {
        Heap<Integer> heap = new Heap<>(CustomList.generateIntegerList(10));

        //System.out.println(heap.getHeapArray());
        heap.makeHeap();
        heap.displayHeap();
        //System.out.print(heap.sort());
    }

    /**
     * Obtient l'index du fils gauche d'un nœud donné.
     *
     * @param parentIndex L'index du nœud parent.
     * @return L'index du fils gauche.
     */
    private static int getLeftChild(int parentIndex) {
        return parentIndex * 2;
    }

    /**
     * Obtient l'index du fils droit d'un nœud donné.
     *
     * @param parentIndex L'index du nœud parent.
     * @return L'index du fils droit.
     */
    private static int getRightChild(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    /**
     * Retire le premier élément du tas binaire.
     *
     * @return Le premier élément du tas binaire.
     */
    public T remove() {
        if (!this.isEmpty()) {
            T root = this.heapArray.getCase(0);
            currentSize--;
            this.heapArray.set(0, this.heapArray.getCase(currentSize));
            this.trickleDown(0);
            return root;
        }
        return null;
    }

    /**
     * Applique l'algorithme "trickle down" à partir d'un index spécifié.
     *
     * @param index L'index à partir duquel appliquer "trickle down".
     */
    private void trickleDown(int index) {
        int smallest;
        T top = this.heapArray.getCase(index);
        while (index < this.currentSize / 2) {
            int left = Heap.getLeftChild(index);
            int right = Heap.getRightChild(index);
            boolean c1 = right < this.currentSize;
            boolean c2 = this.heapArray.biggerThan(
                    this.heapArray.getCase(right),
                    this.heapArray.getCase(left));
            if (c1 && c2) smallest = left;
            else smallest = right;
            if (!this.heapArray.biggerThan(top, this.heapArray.getCase(smallest)))
                break;
            this.heapArray.swap(index, smallest);
            index = smallest;
        }
    }

    /**
     * Construit un tas binaire à partir de la liste représentant le tas.
     */
    public void makeHeap() {
        int size = this.heapArray.getSize();
        for (int i = (size / 2) - 1; i >= 0; i--) {
            this.trickleDown(i);
        }
    }

    /**
     * Trie le tas binaire et renvoie une nouvelle liste triée.
     *
     * @return Une nouvelle liste triée à partir du tas binaire.
     */
    public CustomList<T> sort() {
        List<T> sorted = new ArrayList<>();
        this.makeHeap();
        for (int i = 0; i <= this.heapArray.getSize() - 1; i++) {
            sorted.add(this.remove());
        }
        this.displayHeap();
        return new CustomList<>(sorted);
    }

    /**
     * Incrémente la taille actuelle du tas binaire.
     */
    public void incrementSize() {
        currentSize++;
    }

    /**
     * Affiche le tas binaire.
     */
    public void displayHeap() {
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // current item
        String dots = "...............................";
        System.out.println(dots + dots); // ligne supérieure pointillée
        while (currentSize > 0) // pour chaque élément du tas
        {
            if (column == 0) // premier élément de la ligne ?
                for (int k = 0; k < nBlanks; k++) // espaces précédents
                    System.out.print(" ");
            // afficher l'élément
            System.out.print(this.heapArray.getCase(j));
            if (++j == currentSize) // terminé ?
                break;
            if (++column == itemsPerRow) // fin de la ligne ?
            {
                nBlanks /= 2; // diviser par deux les espaces
                itemsPerRow *= 2; // doubler les éléments
                column = 0; // recommencer
                System.out.println(); // nouvelle ligne
            } else // prochain élément sur la ligne
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print("  "); // espaces intérimaires
        } // fin pour
        System.out.println("\n" + dots + dots); // ligne inférieure pointillée
    } // fin displayHeap()

  
    public CustomList<T> getHeapArray() {
        return heapArray;
    }

    
    public void setHeapArray(CustomList<T> heapArray) {
        this.heapArray = heapArray;
    }

   
    public int getMaxSize() {
        return maxSize;
    }

   
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

  
    public int getCurrentSize() {
        return currentSize;
    }


    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }
}
