package com.finartz.investtrack.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.finartz.investtrack.controller.response.StockSearchResponse;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FinanceService {

    @Value("${alpha-vantage.api-key}")
    private String apiKey;

    @Value("${alpha-vantage.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String searchStocks(String keyword) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("query")
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("function", "SYMBOL_SEARCH")
                .addQueryParameter("keywords", keyword)
                .build().toString();

        String jsonResponse = restTemplate.getForObject(url, String.class);

        try {
            StockSearchResponse response = objectMapper.readValue(jsonResponse, StockSearchResponse.class);

            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (Exception e) {
            return "JSON error: " + e.getMessage();
        }
    }

    public String companyOverview(String symbol) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("query")
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("function", "OVERVIEW")
                .addQueryParameter("symbol", symbol)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }

    public String symbolDailyTimeSeries(String symbol) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("query")
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("function", "TIME_SERIES_DAILY")
                .addQueryParameter("symbol", symbol)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }

}
