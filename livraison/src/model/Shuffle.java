package model;

import util.*;

public class Shuffle extends ListenableModel {
    
    private CustomList maList;

    public Shuffle(CustomList List){
        this.maList = List;
    }
    
    public <T extends Comparable<T>> CustomList<T> getList(){
        return this.maList;
    }
    public <T extends Comparable<T>> CustomList<T> melange(){
        int size = maList.getSize();
        for(int i = 0; i < size / 2; i++) {
            T temp = (T) maList.getList().get(i);
            maList.getList().set(i, maList.getList().get(size - i - 1));
            maList.getList().set(size - i - 1, temp);
            fireChange(); 
        }
        return this.maList;
    }

    public <T extends Comparable<T>> CustomList<T>  shiftElements() {
        int size = maList.getSize();
        if (size <= 1) {
            // Si la liste est vide ou a seulement un élément, il n'y a rien à faire.
            return maList;
        }
    
        T lastElement = (T) maList.getList().get(size - 1); // Stocker le dernier élément
    
        // Déplacer chaque élément vers la droite
        for (int i = size - 1; i >= 1; i--) {
            T currentElement = (T) maList.getList().get(i - 1);
            maList.getList().set(i, currentElement);
            fireChange(); // Appeler la méthode de changement
        }
    
        // Placer le dernier élément à la première position
        maList.getList().set(0, lastElement);
        fireChange();
    
        return maList;
    }
    
    
    

    @Override
    public void fireChange() {
        for (Listener l : super.listeners) {
            l.update(this);
        }
    }
}
