package http;

import exceptions.AppException;
import model.systranapi.Words;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.time.Duration;

public class HttpSystranService extends AbstractHttpService<Words>{

    @Override
    public HttpRequest requestGet(final String path){

        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(path))
                    .version(HttpClient.Version.HTTP_2)
                    .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                    .header("X-RapidAPI-Host", "systran-systran-platform-for-language-processing-v1.p.rapidapi.com")
                    //TODO fill in below field with your key
                    .header("X-RapidAPI-Key", "<PASTE HERE YOUR API KEY>")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AppException("HttpSystranService - requestGet() - wrong path in URI: " + path);
        }
    }


}
