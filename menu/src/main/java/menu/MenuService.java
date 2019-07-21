package menu;

import exceptions.AppException;
import generators.CountriesGenerator;
import generators.NumbersCuriosityGenerator;
import generators.TranslationsGenerator;
import model.countriesapi.Country;
import model.systranapi.json.TranslatedWords;
import service.FileDataService;
import service.UserDataService;
import service.api.CountryQuizService;
import service.api.WordsTranslationService;

import java.util.List;
import java.util.Map;

public class MenuService {

    private String FILE_WITH_WRONG_ANSWERS = "resources/wrong_answers.json";
    private String FILE_WITH_WORDS = "resources/englishWords.json";
    private String FILE_WITH_TRANSLATIONS = "resources/translatedWords.json";
    private String FILE_WITH_COUNTRY_CODES = "resources/countryCodes.json";
    private int NUMER_OF_WORDS = 5;
    private int NUMER_OF_COUNTRIES = 3;
    private UserDataService userDataService = new UserDataService();

    public void mainMenu(){

        FileDataService fileDataService = new FileDataService();

        fileDataService.createFile(FILE_WITH_TRANSLATIONS);
        fileDataService.createFile(FILE_WITH_WRONG_ANSWERS);

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

    private void showMenu() {

        System.out.println("=================MENU=================");
        System.out.println("1 - Translate english words");
        System.out.println("2 - Country quiz");
        System.out.println("3 - Get curiosity about today date");
        System.out.println("4 - end of app");
    }

    private void option1(){

        TranslationsGenerator translationsGenerator = new TranslationsGenerator();
        Map<String, String > words = translationsGenerator.generateEnglishWords(FILE_WITH_WORDS, NUMER_OF_WORDS);
        WordsTranslationService wordsTranslationService = new WordsTranslationService(words);
        FileDataService fileDataService = new FileDataService();

        wordsTranslationService.takeUserAnswers();
        wordsTranslationService.showWrongAnswers();
        wordsTranslationService.showCorrectAnswers();

        TranslatedWords translatedWords = new TranslatedWords(wordsTranslationService.getWrongAnswers());

        fileDataService.saveTranslatedWordsToJsonFile(FILE_WITH_WRONG_ANSWERS, translatedWords);
    }

    private void option2(){

        CountriesGenerator countriesGenerator = new CountriesGenerator();
        List<Country> countries = countriesGenerator.generateCountries(FILE_WITH_COUNTRY_CODES, NUMER_OF_COUNTRIES);
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

}
