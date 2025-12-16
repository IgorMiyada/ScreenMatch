package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.excession.MovieNotFoundException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public final class SearchOmdbApi {

    private static final String apiKey = "d4c31266";

    public static String searchTitle(String titleName) {
        String url = "http://www.omdbapi.com/?t="
                + URLEncoder.encode(titleName, StandardCharsets.UTF_8)
                +"&apikey=" + SearchOmdbApi.apiKey;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try{

            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

            if(response.body().toLowerCase().contains("error")){
                throw new MovieNotFoundException("Movie not found");
            }

            return response.body();

        }catch (InterruptedException | IOException e){
            throw new RuntimeException("Error trying consulting OMDB API." + e.getMessage());
        }
    }
}
