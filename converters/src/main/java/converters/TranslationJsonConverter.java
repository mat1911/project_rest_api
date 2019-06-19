package converters;

import model.systranapi.Output;

public class TranslationJsonConverter extends JsonConverter<Output>{
    public TranslationJsonConverter(String jsonFileName) {
        super(jsonFileName);
    }
}
