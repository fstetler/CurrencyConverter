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
            DateValueApiResponse[] apiResponse = objectMapper.readValue(response.body(), DateValueApiResponse[].class);
            return apiResponse[0].getValue();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getApiStringExchangeRate(String seriesId, String seriesIdToCompareTo) throws TooManyRequestsException {
        LocalDate today = LocalDate.now();

        String latestDateApi = "https://api.riksbank.se/swea/v1/CalendarDays/" + today;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(latestDateApi)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 429) {
                throw new TooManyRequestsException(response.body());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            LastestDateApiResponse[] apiResponse = objectMapper.readValue(response.body(), LastestDateApiResponse[].class);

            LocalDateTime todaysDateTime = LocalDateTime.now();
            LocalTime fourFifteenTime = LocalTime.of(16, 15);
            LocalTime todaysTime = todaysDateTime.toLocalTime();
            LocalDate todaysDate = LocalDate.parse(apiResponse[0].getCalendarDate());

            if (todaysTime.isBefore(fourFifteenTime))  {
                return "https://api.riksbank.se/swea/v1/CrossRates/" + seriesId + "/" + seriesIdToCompareTo + "/" + todaysDate.minusDays(1);
            } else {
                return "https://api.riksbank.se/swea/v1/CrossRates/" + seriesId + "/" + seriesIdToCompareTo + "/" + todaysDate;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
