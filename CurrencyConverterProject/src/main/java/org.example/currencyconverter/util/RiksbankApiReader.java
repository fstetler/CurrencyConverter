package org.example.currencyconverter.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RiksbankApiReader {

    public Double exchangeRate(String seriesId, String seriesIdToCompareTo) throws TooManyRequestsException {

        String exchangeRate = getApiStringExchangeRate(seriesId, seriesIdToCompareTo);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(exchangeRate)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 429) {
                throw new TooManyRequestsException(response.body());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponse[] apiResponse = objectMapper.readValue(response.body(), ApiResponse[].class);
            return apiResponse[0].getValue();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getApiStringExchangeRate(String seriesId, String seriesIdToCompareTo) {
        LocalDateTime todaysDateTime = LocalDateTime.now();
        LocalDate todaysDate = todaysDateTime.toLocalDate();
        LocalTime fourFifteenTime = LocalTime.of(16, 15);
        LocalTime todaysTime = todaysDateTime.toLocalTime();
        if (todaysTime.isBefore(fourFifteenTime))  {
            return "https://api.riksbank.se/swea/v1/CrossRates/" + seriesId + "/" + seriesIdToCompareTo + "/" + todaysDate.minusDays(1);
        } else {
            return "https://api.riksbank.se/swea/v1/CrossRates/" + seriesId + "/" + seriesIdToCompareTo + "/" + todaysDate;
        }
    }
}
