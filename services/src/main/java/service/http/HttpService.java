package service.http;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public interface HttpService <T>{

    HttpRequest requestGet(final String path);
    HttpRequest requestPost(final String path, T body);
    HttpResponse get(String apiPath);

}
