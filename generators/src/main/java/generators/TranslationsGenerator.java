package generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.AppException;
import model.systranapi.Words;
import service.FileDataService;
import service.http.AbstractHttpService;
import service.http.HttpSystranService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslationsGenerator {

    private Map<String, String> translatedWords;

    public Map<String, String> generateEnglishWords(String fileName, int numberOfWords) {

        List<String> englishWords = takeWordsFromFile(fileName, numberOfWords);
        translatedWords = new HashMap<>(numberOfWords);

        System.out.println("Loading and translating words. Please wait.");
        return getTranslatedWord(englishWords);

    }

    private List<String> takeWordsFromFile(String fileName, int numberOfWords) {

        FileDataService fileDataService = new FileDataService();
        return fileDataService.getDataFromFile(fileName, numberOfWords);

    }

    private String getTranslatedWord(String word){

        String apiPath = ApiPathGenerator.getPathToTranslator("en", "pl", word);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        AbstractHttpService httpService = new HttpSystranService();

        Words translation = gson.fromJson( httpService.get(apiPath).body().toString(), Words.class);

        if(translation.getOutputs().get(0) == null || translation.getOutputs().get(0).getOutput() == null){
            throw new AppException("Problem with connecting with Systran API");
        }

        return translation.getOutputs().get(0).getOutput();

    }

    public Map<String, String> getTranslatedWord(List<String> wordsToTranslate){

        FileDataService fileDataService = new FileDataService();
        List<String> translatedWordsFromFile = fileDataService.getDataFromFile("translatedWords.txt", FileDataService.ALL_LINES);
        Map<String, String> translatedWordsAsMap = new HashMap<>();
        Map<String, String> translations = new HashMap<>();

        translatedWordsFromFile.forEach(words -> {
            String[] wordAndTranslation = words.split(";");
            translatedWordsAsMap.put(wordAndTranslation[0], wordAndTranslation[1]);
        });

        wordsToTranslate.forEach(word -> {
            if (translatedWordsAsMap.containsKey(word)) {
                translations.put(word, translatedWordsAsMap.get(word));
            } else {
                translations.put(word, getTranslatedWord(word));
                translatedWordsFromFile.add(word + ";" + translations.get(word));
            }
        });

        fileDataService.saveDataToFile("translatedWords.txt", translatedWordsFromFile, false);

        return translations;

    }
}

