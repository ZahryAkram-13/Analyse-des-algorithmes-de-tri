package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import model.generators.*;

/**
 * La classe CustomList représente une liste personnalisée avec diverses méthodes utilitaires pour la manipulation et les tests.
 * 
 * @param <T> le type des éléments dans la liste, doit être Comparable
 * @author Zahry Akram
 */
public class CustomList<T extends Comparable<T> > {
	
	public static final String LIST_IS_NULL = "liste vide";

    public static List<Integer> LISTTEST = Arrays.asList(0, 2, 1, 3, 4);

    private List<T> list;               // La liste sous-jacente
    private T max;                       // Élément maximal dans la liste
    private T min;                       // Élément minimal dans la liste

    private Integer AccessCaseNumber;    // Nombre d'accés pendant les opérations
    private Integer ComparaisonNumber;   // Nombre de comparaisons pendant les opérations

    private Integer size;                // Taille de la liste

    /**
     * Construit une CustomList avec la liste donnée d'éléments.
     * 
     * @param list la liste d'éléments
     */
    public CustomList(List<T> list) {
        this.list = list;
        this.min = Collections.min(this.list); // O(n)
        this.max = Collections.max(this.list); // O(n)
        this.setSize(list.size()); // On met à jour la taille
        this.AccessCaseNumber = 0;
        this.ComparaisonNumber = 0;
    }

    /**
     * Construit une CustomList vide.
     */
    public CustomList() {
        this.list = new ArrayList<T>();
        this.min = null; // O(n)
        this.max = null; // O(n)

        this.setSize(0); // On met à jour la taille
        this.AccessCaseNumber = 0;
        this.ComparaisonNumber = 0;
    }

    /**
     * Méthode statique pour tester la classe CustomList.
     */
    public static void test() {
        CustomList<Integer> list = CustomList.generateIntegerList(10);
        System.out.print(list.slice(0, 5));
    }

    /**
     * Compare deux éléments pour vérifier si le premier élément est plus grand que le second.
     * Incrémente le compteur de comparaisons.
     * 
     * @param a le premier élément
     * @param b le second élément
     * @return true si le premier élément est plus grand que le second, false sinon
     */
    public boolean biggerThan(T a, T b) {
        this.ComparaisonNumber++;
        return a.compareTo(b) > 0;
    }

    /**
     * Compare deux éléments pour vérifier si le premier élément est plus grand que ou égal au second.
     * Incrémente le compteur de comparaisons.
     * 
     * @param a le premier élément
     * @param b le second élément
     * @return true si le premier élément est plus grand que ou égal au second, false sinon
     */
    public boolean biggerThanOrEqual(T a, T b) {
        this.ComparaisonNumber++;
        return a.compareTo(b) >= 0;
    }
    
    public CustomList<Integer> generateList(int n){
        CustomList<Integer> list = listGenerator.generateIncreasingIntegerSerie(n);
        return list;
    }

    /**
     * Génère une CustomList d'entiers avec des valeurs aléatoires.
     * 
     * @param n la taille de la liste
     * @return une CustomList d'entiers avec des valeurs aléatoires
     */
    public static CustomList<Integer> generateIntegerList(int n) {
        if (n > 0) {
            Random rand = new Random();
            List<Integer> list = new ArrayList<>();
            while (list.size() != n) {
                Integer value = rand.nextInt(10);
                list.add(value);
            }
            return new CustomList<Integer>(list);
        }
        return null;
    }

    /**
     * Génère une CustomList d'entiers uniques avec des valeurs aléatoires.
     * 
     * @param n la taille de la liste
     * @return une CustomList d'entiers uniques avec des valeurs aléatoires
     */
    public static CustomList<Integer> generateUniqueIntegerList(int n) {
        if (n > 0) {
            Set<Integer> uniques = new HashSet<>();
            Random random = new Random();
            while (uniques.size() < n) {
                int value = (int) random.nextInt(10000); // Vous pouvez ajuster cette valeur selon vos besoins
                uniques.add(value);
            }
            return new CustomList<Integer>(new ArrayList<Integer>(uniques));
        }
        return null;
    }

