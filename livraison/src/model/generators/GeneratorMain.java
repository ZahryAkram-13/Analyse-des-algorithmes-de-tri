package model.generators;
import model.CustomList;
/**
 * La classe GeneratorMain sert de test rapide pour debugger. La classe n'est jamais utilisee dans l'application.
 * 
 * @author BOUTROIS Benjamin
 */
public class GeneratorMain{
    public static void main(String[] args) {

        /* 
         * INITIALISATION
         */
        CustomList<Integer> maListe = CustomList.generateIntegerList(10);
        System.out.println("Liste de base : " +maListe);

        /*
         * TEST ADD ET REMOVE
         */
        System.out.println("--------ADD/REMOVE--------");
        maListe.addI(0, 99);
        System.out.println("On ajoute 99 au debut : " +maListe);
        maListe.remove(0);
        System.out.println("On retire le premier element : " + maListe);

        /*
         * TEST REVERSE GENERATOR
         */
        System.out.println("--------REVERSE--------");
        Generator<Integer> monGen = new GeneratorFactory<Integer>().createGenerator("reverse");
        System.out.println("Liste de base : " +maListe);
        monGen.disorderAlgorithm(maListe);
        System.out.println("Liste apres reverse : " + maListe);

        
        /*
        * TEST ALMOST REVERSE GENERATOR
        */
        System.out.println("--------ALMOST REVERSE--------");
        Generator<Integer> monGen2 = new GeneratorFactory<Integer>().createGenerator("almost reverse");
        System.out.println("Liste de base : " +maListe);
        monGen2.disorderAlgorithm(maListe);
        System.out.println("Liste apres almost reverse : " + maListe);

        /*
         * TEST CREATEGENERATOR
         */
        System.out.println("--------CREATEGENERATOR--------");
        Generator<Integer> monGen3 = new GeneratorFactory<Integer>().createGenerator();
        System.out.println(monGen3.getClass());

        /*
         * TEST ALMOST SAME GENERATOR
         */
        System.out.println("--------ALMOST SAME--------");
        Generator<Integer> monGen4 = new GeneratorFactory<Integer>().createGenerator("almost same");
        System.out.println("Liste de base : " +maListe);
        monGen4.disorderAlgorithm(maListe);
        System.out.println("Liste apres almost reverse : " + maListe);

        /*
         * TEST RANDOM GENERATOR
         */
        System.out.println("--------RANDOM--------");
        Generator<Integer> monGen5 = new GeneratorFactory<Integer>().createGenerator("random");
        System.out.println("Liste de base : " +maListe);
        monGen5.disorderAlgorithm(maListe);
        System.out.println("Liste apres random : " + maListe);

        /*
         * TEST CAESAR GENERATOR
         */
        System.out.println("--------CAESAR--------");
        Generator<Integer> monGen6 = new GeneratorFactory<Integer>().createGenerator("caesar");
        System.out.println("Liste de base : " +maListe);
        monGen6.disorderAlgorithm(maListe);
        System.out.println("Liste apres caesar : " + maListe);
    } 
}
