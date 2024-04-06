package view;
import javax.swing.*;

import controller.MainController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe CustomTextInput représente un composant d'entrée de texte personnalisé avec un bouton "OK".
 * Elle hérite de JPanel.
 * 
 * Ce composant est utilisé pour saisir des valeurs numériques afin de mettre à jour la taille de la liste. 
 * 
 * Lorsque l'utilisateur appuie sur le bouton "OK", le texte saisi est converti en entier. Si la conversion réussit, le contrôleur est notifié du changement de valeur.
 * Si la conversion échoue (c'est-à-dire si le texte saisi n'est pas un nombre valide), un message d'erreur est affiché dans la vue parente.
 * 
 * @param parent     La vue parente à laquelle ce composant est associé.
 * @param controller Le contrôleur associé à ce composant.
 * 
 * @author Baptiste Borie 
 * @see JPanel
 */


public class CustomTextInput extends JPanel {
    private JTextField textField;
    private JButton okButton;
    private MainController controller;

    public CustomTextInput(EcranVisualiser parent, MainController controller) {
        
        textField = new JTextField(10);
        okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                try {

                    int number = Integer.parseInt(input);
                    if (number > 0) { // Vérifie si le nombre est supérieur à zéro
                        // Affiche le nombre dans la console (à des fins de test)
                        controller.changeList(number);
                        parent.error("");
                    } else {
                        parent.error("Le nombre doit être supérieur à zéro");
                    }
                } catch (NumberFormatException ex) {
                    parent.error("Les données entrée doivent être un entier positif");
                }
            }
        });

        this.add(textField);
        this.add(Box.createVerticalStrut(10)); 
        this.add(okButton);
        
    }

    @Override
    public Dimension getMaximumSize() {
        int hauteur = 180; 
        int largeur = 180; 
        return new Dimension(largeur, hauteur);
    }


}