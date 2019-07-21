package model.systranapi.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InputWords {

    private List<String> words;

    public InputWords(Collection<String> words){ this.words = new ArrayList<>(words); }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputWords that = (InputWords) o;

        return words != null ? words.equals(that.words) : that.words == null;
    }

    @Override
    public int hashCode() {
        return words != null ? words.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InputWords{" +
                "words=" + words +
                '}';
    }
}
