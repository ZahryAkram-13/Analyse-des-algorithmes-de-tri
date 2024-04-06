package tests;

/**
 * Classe a executer pour passer les tests unitaires.
 * 
 * @author BOUTROIS Benjamin
 */
public class TestMain {
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main(
      "tests.model.generators.GeneratorFactoryTest",
      "tests.model.generators.concreteGenerators.ReverseGeneratorTest",
      "tests.model.generators.concreteGenerators.AlmostReverseGeneratorTest",
      "tests.model.generators.concreteGenerators.AlmostSameGeneratorTest",
      "tests.model.generators.concreteGenerators.RandomGeneratorTest"
    );
    System.out.println("All tests passed");
  }
  
}
