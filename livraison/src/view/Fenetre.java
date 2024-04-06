package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import controller.*;

import java.awt.event.*;

/**
 * La classe Fenêtre représente la fenêtre qui va accueillir les différents
 * écrans de contenus.
 * Elle hérite de JFrame.
 * 
 * Cette classe est utilisée comme la fenêtre principale de l'application,
 * intégrant différents écrans pour la visualisation et l'analyse
 * d'algorithmes de tri.
 * 
 * Elle est associée à un AnalyseController pour gérer les interactions entre
 * la vue et modele.
 * 
 * @author BORIE Baptiste
 * 
 * @see JFrame
 */
public class Fenetre extends JFrame {

    private MainController controller;


    /**
     * Constructeur de la classe Fenetre qui initialise le titre de la fenêtre à
     * "Analyse d'algorithme de tri" et défini comme écran de contenu par défaut
     * la classe EcranMenu.
     * 
     * @param controller Le contrôleur associé à la fenêtre.
     */
    public Fenetre(MainController controller) {
        super("Analyse d'algorithme de tri");
        this.controller = controller;
        this.set(new EcranVisualiser(this, controller));//actuellement EcranVisualiser pour faciliter le debug. 
    }

    // ---------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Cette méthode permet de définir le panneau de contenu de la fenêtre et
     * d'afficher la fenêtre à l'écran.
     * 
     * @param panel Le JPanel à afficher comme panneau de contenu de la fenêtre.
     */
    public void set(JPanel panel) {
        super.setContentPane(panel);
        super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
        super.pack();
        super.setVisible(true);
    }

    /**
     * Retourne la taille préférée de la JFrame Fenetre, correspondant à la taille
     * de l'écran par défaut.
     * 
     * @return La taille préférée de la JFrame Fenetre.
     */
    @Override
    public Dimension getPreferredSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * Retourne la taille maximale de la JFrame Fenetre, correspondant à la taille de
     * l'écran par défaut.
     * 
     * @return La taille maximale de la JFrame Fenetre.
     */
    @Override
    public Dimension getMaximumSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * Rajoute un espace vide à un composant permettant de creer une marge. 
     * 
     * @param size la taille de l'espace à ajouter  
     * @param component le composant auquel se rajoute la marge 
     * @param position la position de la marge (haut/bas/gauche/droite)
     */
    public static void createEmptySpace(int size, JComponent component, String position){
        Insets insets = component.getInsets();
        switch (position) {
            case "t":
                insets.top = size;
                break;
            case "b":
                insets.bottom= size;
                break;
            case "l":
                insets.left = size;
                break;
            case "r":
                insets.right = size;
                break;
            default:
                throw new IllegalArgumentException("Invalid position"+ position);
        }
        component.setBorder(new EmptyBorder(insets));
    }
}
