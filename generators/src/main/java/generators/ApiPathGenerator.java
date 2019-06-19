package generators;

public class ApiPathGenerator {

    private static StringBuilder translatorPath;

    public static String getPathToTranslator(String sourceLanguageCode, String targetLanguageCode, String word){
        translatorPath = new StringBuilder("https://systran-systran-platform-for-language-processing-v1.p.rapidapi.com/translation/text/translate?source=");
        translatorPath.append("" + sourceLanguageCode + "&target=");
        translatorPath.append("" + targetLanguageCode + "&input=");
        translatorPath.append(word );

        return translatorPath.toString();
    }
}
