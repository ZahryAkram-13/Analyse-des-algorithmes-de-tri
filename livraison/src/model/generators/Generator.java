package model.generators;
import model.CustomList;

/**
 * La classe abstraite Generator définit le contrat pour les générateurs. Les classes qui implémentent cette classe abstraite représentent différents générateurs de désordre.
 * 
 * @author BOUTROIS Benjamin
 */
public abstract class Generator <T extends Comparable<T>>{

    String name;
    /** Renvoie le nom du générateur.
     * 
     * @return Le nom du générateur.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Modifie la CustomList en argument en y appliquant a sa liste un desordre specifique a chaque classe.
     */
    public abstract void disorderAlgorithm(CustomList<T> liste);
}