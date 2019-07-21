package generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.AppException;
import model.systranapi.Words;
import model.systranapi.json.InputWords;
import model.systranapi.json.TranslatedWords;
import service.FileDataService;
import service.http.AbstractHttpService;
import service.http.HttpSystranService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslationsGenerator {

    private static final String  FILE_WITH_TRANSLATED_WORTDS = "resources/translatedWords.json";
    private List<String> allWordsFromFile;


    public TranslationsGenerator() {
        allWordsFromFile = new ArrayList<>();
    }

    public Map<String, String> generateEnglishWords(String fileName, int numberOfWords) {

        List<String> drawWords = getWordsFromFile(fileName, numberOfWords);
        System.out.println("Loading and translating words. Please wait.");

        return getTranslatedWord(drawWords);

    }

    public List<String> getAllWordsFromFile() {
        return allWordsFromFile;
    }

    private List<String> getWordsFromFile(String fileName, int numberOfWords) {

        FileDataService fileDataService = new FileDataService();
        InputWords inputWords = fileDataService.getEnglishWordsFromJsonFile(fileName);
        ElementsGenerator<String> generator = new ElementsGenerator<>();

        allWordsFromFile = inputWords.getWords();

        return generator.generateSubList(inputWords.getWords(), numberOfWords);

    }

    private String translateWords(String word){

        String apiPath = ApiPathGenerator.getPathToTranslator("en", "pl", word);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        AbstractHttpService httpService = new HttpSystranService();

        Words translation = gson.fromJson( httpService.get(apiPath).body().toString(), Words.class);

        if(translation.getOutputs().get(0) == null || translation.getOutputs().get(0).getOutput() == null){
            throw new AppException("Problem with connecting with Systran API");
        }

        return translation.getOutputs().get(0).getOutput();

    }

    private Map<String, String> getTranslatedWord(List<String> wordsToTranslate){

        FileDataService fileDataService = new FileDataService();
        TranslatedWords translatedWords = fileDataService.getTranslatedWordsFromJsonFile(FILE_WITH_TRANSLATED_WORTDS);
        Map<String, String> translatedWordsAsMap = translatedWords.getTranslatedWords();
        Map<String, String> translations = new HashMap<>();

        wordsToTranslate.forEach(word -> {

            if (translatedWordsAsMap.containsKey(word)) {
                translations.put(word, translatedWordsAsMap.get(word));
            } else {
                translations.put(word, translateWords(word));
                translatedWordsAsMap.put(word, translations.get(word));
            }

        });

        fileDataService.saveTranslatedWordsToJsonFile(FILE_WITH_TRANSLATED_WORTDS, translatedWords);

        return translations;

    }
}

