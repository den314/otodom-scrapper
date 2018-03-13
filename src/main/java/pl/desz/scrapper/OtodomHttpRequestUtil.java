package pl.desz.scrapper;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;

public class OtodomHttpRequestUtil {

    private static HttpClient httpClient = HttpClient.newHttpClient();
    private static final URI uri = URI.create("https://www.otodom.pl/ajax/search/list/");

    public static HttpResponse<String> getResponse(String payload) throws IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder(uri).header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyProcessor.fromString(payload)).build();

        return httpClient.send(build, HttpResponse.BodyHandler.asString());
    }
}
