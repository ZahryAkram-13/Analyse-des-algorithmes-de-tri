package controller;


import model.*;
import model.algorithms.statistics.writers.CsvWriter;
import model.algorithms.statistics.writers.GeneralWriter;


/**
 * La classe AnalyseController sert a lancer le lancement des deux writer afin de recalculer les csv.  
 * @author Baptiste Borie
 * 
 */

public class AnalyseController {


    CustomList maListe;

    public AnalyseController(){
        
        //Complexity.test();
        CsvWriter.test();
        System.out.println("------General WRITER ------");
        GeneralWriter.writeCSV();
    } 
}
