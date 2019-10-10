package http;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public interface HttpService <T>{

    HttpRequest requestGet(final String path);
    HttpResponse get(String apiPath);

}
