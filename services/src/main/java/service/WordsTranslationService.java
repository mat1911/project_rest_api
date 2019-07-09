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
        this.userDataService = new UserDataService();
    }

    public void takeUserAnswers(){
        System.out.println("Translate words from polish language to english");
        words.forEach(this::takeAndCheckAnswer);
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

    public void showWrongAnswers(){

        showAnswers(wrongAnswers, "==============================WRONG ANSWERS===============================");
    }

    public void showCorrectAnswers(){

        showAnswers(correctAnswers, "==============================CORRECT ANSWERS=============================");
    }

    private void takeAndCheckAnswer(String enWord, String plWord){
        String answer;

        showHint(enWord);
        answer = userDataService.getString(plWord + " -> ", false);

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

    private void showAnswers(Map<String, String> correctAnswers, String s) {
        if(correctAnswers.isEmpty()){
            return;
        }

        System.out.println("==========================================================================");
        System.out.println(s);
        System.out.println("==========================================================================");
        correctAnswers.forEach((en, pl) -> System.out.println("-" + en + " " + pl));
    }

}
