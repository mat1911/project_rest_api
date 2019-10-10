package http;

import exceptions.AppException;
import model.countriesapi.Country;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class HttpCountriesService extends AbstractHttpService<Country>{

    @Override
    public HttpRequest requestGet(String path) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(path))
                    .version(HttpClient.Version.HTTP_2)
                    .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                    .header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                    //TODO fill in below field with your key
                    .header("X-RapidAPI-Key", "<PASTE HERE YOUR API KEY>")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AppException("HttpCountriesService - requestGet() - wrong path in URI: " + path);
        }
    }
}
