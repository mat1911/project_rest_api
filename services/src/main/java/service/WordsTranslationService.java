package service;

import java.util.HashMap;
import java.util.Map;

public class WordsTranslationService {

    private UserDataService userDataService;
    private Map<String, String> correctAnswers;
    private Map<String, String> wrongAnswers;
    private Map<String, String> words;

    public WordsTranslationService(Map<String, String> words) {
        this.words = words;
        this.correctAnswers = new HashMap<>();
        this.wrongAnswers = new HashMap<>();
    }

    public void takeUserAnswers(){
        userDataService = new UserDataService();

        System.out.println("Translate words from polish language to english");
        words.forEach((enWord, plWord) -> {
            String answear = userDataService.getStringOfLetters(plWord + " -> ", false);
            if(answear.equals(enWord)){
                correctAnswers.put(enWord, plWord);
            }
            else {
                wrongAnswers.put(enWord, plWord);
            }
        });
    }

    public Map<String, String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Map<String, String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Map<String, String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(Map<String, String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

}
