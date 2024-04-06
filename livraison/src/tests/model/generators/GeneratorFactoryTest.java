package tests.model.generators;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import model.generators.*;

/**
 * Classe de tests unitaires pour la classe GeneratorFactory.
 * 
 * @author BOUTROIS Benjamin
 */
public class GeneratorFactoryTest {
    
    @Test
    public void testCreateGeneratorNotNull() throws Exception{
        Generator myGen = new GeneratorFactory().createGenerator();
        assertNotNull(myGen);
    }

    @Test
    public void testCreateGeneratorCorrectType() throws Exception{
        Generator myReverseGen = new GeneratorFactory().createGenerator("reverse");
        //Vérifie que la création d'un  générateur de type "reverse" est de type Generator
        assertTrue(myReverseGen instanceof Generator);
        Generator myAlmostReverseGen = new GeneratorFactory().createGenerator("almost reverse");
        //Vérifie que la création d'un  générateur de type "almost reverse" est de type Generator
        assertTrue(myAlmostReverseGen instanceof Generator);

        System.out.println("GeneratorFactoryTest passed");
    }
}
