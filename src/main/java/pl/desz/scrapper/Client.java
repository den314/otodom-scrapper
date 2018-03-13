package pl.desz.scrapper;

import jdk.incubator.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Client {

    public static final String OFFERS_COUNT = "div.offers-index.pull-left.text-nowrap strong";

    public static void main(String[] args) throws IOException, InterruptedException {

        // create criteris for query
        OtodomSearchQueryBuilder builder = OtodomSearchQueryBuilder.newBuilder()
                                                .setMinFlatArea(50)
                                                .setMaxFlatArea(80)
                                                .setRooms(2)
                                                .setBalcony(OtodomSearchQueryBuilder.Balcony.YES);
        // build search payload
        String payload = builder.build();
        System.out.println("Payload to be send:\n" + payload);

        // make a HTTP call to otodom.pl with Java 9 Incubated Http Client
        HttpResponse<String> stringHttpResponse = OtodomHttpRequestUtil.getResponse(payload);
        String body = stringHttpResponse.body();

        // parse and show scrapped value
        Document doc = Jsoup.parse(body);
        System.out.printf("Response:%nFound %s offers based on given criteria.%n", doc.select(OFFERS_COUNT).text());

    }


}
