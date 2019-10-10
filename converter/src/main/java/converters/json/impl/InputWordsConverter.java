package converters.json.impl;

import converters.json.JsonConverter;
import model.systranapi.json.InputWords;

public class InputWordsConverter extends JsonConverter<InputWords> {
    public InputWordsConverter(String jsonFileName) {
        super(jsonFileName);
    }
}
