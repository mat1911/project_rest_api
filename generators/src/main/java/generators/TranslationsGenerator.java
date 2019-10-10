package generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.AppException;
import http.HttpService;
import http.HttpSystranService;
import model.systranapi.Words;
import model.systranapi.json.InputWords;
import model.systranapi.json.TranslatedWords;
import service.FileDataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslationsGenerator {

    private String fileWithTranslatedWords;
    private List<String> allWordsFromFile;


    public TranslationsGenerator(String fileWithTranslatedWords) {

        this.fileWithTranslatedWords = fileWithTranslatedWords;
        allWordsFromFile = new ArrayList<>();

    }

    public Map<String, String> generateEnglishWords(String fileName, int numberOfWords) {

        List<String> drawWords = getRandomWordsFromFile(fileName, numberOfWords);
        System.out.println("Loading and translating words. Please wait.");

        return getTranslatedWord(drawWords);

    }

    public List<String> getAllWordsFromFile() {
        return allWordsFromFile;
    }

    private List<String> getRandomWordsFromFile(String fileName, int numberOfWords) {

        InputWords inputWords = FileDataService.getEnglishWordsFromJsonFile(fileName);
        SublistGenerator<String> generator = new SublistGenerator<>();

        allWordsFromFile = inputWords.getWords();

        if(inputWords == null || inputWords.getWords() == null || inputWords.getWords().isEmpty()){
            throw new AppException("TranslationsGenerator - getRandomWordsFromFile() - " + fileName + " file has no words!");
        }

        return generator.generateSubList(inputWords.getWords(), numberOfWords);

    }

    private Map<String, String> getTranslatedWord(List<String> wordsToTranslate){

        TranslatedWords translatedWords = FileDataService.getTranslatedWordsFromJsonFile(fileWithTranslatedWords);
        Map<String, String> translatedWordsAsMap = translatedWords.getTranslatedWords();
        Map<String, String> translations = new HashMap<>();

        for(String word : wordsToTranslate){

            if (translatedWordsAsMap.containsKey(word)) {
                translations.put(word, translatedWordsAsMap.get(word));
            } else {
                translations.put(word, translateWord(word));
                translatedWordsAsMap.put(word, translations.get(word));
            }
        }

        FileDataService.saveTranslatedWordsToJsonFile(fileWithTranslatedWords, translatedWords);

        return translations;

    }

    private String translateWord(String word){

        String apiPath = ApiPathGenerator.getPathToTranslator("en", "pl", word);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpService httpService = new HttpSystranService();

        Words translation = gson.fromJson( httpService.get(apiPath).body().toString(), Words.class);

        if(translation.getOutputs().get(0) == null || translation.getOutputs().get(0).getOutput() == null){
            throw new AppException("TranslationsGenerator - translateWord() - problem with connecting with Systran API");
        }

        return translation.getOutputs().get(0).getOutput();

    }

}

