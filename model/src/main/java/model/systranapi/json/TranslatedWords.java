package model.systranapi.json;

import java.util.HashMap;
import java.util.Map;

public class TranslatedWords {

    private Map<String, String> translatedWords;

    public TranslatedWords() {
        translatedWords = new HashMap<>();
    }

    public TranslatedWords(Map<String, String> translatedWords) {
        this.translatedWords = translatedWords;
    }

    public Map<String, String> getTranslatedWords() {
        return translatedWords;
    }

    public void setTranslatedWords(Map<String, String> translatedWords) {
        this.translatedWords = translatedWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslatedWords that = (TranslatedWords) o;

        return translatedWords != null ? translatedWords.equals(that.translatedWords) : that.translatedWords == null;
    }

    @Override
    public int hashCode() {
        return translatedWords != null ? translatedWords.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TranslatedWords{" +
                "translatedWords=" + translatedWords +
                '}';
    }
}
