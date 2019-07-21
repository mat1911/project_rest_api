package menu;

import exceptions.AppException;
import generators.CountriesGenerator;
import generators.NumbersCuriosityGenerator;
import generators.TranslationsGenerator;
import model.countriesapi.Country;
import model.systranapi.json.InputWords;
import service.FileDataService;
import service.UserDataService;
import service.api.CountryQuizService;
import service.api.WordsTranslationService;

import java.util.List;
import java.util.Map;

public class MenuService {

    private String FILE_WITH_WORDS_1 = "resources/words/firstPartition.json";
    private String FILE_WITH_WORDS_2 = "resources/words/secondPartition.json";
    private String FILE_WITH_WORDS_3 = "resources/words/thirdPartition.json";
    private String FILE_WITH_WORDS_4 = "resources/words/fourthPartition.json";
    private String FILE_WITH_WORDS_5 = "resources/words/fifthPartition.json";
    private String FILE_WITH_LEARNED_WORDS = "resources/words/learnedWords.json";
    private String FILE_WITH_TRANSLATIONS = "resources/translatedWords.json";
    private String FILE_WITH_COUNTRY_CODES = "resources/countries/countryCodes.json";
    private int NUMBER_OF_WORDS = 4;
    private int NUMBER_OF_COUNTRIES = 3;
    private UserDataService userDataService = new UserDataService();

    public void mainMenu(){

        while (true) {
            try {

                showMenu();
                int option = userDataService.getInt("Enter number of selected option: ");

                switch(option){

                    case 1 -> option1();

                    case 2 -> option2();

                    case 3 -> option3();

                    case 4 ->{
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

    public void subMenu(){

        FileDataService fileDataService = new FileDataService();

        fileDataService.createJsonFiles(FILE_WITH_WORDS_1, FILE_WITH_WORDS_2, FILE_WITH_WORDS_3, FILE_WITH_WORDS_4,
                FILE_WITH_WORDS_5,FILE_WITH_TRANSLATIONS, FILE_WITH_LEARNED_WORDS);

        while (true) {
            try {

                showSubmenu();
                int option = userDataService.getInt("Enter number of selected option: ");

                switch(option){

                    case 1 -> suboption1(FILE_WITH_WORDS_2, FILE_WITH_WORDS_1, FILE_WITH_WORDS_1);

                    case 2 -> suboption1(FILE_WITH_WORDS_3, FILE_WITH_WORDS_1, FILE_WITH_WORDS_2);

                    case 3 -> suboption1(FILE_WITH_WORDS_4, FILE_WITH_WORDS_1, FILE_WITH_WORDS_3);

                    case 4 -> suboption1(FILE_WITH_WORDS_5, FILE_WITH_WORDS_1, FILE_WITH_WORDS_4);

                    case 5 -> suboption1(FILE_WITH_LEARNED_WORDS, FILE_WITH_WORDS_1, FILE_WITH_WORDS_5);


                    case 6 ->{
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
        System.out.println("2 - Country quiz");
        System.out.println("3 - Get curiosity about today date");
        System.out.println("4 - End of app");
    }

    private void showSubmenu() {

        System.out.println("=================SUBMENU=================");
        System.out.println("1 - Learn from first partition");
        System.out.println("2 - Learn from second partition");
        System.out.println("3 - Learn from third partition");
        System.out.println("4 - Learn from fourth partition");
        System.out.println("5 - Learn from fifth partition");
        System.out.println("6 - Back to main menu");
    }

    private void option1(){

        subMenu();

    }

    private void option2(){

        CountriesGenerator countriesGenerator = new CountriesGenerator();
        List<Country> countries = countriesGenerator.generateCountries(FILE_WITH_COUNTRY_CODES, NUMBER_OF_COUNTRIES);
        CountryQuizService countryQuizService = new CountryQuizService(countries);

        countryQuizService.takeUserAnswers();
        countryQuizService.showCorrectAnswers();
        countryQuizService.showWrongAnswers();
    }

    private void option3(){

        NumbersCuriosityGenerator numbersCuriosityGenerator = new NumbersCuriosityGenerator();
        String curiosity = numbersCuriosityGenerator.generateCuriosityAboutNumbers();

        System.out.println(curiosity);
    }

    private void suboption1(String fileForCorrectAnswer, String fileForWrongAnswer, String currentFileName){

        TranslationsGenerator translationsGenerator = new TranslationsGenerator();
        Map<String, String > words = translationsGenerator.generateEnglishWords(currentFileName, NUMBER_OF_WORDS);
        WordsTranslationService wordsTranslationService = new WordsTranslationService(words);

        wordsTranslationService.takeUserAnswers();
        wordsTranslationService.showWrongAnswers();
        wordsTranslationService.showCorrectAnswers();

        List<String> allWordsFromFile = translationsGenerator.getAllWordsFromFile();
        allWordsFromFile.removeAll(wordsTranslationService.getCorrectAnswers().keySet());
        allWordsFromFile.removeAll(wordsTranslationService.getWrongAnswers().keySet());

        InputWords currentFileData = new InputWords(allWordsFromFile);
        InputWords wrongAnswers = new InputWords(allWordsFromFile);
        InputWords correctAnswers = new InputWords(wordsTranslationService.getCorrectAnswers().keySet());

        FileDataService fileDataService = new FileDataService();

        fileDataService.saveEnglishWordsToJsonFile(currentFileName, currentFileData, false);
        fileDataService.saveEnglishWordsToJsonFile(fileForWrongAnswer, wrongAnswers, true);
        fileDataService.saveEnglishWordsToJsonFile(fileForCorrectAnswer, correctAnswers, true);

    }
}
