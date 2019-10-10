package service.api;

import exceptions.AppException;
import service.UserDataService;
import service.api.enums.Answers;

import java.util.HashMap;
import java.util.Map;

public class WordsTranslationService{

    private UserDataService userDataService;
    private Map<String, String> correctAnswers;
    private Map<String, String> wrongAnswers;
    private Map<String, String> words;

    public WordsTranslationService(Map<String, String> words, UserDataService userDataService) {

        initFields(words, userDataService);
    }

    public Map<Answers, Map<String, String>> takeUserAnswers(){

        Map<Answers, Map<String, String>> result = new HashMap<>();

        words.forEach(this::takeAndCheckAnswer);
        result.put(Answers.CORRECT, correctAnswers);
        result.put(Answers.WRONG, wrongAnswers);

        return result;
    }

    public Map<String, String> getWrongAnswers() {
        return wrongAnswers;
    }

    public Map<String, String> getCorrectAnswers() {
        return correctAnswers;
    }

    private void initFields(Map<String, String> words, UserDataService userDataService){

        if(words == null || userDataService == null){
            throw new AppException("WordsTranslationService - initFields() - map with words or userDataService is null!");
        }

        if(words.isEmpty()){
            throw new AppException("WordsTranslationService - initFields() - map with words is empty!");
        }

        this.words = words;
        this.correctAnswers = new HashMap<>();
        this.wrongAnswers = new HashMap<>();
        this.userDataService = userDataService;
    }

    private void takeAndCheckAnswer(String enWord, String plWord){
        String answer;

        showHint(enWord);
        answer = userDataService.getString(plWord + " -> ");

        if(answer.equals(enWord)){
            correctAnswers.put(enWord, plWord);
        }
        else {
            wrongAnswers.put(enWord, plWord);
        }
    }

    private void showHint(String word){
        System.out.println("\nFirst letter: " + word.charAt(0));
        System.out.println("Number of letters: " + word.length());
    }

}
