package model.algorithms.statistics.writers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.algorithms.SortName;
import model.algorithms.SortFunction;
import model.algorithms.sorts.mediator.*; 
import model.CustomList;

import model.algorithms.statistics.Complexity;
import model.generators.*;


public class CsvWriter implements Writer{

    public static final String PATH = "src/model/algorithms/statistics/csv/";
    private static final String  FILE_NAME = "complexity.csv";

    @Override
    public void write(){
        CsvWriter.init();
        System.out.println("wrote;");
    }

    public static void init(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the Sort");
        String sortName = scanner.nextLine();
        System.out.println("Enter the name of the Generator ");
        String generatorName = scanner.nextLine();
        //generatorName = AlmostSortedGenerator.ALMOST_SORTED_GENERATOR;
        generatorName = "almost reverse";
        sortName = SortName.FusionSort.name();

        scanner.close();
        Mediator<Integer> mediator = new SortCreatorMediator<Integer>();
        SortFunction<Integer> sort  = mediator.create(SortName.getSortByName(sortName), CustomList.generateUniqueIntegerList(10));

        //Generator generator = new AlmostSortedGenerator();
        GeneratorFactory<Integer> geneF = new GeneratorFactory<Integer>();
        Generator<Integer> generator = geneF.createGenerator(generatorName);
        
        Complexity<Integer> complexity = new Complexity<Integer>(sort, generator, 100);
        System.out.println("u have chosen :" + sortName + " and " + generatorName);

        CsvWriter.writeCSV(CsvWriter.PATH + CsvWriter.FILE_NAME, complexity);

    }

    public static void test(){
        CustomList<Integer> list = CustomList.generateUniqueIntegerList(10);
        Mediator<Integer> mediator = new SortCreatorMediator<Integer>();
        SortFunction<Integer> sort  = mediator.create(SortName.FusionSort, list);
        
        GeneratorFactory geneF = new GeneratorFactory();
        Generator<?> generator = geneF.createGenerator("casear");
        //System.out.println(sort.getInfo());

        Complexity<Integer> complexity = new Complexity<>(sort, generator, 10);

        CsvWriter.writeCSV(CsvWriter.PATH + CsvWriter.FILE_NAME, complexity);

    }

    public static void writeHeader(FileWriter writer, Map<String, String> header) throws IOException{
        if(writer == null) throw new IllegalArgumentException("no writer is found");
        for(String key : header.keySet()){
            writer.append("#" + key + "," + header.get(key) + "\n");
        }
    }

    public static void writeCSV(String filePath, Complexity complexity) {

        try (FileWriter writer = new FileWriter(filePath)) {
            Map<String, List<Double>> data = complexity.getInfo();
            // Écrire l'en-tête2
            writer.append("size,time\n");

            // Écrire les données
            List<Double> sizes = data.get(Complexity.SIZE);
            List<Double> complexities = data.get(Complexity.TIME);

            for (int i = 0; i < sizes.size(); i++) {
                writer.append(sizes.get(i).toString());
                writer.append(",");
                writer.append(complexities.get(i).toString());
                writer.append("\n");
            }
            //CsvWriter.writeHeader(writer,complexity.getHeader());
            
            System.out.println("CSV file have written !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
