package generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import model.systranapi.Example;
import model.systranapi.Output;
import service.FileDataService;
import service.HTTPService;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslationsGenerator {

    private Map<String, String> translatedWords;

    public Map<String, String> generateEnglishWords(String fileName, int numberOfWords) {
        List<String> englishWords = takeWordsFromFile(fileName, numberOfWords);
        translatedWords = new HashMap<>(numberOfWords);

        System.out.println("Loading and translating words. Please wait.");
        englishWords.forEach(word -> translatedWords.put(word, getTranslatedWord(word)));
        return translatedWords;
    }

    public Map<String, String> getTranslatedWords() {
        return translatedWords;
    }

    public void setTranslatedWords(Map<String, String> translatedWords) {
        this.translatedWords = translatedWords;
    }

    private String getTranslatedWord(String word){
        String apiPath = ApiPathGenerator.getPathToTranslator("en", "pl", word);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Example translation = gson.fromJson( HTTPService.get(apiPath).body().toString(), Example.class);
        return translation.getOutputs().get(0).getOutput();
    }

    private Output getTranslation(HttpResponse response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Output translation = gson.fromJson((JsonElement) response.body(), Output.class);

        System.out.println(translation);

        return null;
    }

    private List<String> takeWordsFromFile(String fileName, int numberOfWords) {
        FileDataService fileDataService = new FileDataService();
        return fileDataService.getDataFromFile(fileName, numberOfWords);
    }
}

