package generators;

import converters.json.impl.InputWordsConverter;
import converters.json.JsonConverter;
import model.systranapi.json.InputWords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

public class TranslationsGeneratorTest {

    private TranslationsGenerator translationsGenerator;

    @Test
    public void test1(){

        File file = new File("testingFile.json");
        List<String> codes = List.of("cat", "dog", "fish");
        InputWords inputWords = new InputWords(codes);

        JsonConverter jsonConverter = new InputWordsConverter(file.getName());
        jsonConverter.toJsonFile(inputWords);

        translationsGenerator = new TranslationsGenerator(file.getName());

        Map<String, String> result = translationsGenerator.generateEnglishWords(file.getName(), 3);

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(result.get("cat"), "kot");

        file.delete();
    }
}
