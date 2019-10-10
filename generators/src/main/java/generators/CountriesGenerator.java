package generators;


import com.google.gson.Gson;
import exceptions.AppException;
import http.HttpCountriesService;
import http.HttpService;
import model.countriesapi.Country;
import model.countriesapi.json.CountryCodes;
import service.FileDataService;

import java.util.List;
import java.util.stream.Collectors;

public class CountriesGenerator {

    public List<Country> generateCountries(String fileName,int numberOfCountires){

        return  takeCountryCodesFromFile(fileName, numberOfCountires)
                    .stream()
                    .map(code -> getCountryData(code))
                    .collect(Collectors.toList());

    }

    private List<String> takeCountryCodesFromFile(String fileName, int numberOfCodes) {

        CountryCodes countryCodes = FileDataService.getCountryCodesFromJsonFile(fileName);
        SublistGenerator<String> generator = new SublistGenerator<>();

        if(countryCodes == null || countryCodes.getCodes() == null){
            throw new AppException("CountriesGenerator - takeCountryCodesFromFile() - problem with file with country codes: " + fileName);
        }

        return generator.generateSubList(countryCodes.getCodes(), numberOfCodes);
    }

    private Country getCountryData(String countryCode){

        HttpService httpService = new HttpCountriesService();
        String apiPath = ApiPathGenerator.getPathToCountry(countryCode);
        Gson gson = new Gson();

        Country country = gson.fromJson(httpService.get(apiPath).body().toString(), Country.class);

        if(country == null || country.getName() == null){
            throw new AppException("CountriesGenerator - getCountryData() - problem with connecting with country api");
        }

        return country;
    }
}
