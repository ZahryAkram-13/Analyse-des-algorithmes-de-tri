package view; 

import java.awt.*;

import javax.swing.*;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.MainController;
import model.CustomList;
import util.*;

/**
 * La classe EcranAlgo représente un écran de l'interface utilisateur
 * destiné à afficher un histogramme basé sur les données d'un objet CustomList.
 * Elle hérite de JPanel.
 * 
 * Cette classe utilise la bibliothèque JFreeChart pour créer et afficher
 * un histogramme à barres à partir des données fournies par le contrôleur.
 * 
 * @author BORIE Baptiste
 *
 * @see JPanel
 */
public class EcranAlgo extends JPanel implements Listener {

    private MainController controller;
    private CustomList maList;
    private int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private JFreeChart chart;
    private ChartPanel chartPanel;

    /**
     * Constructeur de la classe EcranAlgo qui initialise un écran avec un histogramme
     * basé sur les données fournies par le contrôleur.
     * 
     * @param controller Le contrôleur associé à cet écran.
     */
    public EcranAlgo(MainController controller) {
        this.controller = controller;
        this.maList = controller.getList();
        this.controller.addListener(this);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        createChart();
        add(chartPanel);
    }

    /**
     * Crée et configure le graphique (histogramme). L'histogramme est crée avec un theme personalisé. 
     */
    private void createChart() {
        CategoryDataset dataset = createDataset();
        StandardChartTheme theme = new StandardChartTheme("MainTheme", true);
        Color color = new Color(173, 216, 230);
        theme.setPlotBackgroundPaint(color);
        theme.setChartBackgroundPaint(color.brighter());
        ChartFactory.setChartTheme(theme);
        chart = ChartFactory.createBarChart("Histogramme", "", "Valeurs", dataset);
        chartPanel = new ChartPanel(chart);
    }

    /**
     * Crée et retourne un ensemble de données pour l'histogramme.
     * 
     * @return Le dataset contenant les données à afficher dans l'histogramme.
     */
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < controller.getList().getSize(); i++) {
            int tmp = (Integer) maList.getList().get(i);
            dataset.addValue(tmp, "Serie", ""+tmp);
        }
        return dataset;
    }

    /**
     * Met à jour l'interface utilisateur en réponse aux changements dans le modèle.
     */
    @Override
    public void update(Object o) {
        refreshListDisplay();
    }

    /**
     * Rafraîchit l'affichage de l'histogramme avec les nouvelles données.
     */
    private void refreshListDisplay() {
        try {
            Thread.sleep(5); //Changer en fonction de maList.getSize()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CategoryDataset dataset = createDataset();
        chart.getCategoryPlot().setDataset(dataset);
        chartPanel.repaint();

    }

    /**
     * Retourne la taille préférée de la JPanel EcranAlgo.
     * 
     * @return La taille préférée de la JPanel EcranAlgo.
     */
    @Override
    public Dimension getPreferredSize() {
        // Obtenez les dimensions du conteneur parent
        Dimension parentSize = getParent().getSize();
        
        // Calculez les dimensions préférées en fonction des dimensions du parent
        int preferredWidth = parentSize.width / 3 + 1000;
        int preferredHeight = parentSize.height / 2 + 465;
    
        return new Dimension(preferredWidth, preferredHeight);
    }

    public void updateList(CustomList<?> list){
        this.maList = list;
    }
}
