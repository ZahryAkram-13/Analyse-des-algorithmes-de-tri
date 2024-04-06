package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;
import model.algorithms.*;
import model.algorithms.sorts.*;
import model.algorithms.sorts.mediator.SortCreatorMediator;
import util.*;
import model.generators.*;
import model.generators.concreteGenerators.CaesarGenerator;
import view.*;

import java.lang.Thread;

/**
 * La classe mainController fait office de controleur au sein de notre application. Dans le contexte d'une architecture MVC le controleur sert de "relais" entre la vue et le modèle. Les changement de liste sont notifiés ici afin d'adapter la vue, inversement un changement dans la vue sera renvoyé ici afin d'adapter le modèle en conséquence. 
 * @author Baptiste Borie
 * 
 * @see JComboBox
 */


public class MainController implements Listener {
    private Fenetre fenetre;
    private CustomList maListe;
    private EcranAlgo ecran;
    private EcranVisualiser ecranVisualiser;
    private String nomGene;
    private  SortFunction<?> selectionSort; 
    private SortName  nomAlgo ; 
    private Generator<?> selectedGenerator;
    private  SortCreatorMediator<?> mediator ;
    private List<Listener> listeners = new ArrayList<>();

    public MainController() {
        this.mediator = new SortCreatorMediator<>();
        this.maListe = maListe.generateIntegerList(1); 
        this.maListe = maListe.generateList(20); 
        this.selectionSort = new BinaryTreeSort<>(maListe);
        this.selectionSort.addListener(this);
        this.selectedGenerator = new CaesarGenerator<>();
        this.fenetre = new Fenetre(this);
        this.ecran = (EcranAlgo)listeners.get(0);
    }

    public CustomList<?> getList() {
        return this.maListe;
    }    

    /**
     * Notifie tous les auditeurs en cas de changement
     */
    public void fireChange() {
        for (Listener listener : listeners) {
            listener.update(selectionSort.sort()); 
        }
    }

        /**
     * Récupère la dernière entrée clavier via un écouteur.
     */
    
    public void updateList(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                fireChange();
            }
        });
        thread.start();
    }

        /**
     * Permet d'ajouter un écouteur.
     * 
     * @param listener L'écouteur à ajouter.
     */
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    /**
     * Permet de retirer un écouteur.
     * 
     * @param listener L'écouteur à retirer.
     */
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
    

        /**
     * Permet de mettre a jour tous les listeners
     * 
     * @param o l'information a transmettre lors de l'update.
     */
    @Override
    public void update(Object o){
        for (Listener listener : listeners) {
            listener.update(o);
        }
    }

        /**
     * Permet de changer le nom de l'algo selectionné
     * 
     * @param selected le nom du nouvel algo selectionné.
     */
    public void  setAlgoSelected(String selected){
        SortName sortName =SortName.getSortByName(selected);
        this.nomAlgo = sortName;
    } 
    
        /**
     * Permet de changer le générateur selectionné à partir d'un nom donné. la fonction va également lancé le générateur de désordre sur la liste juste après l'avoir changé.
     * 
     * @param selectedGene le nom du générateur selectionné .
     */
    public void  setGeneratorSelected(String selectedGene){
        GeneratorFactory geneF = new GeneratorFactory();
        this.nomGene = selectedGene;
        Generator<?> geneName = geneF.createGenerator(this.nomGene);
        this.selectedGenerator= geneName;
        this.selectedGenerator.disorderAlgorithm(this.maListe);
        update(this.maListe);
    } 
    
        /**
     * Permet de redéfinir tous les parametres. Permet aussi d'établier les paramètres par défaut. 
     * 
     */
    public void confirmNewParam(){
    if(this.nomAlgo == null) {
        this.nomAlgo = SortName.BinaryTreeSort;
    }
    if(this.nomGene == null) {
        this.nomGene = "caesar";
    }
    SortFunction<?> sortFunction = mediator.create(this.nomAlgo, this.maListe);
    this.selectionSort = sortFunction;
    this.selectionSort.addListener(this);
    System.out.println("Algo : " + this.selectionSort.name()+" Gene : " + this.nomGene + " taille de la liste : " + this.maListe.getSize());
   }

   /**
    * Permet de changer la taille de la liste.
    * @param number la taille de la nouvelle liste. 
    */
   public void changeList(int number){
    this.maListe = maListe.generateList(number);
    this.ecran.updateList(this.maListe);
    update(this.maListe);
   }
}
