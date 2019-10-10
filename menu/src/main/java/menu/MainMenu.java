package menu;

import service.UserDataService;

public interface MainMenu {

    int NUMBER_OF_COUNTRIES = 3;
    String FILE_WITH_WORDS_1 = "resources/words/firstPartition.json";
    String FILE_WITH_WORDS_2 = "resources/words/secondPartition.json";
    String FILE_WITH_WORDS_3 = "resources/words/thirdPartition.json";
    String FILE_WITH_WORDS_4 = "resources/words/fourthPartition.json";
    String FILE_WITH_WORDS_5 = "resources/words/fifthPartition.json";
    String FILE_WITH_LEARNED_WORDS = "resources/words/learnedWords.json";
    String FILE_WITH_TRANSLATIONS = "resources/words/translatedWords.json";
    String FILE_WITH_COUNTRY_CODES = "resources/countries/countryCodes.json";
    UserDataService userDataService = new UserDataService();

    void mainMenu();

}

