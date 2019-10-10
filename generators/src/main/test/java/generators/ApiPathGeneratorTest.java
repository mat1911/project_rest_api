package generators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiPathGeneratorTest {

    @Test
    public void test1(){

        String url = "https://systran-systran-platform-for-language-processing-v1.p.rapidapi.com/translation/text/translate?source=pl&target=en&input=slowo";

        String result = ApiPathGenerator.getPathToTranslator("pl", "en", "slowo");

        Assertions.assertEquals(url, result);
    }

    @Test
    public void test2(){

        String url = "https://restcountries-v1.p.rapidapi.com/alpha/23";

        String result = ApiPathGenerator.getPathToCountry("23");

        Assertions.assertEquals(url, result);
    }

    @Test
    public void test3(){

        String url = "https://numbersapi.p.rapidapi.com/8/30/date?fragment=true&json=true";

        String result = ApiPathGenerator.getPathToNumbers(8, 30);

        Assertions.assertEquals(url, result);
    }
}
