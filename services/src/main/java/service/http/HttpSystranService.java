package service.http;

import exceptions.AppException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.time.Duration;

public class HttpSystranService<Words> extends AbstractHttpService<Words>{

    @Override
    public HttpRequest requestGet(final String path){

        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(path))
                    .version(HttpClient.Version.HTTP_2)
                    .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                    .header("X-RapidAPI-Host", "systran-systran-platform-for-language-processing-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "<HERE PASTE YOUR KEY FROM RAPIDAPI>")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AppException("WRONG PATH IN URI");
        }
    }


}
