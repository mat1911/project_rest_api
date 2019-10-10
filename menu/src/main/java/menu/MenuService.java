package menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.AppException;
import generators.CountriesGenerator;
import generators.NumbersCuriosityGenerator;
import generators.TranslationsGenerator;
import model.countriesapi.Country;
import model.systranapi.json.InputWords;
import org.fusesource.jansi.AnsiConsole;
import service.FileDataService;
import service.api.CountryQuizService;
import service.api.WordsTranslationService;
import service.api.enums.Answers;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MenuService implements MainMenu{

    public void mainMenu(){

        FileDataService.createDir("resources", "resources/words", "resources/countries");

        FileDataService.createJsonFiles(FILE_WITH_COUNTRY_CODES);

        while (true) {
            try {

                showMenu();
                int option = userDataService.getInt("Enter number of selected option: ");

                switch(option){

                    case 1:
                        option1();
                        break;

                    case 2:
                        option2();
                        break;

                    case 3:
                        option3();
                        break;

                    case 4: {
                        System.out.println("Have a nice day!");
                        return;
                    }
                }

            } catch (AppException e) {
                System.out.println("\n--------------------- EXCEPTION ----------------------");
                System.out.println(e.getMessage());
                userDataService.getAnyKey("Enter any string to continue...");
            }

        }
    }

    public void subMenu(){

        FileDataService.createJsonFiles(FILE_WITH_WORDS_1, FILE_WITH_WORDS_2, FILE_WITH_WORDS_3, FILE_WITH_WORDS_4,
                FILE_WITH_WORDS_5,FILE_WITH_TRANSLATIONS, FILE_WITH_LEARNED_WORDS);

        while (true) {
            try {

                showSubmenu();
                int option = userDataService.getInt("Enter number of selected option: ");

                switch(option){

                    case 1:
                        suboption1(FILE_WITH_WORDS_2, FILE_WITH_WORDS_1, FILE_WITH_WORDS_1);
                        break;

                    case 2:
                        suboption1(FILE_WITH_WORDS_3, FILE_WITH_WORDS_1, FILE_WITH_WORDS_2);
                        break;

                    case 3:
                        suboption1(FILE_WITH_WORDS_4, FILE_WITH_WORDS_1, FILE_WITH_WORDS_3);
                        break;

                    case 4:
                        suboption1(FILE_WITH_WORDS_5, FILE_WITH_WORDS_1, FILE_WITH_WORDS_4);
                        break;

                    case 5:
                        suboption1(FILE_WITH_LEARNED_WORDS, FILE_WITH_WORDS_1, FILE_WITH_WORDS_5);
                        break;

                    case 6:
                        return;
                }

            } catch (AppException e) {
                System.out.println("\n--------------------- EXCEPTION ----------------------");
                System.out.println(e.getMessage());
                userDataService.getAnyKey("Enter any string to continue...");
            }
        }
    }

    private void showMenu() {

        AnsiConsole.out.println("\033[H\033[2J");
        System.out.println("=================MENU=================");
        System.out.println("1 - Translate english words");
        System.out.println("2 - Country quiz");
        System.out.println("3 - Get curiosity about today date");
        System.out.println("4 - End of app");
    }

    private void showSubmenu() {

        AnsiConsole.out.println("\033[H\033[2J");
        System.out.println("=================SUBMENU=================");
        System.out.println("1 - Learn from first partition");
        System.out.println("2 - Learn from second partition");
        System.out.println("3 - Learn from third partition" );
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
        CountryQuizService countryQuizService = new CountryQuizService(countries, userDataService);

        System.out.println("Fill missing fields");
        Map<Answers, Set<Country>> result = countryQuizService.takeUserAnswers();
        System.out.println("\n==================YOUR RESULTS==================");
        System.out.println(toJson(result));
        userDataService.getAnyKey("Enter anything to continue...");
    }

    private void option3(){

        NumbersCuriosityGenerator numbersCuriosityGenerator = new NumbersCuriosityGenerator();
        String curiosity = numbersCuriosityGenerator.generateCuriosityAboutNumbers();

        AnsiConsole.out.println("\033[H\033[2J");
        System.out.println(curiosity);
        userDataService.getAnyKey("Enter anything to continue...");
    }

    private void suboption1(String fileForCorrectAnswer, String fileForWrongAnswer, String currentFileName){

        int numberOfWords = userDataService.getInt("Enter max number of words which you would like to learn: ");

        TranslationsGenerator translationsGenerator = new TranslationsGenerator(FILE_WITH_TRANSLATIONS);
        Map<String, String > words = translationsGenerator.generateEnglishWords(currentFileName, numberOfWords);
        WordsTranslationService wordsTranslationService = new WordsTranslationService(words, userDataService);

        AnsiConsole.out.println("\033[H\033[2J");
        System.out.println("Translate words from polish language to english");
        Map<Answers, Map<String, String>> result = wordsTranslationService.takeUserAnswers();
        System.out.println("\n==================YOUR RESULTS==================");
        System.out.println(toJson(result));
        System.out.println("Page to check the pronunciation: https://youglish.com\n");

        List<String> allWordsFromFile = translationsGenerator.getAllWordsFromFile();
        allWordsFromFile.removeAll(wordsTranslationService.getCorrectAnswers().keySet());
        allWordsFromFile.removeAll(wordsTranslationService.getWrongAnswers().keySet());

        InputWords currentFileData = new InputWords(allWordsFromFile);
        InputWords wrongAnswers = new InputWords(wordsTranslationService.getWrongAnswers().keySet());
        InputWords correctAnswers = new InputWords(wordsTranslationService.getCorrectAnswers().keySet());

        FileDataService.saveEnglishWordsToJsonFile(currentFileName, currentFileData, false);
        FileDataService.saveEnglishWordsToJsonFile(fileForWrongAnswer, wrongAnswers,true);
        FileDataService.saveEnglishWordsToJsonFile(fileForCorrectAnswer, correctAnswers, true);

        userDataService.getAnyKey("Enter anything to continue...");

    }

    private static <T> String toJson(T t) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(t);
    }
}
