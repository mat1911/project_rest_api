package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.AppException;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;


import java.net.http.HttpResponse;
import java.time.Duration;

public class HTTPService {

    public static HttpResponse get(String apiPath){

        HttpResponse<String> response;
        try {
           response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(requestGet(apiPath), HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Problem with request");
        }

        return response;
    }

    private static HttpRequest requestGet(final String path){

        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(path))
                    .version(HttpClient.Version.HTTP_2)
                    .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                    .header("X-RapidAPI-Host", "systran-systran-platform-for-language-processing-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "a3d4c933e1msh826472f9da0e554p1bfe5ajsn2c43227496ea")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AppException("WRONG PATH IN URI");
        }
    }

    private static <T> HttpRequest requestPost(final String path, T body){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(body);

        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(path))
                    .version(HttpClient.Version.HTTP_2)
                    .header("content-type", "application/json;charset=UTF-8")
                    .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AppException("WRONG PATH IN URI");
        }
    }


}
