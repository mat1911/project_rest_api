package generators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumbersCuriosityGeneratorTest {

    private NumbersCuriosityGenerator numbersCuriosityGenerator = new NumbersCuriosityGenerator();

    @Test
    public void test1(){

        String result = numbersCuriosityGenerator.generateCuriosityAboutNumbers();

        Assertions.assertFalse(result.isEmpty());
    }
}
