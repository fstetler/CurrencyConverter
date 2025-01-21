package org.example.currencyconverter.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;


public class RiksbankApiReader {

    public Double exchangeRate(String seriesId, String seriesIdToCompareTo) {
        LocalDate todaysDate = LocalDate.now();

        String exchangeRate = "https://api.riksbank.se/swea/v1/CrossRates/" + seriesId + "/" + seriesIdToCompareTo + "/" + todaysDate;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(exchangeRate)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponse[] apiResponse = objectMapper.readValue(response.body(), ApiResponse[].class);
            return apiResponse[0].getValue();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
