package util;

import java.util.ArrayList;

/**
 * La classe ListenableModel implémente la gestion des écouteurs.
 */
public abstract class ListenableModel implements Listenable{

	protected ArrayList<Listener> listeners;

	public ListenableModel(){
    this.listeners = new ArrayList<Listener>();
	}

  @Override
  public void addListener(Listener l){
    this.listeners.add(l);
  }

  @Override
  public void dropListener(Listener l){
    this.listeners.remove(l);
  }

  public abstract void fireChange();
}
