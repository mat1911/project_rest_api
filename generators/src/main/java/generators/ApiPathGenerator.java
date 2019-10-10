package generators;

public interface ApiPathGenerator {

    static String getPathToTranslator(String sourceLanguageCode, String targetLanguageCode, String word){
        StringBuilder translatorPath = new StringBuilder("https://systran-systran-platform-for-language-processing-v1.p.rapidapi.com/translation/text/translate?source=");
        translatorPath.append(sourceLanguageCode + "&target=");
        translatorPath.append(targetLanguageCode + "&input=" + word);

        return translatorPath.toString();
    }

    static String getPathToCountry(String code){

        StringBuilder translatorPath = new StringBuilder("https://restcountries-v1.p.rapidapi.com/alpha/");
        translatorPath.append(code);

        return translatorPath.toString();
    }

    static String getPathToNumbers(int month, int day){

        StringBuilder translatorPath = new StringBuilder("https://numbersapi.p.rapidapi.com/");
        translatorPath.append(month + "/" + day + "/date?fragment=true&json=true");

        return translatorPath.toString();
    }
}
