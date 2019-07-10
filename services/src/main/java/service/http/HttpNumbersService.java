package service.http;

import exceptions.AppException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class HttpNumbersService<Number> extends AbstractHttpService<Number>{

    @Override
    public HttpRequest requestGet(String path) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(path))
                    .version(HttpClient.Version.HTTP_2)
                    .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                    .header("X-RapidAPI-Host", "numbersapi.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "a9cb7a405dmshc6e122920f39038p1e8d07jsn164b58f6afa1")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AppException("WRONG PATH IN URI");
        }
    }

}
