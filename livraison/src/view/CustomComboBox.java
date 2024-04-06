package view; 

import java.awt.*;

import javax.swing.*;


/**
 * La classe CustomComboBox représente une boîte de liste déroulante personnalisée.
 * Elle hérite de JComboBox<String>.
 * 
 * Ce menu déroulant est utilisée pour afficher une liste d'options définie dans le composant parent.
 * Chaque option est représentée par une chaîne de caractères.
 * ²
 * La classe inclut un rendu personnalisé pour les éléments de la liste déroulante afin de contrôler leur apparence.
 * 
 * Les dimensions de le menu déroulant sont définies de manière à avoir une hauteur fixe de 30 pixels et une largeur fixe de 120 pixels.
 * 
 * @param options Un tableau de chaînes de caractères représentant les options à afficher dans le menu deroulant.
 * 
 * @author Baptiste Borie
 * 
 * @see JComboBox
 */


public class CustomComboBox extends JComboBox<String> {


    public CustomComboBox(String [] options) {

        super(options);
        setRenderer(new CustomComboBoxRenderer());
        setMaximumSize(getPreferredSize()); 
    }

    private class CustomComboBoxRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            return label;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int hauteur = 30; 
        int largeur = 120; 
        return new Dimension(largeur, hauteur);
    }
}
