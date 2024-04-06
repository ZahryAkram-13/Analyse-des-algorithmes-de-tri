package model.algorithms.structures;

import java.util.ArrayList;

import model.CustomList;
/**
 * Représentation d'un arbre binaire.
 *
 * @param <T> Le type des éléments dans l'arbre binaire, doit être comparable.
 * @author Zahry Akram
 */
public class BinaryTree<T extends Comparable<T>> {

    /** La racine de l'arbre binaire. */
    private Node<T> root;

    /** Liste contenant les éléments triés de l'arbre binaire. */
    private CustomList<T> sorted;

    /**
     * Constructeur de l'arbre binaire avec une racine spécifiée.
     *
     * @param root La racine de l'arbre.
     */
    public BinaryTree(Node<T> root) {
        this.root = root;
        this.sorted = new CustomList<T>();
    }

    
    /**
     * Construit un arbre binaire à partir d'une CustomList.
     *
     * @param list La CustomList utilisée pour construire l'arbre.
     * @return Un arbre binaire construit à partir de la CustomList.
     */
    public static <T extends Comparable<T>> BinaryTree<T> builtTree(CustomList<T> list) {
        Node<T> root = new Node<T>(list.getCase(0));
        BinaryTree<T> tree = new BinaryTree<>(root);
        for (int i = 1; i < list.getSize(); i++) {
            Node.insert(root, list.getCase(i));
        }
        return tree;
    }

    /**
     * Effectue un parcours en ordre de l'arbre binaire et remplit la liste triée.
     *
     * @param root La racine de l'arbre.
     */
    public void traverseInOrder(Node<T> root) {
        if (root != null) {
            this.traverseInOrder(root.getLeft());
            this.sorted.add(root.getValue());
            this.traverseInOrder(root.getRight());
        }
    }

 
    public CustomList<T> getSorted() {
        return sorted;
    }

    public void setSorted(CustomList<T> sorted) {
        this.sorted = sorted;
    }

    
    public Node<T> getRoot() {
        return root;
    }

 
    public void setRoot(Node<T> root) {
        this.root = root;
    }
}

