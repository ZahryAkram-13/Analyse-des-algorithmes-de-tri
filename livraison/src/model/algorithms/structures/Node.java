package model.algorithms.structures;
/**
 * Représentation d'un nœud dans un arbre binaire.
 *
 * @param <T> Le type des éléments dans le nœud, doit être comparable.
 * @author Zahry Akram
 */
public class Node<T extends Comparable<T>> {

    /** Valeur du nœud. */
    private T value;

    /** Nœud fils gauche. */
    private Node<T> left;

    /** Nœud fils droit. */
    private Node<T> right;

    /**
     * Constructeur d'un nœud avec une valeur spécifiée et des nœuds fils.
     *
     * @param value La valeur du nœud.
     * @param left  Le nœud fils gauche.
     * @param right Le nœud fils droit.
     */
    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Constructeur d'un nœud avec une valeur spécifiée et des nœuds fils initialisés à null.
     *
     * @param value La valeur du nœud.
     */
    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /**
     * Vérifie si le nœud a un fils gauche.
     *
     * @return {@code true} si le nœud a un fils gauche, sinon {@code false}.
     */
    public boolean hasLeft() {
        return this.left != null;
    }

    /**
     * Vérifie si le nœud a un fils droit.
     *
     * @return {@code true} si le nœud a un fils droit, sinon {@code false}.
     */
    public boolean hasRight() {
        return this.right != null;
    }

    
    /**
     * Compare deux éléments et retourne vrai si le premier est plus grand que le deuxième.
     *
     * @param a Le premier élément à comparer.
     * @param b Le deuxième élément à comparer.
     * @param <T> Le type des éléments, doit être comparable.
     * @return {@code true} si le premier élément est plus grand que le deuxième, sinon {@code false}.
     */
    public static <T extends Comparable<T>> boolean biggerThan(T a, T b) {
        return a.compareTo(b) > 0;
    }

    /**
     * Insère un nouveau nœud dans l'arbre avec une valeur spécifiée.
     *
     * @param parent Le nœud parent dans lequel insérer le nouveau nœud.
     * @param value  La valeur du nouveau nœud.
     * @param <T>    Le type des éléments, doit être comparable.
     * @return Le nouveau nœud inséré.
     */
    public static <T extends Comparable<T>> Node<T> insert(Node<T> parent, T value) {
        if (parent != null) {
            Node<T> node = new Node<T>(value);
            if (Node.biggerThan(value, parent.getValue())) {
                if (parent.hasRight()) Node.insert(parent.getRight(), value);
                else parent.right = node;
            } else {
                if (parent.hasLeft()) Node.insert(parent.getLeft(), value);
                else parent.left = node;
            }
            return node;
        }
        return null;
    }

 
    public T getValue() {
        return value;
    }


    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

 
    public void setLeft(Node<T> left) {
        this.left = left;
    }

  
    public Node<T> getRight() {
        return right;
    }

  
    public void setRight(Node<T> right) {
        this.right = right;
    }
}