    /**
     * Joint la liste actuelle avec une autre liste.
     * 
     * @param l l'autre liste à joindre
     * @return la liste actuelle après le joint
     */
    public CustomList<T> join(CustomList<T> l) {
        l.getList().forEach(elem -> {
            list.add(elem);
        });
        return this;
    }

    /**
     * Définit la valeur d'un élément à un index spécifié.
     * 
     * @param i l'index de l'élément
     * @param value la nouvelle valeur
     */
    public void set(int i, T value) {
        this.list.set(i, value);
    }

    /**
     * Crée une sous-liste de la liste actuelle, à partir de l'index 'n' jusqu'à l'index 'm' (inclus).
     * 
     * @param n l'index de départ
     * @param m l'index de fin
     * @return une nouvelle CustomList représentant la sous-liste
     */
    public CustomList<T> slice(int n, int m) {
        boolean validIndexes = this.validIndex(n) && this.validIndex(m);
        if (validIndexes) {
            List<T> sublist = new ArrayList<>();
            for (int i = n; i <= m; i++) {
                sublist.add(this.getCase(i));
            }
            return new CustomList<T>(sublist);
        }
        return null;
    }

    /**
     * Vérifie si un index est valide dans la liste.
     * 
     * @param i l'index à vérifier
     * @return true si l'index est valide, false sinon
     */
    public boolean validIndex(int i) {
        return i >= 0 && i <= this.size - 1;
    }

    /**
     * Échange les éléments aux index spécifiés.
     * 
     * @param i le premier index
     * @param j le second index
     * @return la CustomList actuelle après l'échange
     */
    public CustomList<T> swap(int i, int j) {
        boolean validIndexes = this.validIndex(i) && this.validIndex(j);
        if (validIndexes) {
            T temp = this.list.get(i);
            this.list.set(i, this.list.get(j));
            this.list.set(j, temp);
            return this;
        }
        return null;
    }

    /**
     * Ajoute un élément à la liste.
     * 
     * @param elem l'élément à ajouter
     */
    public void add(T elem) {
        this.list.add(elem);
        this.setSize(list.size()); // On met à jour la taille
    }
    
    /**
     * Ajoute un élément à la liste à l'index index.
     *
     * @param index L'index auquel ajouter l'élément à ajouter.
     * @param elem L'élément à ajouter.
     */
    public void addI(Integer index,T elem){
		this.list.add(index, elem);
        this.setSize(list.size()); // On met à jour la taille
	}

    /**
     * Enlève un élément à l'index index.
     * 
     * @param index L'index de l'élément à retirer.
     */
    public void remove(int index){
		this.list.remove(index);
        this.setSize(list.size()); // On met à jour la taille
	}


    /**
     * Récupère l'élément à l'index spécifié.
     * Incrémente le compteur de cas accédés.
     * 
     * @param i l'index de l'élément
     * @return l'élément à l'index spécifié
     */
    public T getCase(int i) {
        if (this.validIndex(i)) {
            this.AccessCaseNumber++;
            return this.list.get(i);
        }
        return null;
    }
    
    public void destroyList() {
    	this.list = null;
    	this.AccessCaseNumber = null;
    	this.ComparaisonNumber = null;
    	this.max = null;
    	this.min = null;
    this.setSize(0); // on remet la taiile à 0
    }

    /**
     * Récupère le dernier élément de la liste.
     * 
     * @return le dernier élément de la liste
     */
    public T getLast() {
        return this.list.get(this.size - 1);
    }

    /**
     * Récupère le premier élément de la liste.
     * 
     * @return le premier élément de la liste
     */
    public T getFirst() {
        return this.list.get(0);
    }

    /**
     * Remplace la méthode toString par défaut pour fournir une représentation sous forme de chaîne de la liste.
     * 
     * @return une représentation sous forme de chaîne de la liste
     */
    @Override
    public String toString() {
        return this.list.toString();
    }

