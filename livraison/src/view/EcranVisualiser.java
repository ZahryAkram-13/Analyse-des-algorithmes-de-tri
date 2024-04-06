package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import util.*;
import java.util.List;


import controller.*;
import model.algorithms.*;
import model.generators.*;



/**
 * La classe EcranVisualiser représente un écran de l'interface utilisateur
 * destiné à visualiser et analyser des données à l'aide de plusieurs composants.
 * Elle hérite de JPanel.
 * 
 * Cet écran est composé d'un rectangle central affichant un histogramme basé
 * sur les données du contrôleur, et d'un espace droit affichant un titre et d'autres informations complémentaires.
 * 
 * @author BORIE Baptiste
 * 
 * @see JPanel
 */
public class EcranVisualiser extends JPanel implements Listener {

    private MainController controller;
    private int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private Fenetre frame;
    private JButton buttonStart; 
    private JButton buttonConfirm; 
    private JButton buttonMelange;
    private JPanel OptionPanel;
    private JLabel errorLabel;


    /**
     * Constructeur de la classe EcranVisualiser qui initialise un écran avec
     * un rectangle central contenant un histogramme et un espace droit contenant
     * un titre.
     * 
     * @param frame      La fenêtre principale à laquelle cet écran est associé.
     * @param controller Le contrôleur associé à cet écran.
     */
    public EcranVisualiser(Fenetre frame, MainController controller) {
        this.frame = frame;

        setLayout(new BorderLayout());

        // Panneau pour le rectangle contenant l'algorithme
        JPanel rectanglePanel = new JPanel();
        EcranAlgo ecranAlgo = new EcranAlgo(controller);
        rectanglePanel.add(ecranAlgo);
        rectanglePanel.setPreferredSize(new Dimension(width, height / 2));

        // Panneau droit contenant tous les informations complémentaires
        JPanel espaceDroitPanel = new JPanel();
        espaceDroitPanel.setLayout(new BoxLayout(espaceDroitPanel, BoxLayout.Y_AXIS)); // Utilisation du BoxLayout avec orientation verticale
        
        //Création du titre
        JLabel titre = new JLabel("<html><font color='black'>Paramètre de visualisation</font></html>", SwingConstants.CENTER);
        Fenetre.createEmptySpace(45, titre, "r");
		espaceDroitPanel.add(titre);

        // Création du panneau droit qui va contenir tous les composantns permettant de paramétrer la vue. 
        this.OptionPanel = new JPanel();
        OptionPanel.setLayout(new BoxLayout(OptionPanel, BoxLayout.Y_AXIS));
        espaceDroitPanel.add(OptionPanel);

        // Création du bouton de tri, permettant de lancer le tri de la liste 
        buttonStart = new JButton("Tri");
        buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                controller.updateList();
            }
        });
        Fenetre.createEmptySpace(15, OptionPanel, "l");
        

        // Création du menu déroulant de tous les algorithmes
        List<SortName> sortNames = SortName.SORTSNAMES;
        String[] options = new String[sortNames.size()];
        for (int i = 0; i < sortNames.size(); i++) {
            options[i] = sortNames.get(i).name;
        }
        JComboBox<String> comboBoxAlgo = new CustomComboBox(options);
        // Ajout d'un écouteur pour détecter les changements de sélection
        comboBoxAlgo.addActionListener(e -> {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            String selectedOption = (String) cb.getSelectedItem();
            controller.setAlgoSelected(selectedOption);
        });
        
         // Création du menu déroulant de tous les générateurs
        String[] generatorNames = GeneratorFactory.GENERATOR_NAMES; 
        JComboBox<String> comboBoxGenerator = new CustomComboBox(generatorNames);
        comboBoxGenerator.addActionListener(e -> {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            String selectedOptionGene = (String) cb.getSelectedItem();
            controller.setGeneratorSelected(selectedOptionGene);
        });


        //Création du bouton confirmer afin de changer la taille
        buttonConfirm = new JButton("Confirmer");
        buttonConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                controller.confirmNewParam();
            }
        });
        //par défaut le label est vide on lui ajoute du contenu seulement si le champ d'entrée est invalide.
        this.errorLabel = new JLabel("");
        this.errorLabel.setForeground(Color.RED);
        this.errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        OptionPanel.add(Box.createVerticalStrut(50));
        OptionPanel.add(comboBoxGenerator);
        OptionPanel.add(Box.createVerticalStrut(10));
        OptionPanel.add(comboBoxAlgo);      
        OptionPanel.add(Box.createVerticalStrut(10));
        OptionPanel.add(buttonConfirm);
        OptionPanel.add(Box.createVerticalStrut(10));
        OptionPanel.add(buttonStart);
        OptionPanel.add(Box.createVerticalStrut(40));
        OptionPanel.add(this.errorLabel, BorderLayout.SOUTH);
        
        //Création du panneau de changement de taille de la liste à l'aide du composant CustomTextInput
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        CustomTextInput inputTaille = new CustomTextInput(this,controller); 
        JLabel tailleLabel = new JLabel("Taille de la liste : ");
        inputPanel.add(inputTaille);
        tailleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Création du panneau d'explication 
        JLabel explainLabel = new JLabel();
        explainLabel.setText("<html><style>div{width : 320px;font-size:9px}ul{width : 200px;} li{font-size :9px;}</style><body> Afin de faire fonctionner la vue vous pouvez :<ul><li>Premier menu déroulant : <br>Permet d'appliquer un générateur de désordre.</li><li>Second menu déroulant : <br>Permet de changer l'algorithme de tri à appliquer.<br>Puis veuillez utiliser le bouton confirmer .</li><li>Bouton confirmer : <br>Permet de confirmer l'algorithme de tri choisi.</li><li>Bouton Tri: <br>Permet de lancer le tri sur la liste.</li><li>Taille de la liste: <br>La taille de la liste est définie dans le champ de texte.<br> Veuillez confirmer à l'aide du bouton.</li></ul><div>Attention : <br> Les deux algorithmes avec structures ( Heap, BinaryTree)<br> ne peuvent pas fonctionner avec la vue de part leur structure.<br> BitonicsSort nécessite une liste de taille n². <br> La progression de JavaSort n'est pas visible<br> car nous n'avons accès au code. <br> Pour rappel : <br>vitesse rendu visuelle != vitesse réelle de l'algo.</div></body></html>");
        inputPanel.add(explainLabel);        
        explainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        OptionPanel.add(tailleLabel);
        OptionPanel.add(inputPanel);



        add(rectanglePanel, BorderLayout.CENTER);
        add(espaceDroitPanel, BorderLayout.EAST);
    }

    /**
     * Fonction permettant de réactualiser la vue 
     */
    @Override
    public void update(Object o) {
       this.revalidate();
       this.repaint();
    }

    /**
     * Fonction permettant de changer la valeur du errorLabel si le texte entrée est incorrect. 
     * 
     * @param message message d'erreur a rajouter
     */
    public void error(String message){
        this.errorLabel.setText(message);
        this.revalidate();
    }
}
