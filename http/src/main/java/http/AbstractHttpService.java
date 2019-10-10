package http;

import exceptions.AppException;

import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;


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
            throw new AppException("AbstractHttpService - get() - problem with request, apiPath: " + apiPath);
        }

        return response;
    }
}
