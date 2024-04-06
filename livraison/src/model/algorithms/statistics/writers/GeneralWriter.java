package model.algorithms.statistics.writers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.CustomList;
import model.algorithms.SortFunction;
import model.algorithms.SortName;
import model.generators.*;
import model.algorithms.statistics.Complexity;
import model.algorithms.sorts.mediator.*;


/**
 * La classe GeneralWriter est celle qui permet de lancer le calcul en temps de l'ensemble des algorithmes.
 * 
 * La classe contient deux boucles principales, une sur l'ensemble de la liste des algorithmes et l'autre sur l'ensemble des générateurs. De cette façon pour chaque algorithme dans la liste "SortNames" la classe va rechercher le fichier csv dans lequel écrire selon la syntaxe "compexlity_nomAlgorithme.csv". Une fois le fichier trouvé il va lancer le test de complexité afin de savoir le temps que l'algorithme met à trier la liste. Ceci est répété pour chaque générateur, puis la boucle passe à l'algorithme suivant. 
 * 
 * @author Baptiste Borie
 * @author Benjamin Boutrois

 */

public class GeneralWriter {

    public static final String PATH = "src/model/algorithms/statistics/csv/";
    private static String FILE_NAME = "complexityGeneral.csv";
    
    public static void writeCSV() {
        CustomList<Integer> list = CustomList.generateUniqueIntegerList(10);
        Mediator<Integer> mediator = new SortCreatorMediator<>();
        GeneratorFactory geneF = new GeneratorFactory();
    
        List<SortName> sortNames = SortName.SORTSNAMES;
        String[] generatorNames = GeneratorFactory.GENERATOR_NAMES;
        
         
        for (SortName sortName : sortNames) {
            String sortFileName = "complexity_" + sortName.name() + ".csv";
            try (FileWriter writer = new FileWriter(PATH + sortFileName)) {
                writer.append("Generator,Time\n");
                int AccessCaseNumber = 0;
                int ComparaisonNumber = 0;
                SortFunction<Integer> sort = mediator.create(sortName, list);
                for (String curr_generator : generatorNames) {
                    Generator<?> generator = geneF.createGenerator(curr_generator);
                    Complexity<Integer> complexity = new Complexity<>(sort, generator, 1);
                    List<Double> complexities = complexity.getInfo().get(Complexity.TIME);
                    
                    AccessCaseNumber += sort.getList().getAccessCaseNumber();
                    ComparaisonNumber += sort.getList().getComparaisonNumber();
                    // Calcule la moyenne
                    double totalTime = 0.0;
                    for (Double time : complexities) {
                        totalTime += time;
                    }
                    double averageTime = totalTime / complexities.size();
                    

                    writer.append(curr_generator).append(",").append(String.valueOf(averageTime)).append("\n");
                }
                int averageAcess = AccessCaseNumber/generatorNames.length;
                int averageComparasion = ComparaisonNumber/generatorNames.length;
                System.out.println(
                    "SortName : "+ sort.name()+ "\n"+
                    "AccessCaseNumber: " + (averageAcess)+ "\n" +
                    "ComparaisonNumber: " + (averageComparasion) + "\n" 
                );

                System.out.println("CSV file has been written to: " + PATH + sortFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }
    
    
}