package model.systranapi.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslatedWords {

    private Map<String, String> translatedWords = new HashMap<>();
}
