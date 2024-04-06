

package util;

/**
 * L'interface Listenable définit le contrat pour une classe d'écouteurs.
 */
public interface Listenable{
  public abstract void addListener(Listener l);
  public abstract void dropListener(Listener l);
}
