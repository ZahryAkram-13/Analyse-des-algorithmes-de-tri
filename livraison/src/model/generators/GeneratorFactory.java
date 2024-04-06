package model.generators;
import model.generators.concreteGenerators.*;
import java.util.Random;

/**
 * La classe GeneratorFactory repertorie tous les noms de generateurs de desordre et permet de les instancier facilement.
 * 
 * @author BOUTROIS Benjamin
 */
public class GeneratorFactory<T extends Comparable<T>>{

    private Random random;
    public static final String[] GENERATOR_NAMES = {
        "caesar",
        "almost_same",
        "random",
        "reverse",
        "almost_reverse"
    };

    public GeneratorFactory() {
        this.random = new Random();
    }

    /**
     * Créé un générateur choisi par son nom generatorName.
     * Méthode de surcharge pour pouvoir imposer le générateur à utiliser.
     * 
     * @param generatorName Le nom du générateur à créer.
     * 
     * @return L'instance du nouveau générateur.
     */
    public Generator<T> createGenerator(String generatorName){
        for (int i = 0; i < GENERATOR_NAMES.length; i++){
            if ("caesar".equals(generatorName)){
                return new CaesarGenerator<T>();
            }
            if ("almost_same".equals(generatorName)){
                return new AlmostSameGenerator<T>();
            }
            if ("random".equals(generatorName)){
                return new RandomGenerator<T>();
            }
            if ("reverse".equals(generatorName)){
                return new ReverseGenerator<T>(); 
            }
            if ("almost_reverse".equals(generatorName)){
                return new AlmostReverseGenerator<T>(); 
            }
        }
        System.out.println("Générateur non reconnu, un generateur aleatoire a ete choisi");
        return createGenerator();
    }

    /**
     * Créé un générateur aléatoire parmis ceux répertoriés.

     * @return L'instance du nouveau générateur.
     */
    public Generator<T> createGenerator(){
        int randomIndex = random.nextInt(GENERATOR_NAMES.length); //Choisi un nom aléatoire dans la liste des noms de générateurs.
        String name = GENERATOR_NAMES[randomIndex]; 
        return createGenerator(name);
    }
}