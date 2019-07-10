package generators;

import com.google.gson.Gson;
import exceptions.AppException;
import model.countriesapi.Country;
import service.FileDataService;
import service.http.AbstractHttpService;
import service.http.HttpCountriesService;

import java.util.ArrayList;
import java.util.List;

public class CountriesGenerator {

    List<Country> countires = new ArrayList<>();

    public List<Country> generateCountries(String fileName,int numberOfCountires){

        List<String> countryCodes = takeCountryCodesFromFile(fileName, numberOfCountires);
        countryCodes.forEach(code -> countires.add(getCountryData(code)));

        return countires;
    }


    private List<String> takeCountryCodesFromFile(String fileName, int numberOfCodes) {

        FileDataService fileDataService = new FileDataService();
        return fileDataService.getDataFromFile(fileName, numberOfCodes);
    }

    private Country getCountryData(String countryCode){

        AbstractHttpService httpService = new HttpCountriesService();
        String apiPath = ApiPathGenerator.getPathToCountry(countryCode);
        Gson gson = new Gson();

        Country country = gson.fromJson(httpService.get(apiPath).body().toString(), Country.class);

        if(country == null){
            throw new AppException("Problem with connecting with country api - CountriesGenerator - getCountryData");
        }

        return country;
    }
}