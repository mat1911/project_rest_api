package generators;

import converters.json.impl.CountryCodeConverter;
import converters.json.JsonConverter;
import model.countriesapi.Country;
import model.countriesapi.json.CountryCodes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class CountriesGeneratorTest {

    private CountriesGenerator countriesGenerator = new CountriesGenerator();

    @Test
    public void test1(){

        File file = new File("testingFile.json");
        List<String> codes = List.of("pl");
        CountryCodes countryCodes = new CountryCodes(codes);
        JsonConverter jsonConverter = new CountryCodeConverter(file.getName());

        jsonConverter.toJsonFile(countryCodes);

        List<Country> result = countriesGenerator.generateCountries("testingFile.json", 2);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Poland", result.get(0).getName());

        file.delete();
    }
}
