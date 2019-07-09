package menu;

import exceptions.AppException;
import generators.TranslationsGenerator;
import service.FileDataService;
import service.UserDataService;
import service.WordsTranslationService;

import java.util.Map;

public class MenuService {

    private String WRONG_ANSWERS_FILE = "resources/wrong_answers.txt";
    private String FILE_WITH_WORDS = "resources/englishWords.txt";
    private int NUMER_OF_WORDS = 1;
    private UserDataService userDataService = new UserDataService();

    public void mainMenu(){

        FileDataService fileDataService = new FileDataService();
        fileDataService.createFile("translatedWords.txt");

        while (true) {
            try {

                showMenu();
                int option = userDataService.getInt("Enter number of selected option: ");

                switch(option){

                    case 1 -> option1();

                    case 2 ->{
                        System.out.println("Have a nice day!");
                        return;
                    }
                }

            } catch (AppException e) {
                System.out.println("\n--------------------- EXCEPTION ----------------------");
                System.out.println(e.getMessage());
            }
        }
    }

    private void showMenu() {

        System.out.println("=================MENU=================");
        System.out.println("1 - Translate english words");
        System.out.println("2 - end of app");
    }

    private void option1(){
        TranslationsGenerator translationsGenerator = new TranslationsGenerator();
        Map<String, String > words = translationsGenerator.generateEnglishWords(FILE_WITH_WORDS, NUMER_OF_WORDS);
        WordsTranslationService wordsTranslationService = new WordsTranslationService(words);
        FileDataService fileDataService = new FileDataService();

        wordsTranslationService.takeUserAnswers();
        wordsTranslationService.showWrongAnswers();
        wordsTranslationService.showCorrectAnswers();

        fileDataService.saveDataToFile(WRONG_ANSWERS_FILE, wordsTranslationService.getWrongAnswers(), true);
    }

}
