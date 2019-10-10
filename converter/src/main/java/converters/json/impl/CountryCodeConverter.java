package converters.json.impl;

import converters.json.JsonConverter;
import model.countriesapi.json.CountryCodes;

public class CountryCodeConverter extends JsonConverter<CountryCodes> {
    public CountryCodeConverter(String jsonFileName) {
        super(jsonFileName);
    }
}
