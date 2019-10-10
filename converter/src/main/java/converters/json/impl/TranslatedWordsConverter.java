package converters.json.impl;

import converters.json.JsonConverter;
import model.systranapi.json.TranslatedWords;

public class TranslatedWordsConverter extends JsonConverter<TranslatedWords> {
    public TranslatedWordsConverter(String jsonFileName) {
        super(jsonFileName);
    }
}
