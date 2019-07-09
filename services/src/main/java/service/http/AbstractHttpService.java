package service.http;

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

public abstract class AbstractHttpService<T> implements HttpService<T>{

    @Override
    public HttpResponse get(String apiPath) {
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

    @Override
    public HttpRequest requestPost(String path, T body) {
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