    /**
     * Récupère la liste sous-jacente.
     * 
     * @return la liste sous-jacente
     */
    public List<T> getList() {
        return this.list;
    }

    /**
     * Récupère le nombre de cas accédés pendant les opérations.
     * 
     * @return le nombre de cas accédés
     */
    public Integer getAccessCaseNumber() {
        return AccessCaseNumber;
    }

    /**
     * Définit le nombre de cas accédés pendant les opérations.
     * 
     * @param accessCasecounter le nombre de cas accédés à définir
     */
    public void setAccessCasecounter(Integer accessCasecounter) {
        AccessCaseNumber = accessCasecounter;
    }

    /**
     * Récupère le nombre de comparaisons pendant les opérations.
     * 
     * @return le nombre de comparaisons
     */
    public Integer getComparaisonNumber() {
        return ComparaisonNumber;
    }

    /**
     * Définit le nombre de comparaisons pendant les opérations.
     * 
     * @param comparaisonNumber le nombre de comparaisons à définir
     */
    public void setComparaisonNumber(Integer comparaisonNumber) {
        ComparaisonNumber = comparaisonNumber;
    }

    /**
     * Récupère l'élément maximal dans la liste.
     * 
     * @return l'élément maximal dans la liste
     */
    public T getMax() {
        return max;
    }

    /**
     * Définit l'élément maximal dans la liste.
     * 
     * @param max l'élément maximal à définir
     */
    public void setMax(T max) {
        this.max = max;
    }

    /**
     * Récupère l'élément minimal dans la liste.
     * 
     * @return l'élément minimal dans la liste
     */
    public T getMin() {
        return min;
    }

    /**
     * Définit l'élément minimal dans la liste.
     * 
     * @param min l'élément minimal à définir
     */
    public void setMin(T min) {
        this.min = min;
    }

    /**
     * Récupère la taille de la liste.
     * 
     * @return la taille de la liste
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Définit la taille de la liste.
     * 
     * @param size la taille à définir
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Définit la liste sous-jacente.
     * 
     * @param list la liste à définir
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * Vérifie si la liste est vide.
     *
     * @return true si la liste est vide, sinon false.
     */
    public boolean isEmpty(){
		return this.list.isEmpty();
	}
    
    /**
     * Vérifie si tous les éléments de otherList sont compris dans cette instance de CustomList.
     *
     * @param otherList La liste contenue ou non dans cette instance de CustomList.
     * @return Un booléen correspondant à la présence de tous les éléments de otherList dans cette instance de CustomList.
     */
    public boolean containsAll(CustomList<T> otherList) {
        for (T item : otherList.getList()) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

	/**
	 * Vérifie si la liste contient un élément.
	 *
	 * @param element l'élément à vérifier.
	 * @return Un booléen correspondant à la présence de l'élément dans la liste.
	 */ 
	public boolean contains(T element) {
        return this.list.contains(element);
    }

	/** Cette méthode permet de cloner proprement une CustomList, en créant un nouvel objet CustomList qui contiendra exactement les mêmes éléments.
	 * Cette méthode requiert que les éléments composant la CustomList implémentent l'interface "Cloneable", sinon leur copie se fera sur la même référence.
	 * 
	 * @return La nouvelle CustomList clonée.
	 */
	@SuppressWarnings("unchecked")
    public CustomList<T> clone() {
		CustomList<T> newList = new CustomList<>();
		for (T item : this.list) {
			if (item instanceof Cloneable) {
				try {
					newList.add((T)item.getClass().getMethod("clone").invoke(item));
				} catch (Exception e) {
					// Gère l'exception en ajoutant une copie par défaut de l'élément
					newList.add(item);
				}
			} else {
				// Si l'objet ne peut pas être cloné, ajoute simplement la référence
				newList.add(item);
			}
		}
		newList.setMin(this.min);
		newList.setMax(this.max);
		newList.setSize(this.size);
		newList.setAccessCasecounter(this.AccessCaseNumber);
		newList.setComparaisonNumber(this.ComparaisonNumber);
		return newList;
	}
}
