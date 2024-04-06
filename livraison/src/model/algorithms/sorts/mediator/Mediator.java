package model.algorithms.sorts.mediator;

import model.CustomList;
import model.algorithms.SortName;
import model.algorithms.SortFunction;

/**
 * Interface Médiateur pour la création d'instances de SortFunction.
 *
 * @param <T> Le type des éléments dans CustomList.
 * @author Zahry Akram
 */
public interface Mediator<T extends Comparable<T>> {

    /**
     * Crée et renvoie une instance de SortFunction basée sur le SortName et CustomList fournis.
     *
     * @param name Le SortName spécifiant le type d'algorithme de tri.
     * @param list La CustomList à trier.
     * @return Une instance de SortFunction correspondant au SortName spécifié.
     */
    SortFunction<T> create(SortName name, CustomList<T> list);

}
