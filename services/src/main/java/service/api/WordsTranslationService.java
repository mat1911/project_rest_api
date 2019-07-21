package service.api;

import service.UserDataService;

import java.util.HashMap;
import java.util.Map;

public class WordsTranslationService{

    private UserDataService userDataService;
    private Map<String, String> correctAnswers;
    private Map<String, String> wrongAnswers;
    private Map<String, String> words;

    public WordsTranslationService(Map<String, String> words) {
        this.words = words;
        this.correctAnswers = new HashMap<>();
        this.wrongAnswers = new HashMap<>();
        this.userDataService = new UserDataService();
    }

    public void takeUserAnswers(){
        System.out.println("Translate words from polish language to english");
        words.forEach(this::takeAndCheckAnswer);
    }

    public void showWrongAnswers(){

        showAnswers(wrongAnswers, "==============================WRONG ANSWERS===============================");
    }

    public void showCorrectAnswers(){

        showAnswers(correctAnswers, "==============================CORRECT ANSWERS=============================");
    }

    public Map<String, String> getWrongAnswers() {
        return wrongAnswers;
    }

    public Map<String, String> getCorrectAnswers() {
        return correctAnswers;
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
        System.out.println("First letter: " + word.charAt(0));
        System.out.println("Number of letters: " + word.length());
    }

    private void showAnswers(Map<String, String> answers, String s) {
        if(answers.isEmpty()){
            return;
        }

        System.out.println("==========================================================================");
        System.out.println(s);
        System.out.println("==========================================================================");
        answers.forEach((en, pl) -> System.out.println("-" + en + " " + pl));
    }

}
