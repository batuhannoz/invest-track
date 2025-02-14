package com.finartz.investtrack.service;

import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class FinanceService {

    @Value("${fmp.api-key}")
    private String apiKey;

    @Value("${fmp.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String searchStocks(String keyword) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("api")
                .addPathSegment("v3")
                .addPathSegment("search")
                .addQueryParameter("query", keyword)
                .addQueryParameter("apikey", apiKey)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }


    public String getCompanyInfo(String symbol) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("api")
                .addPathSegment("v3")
                .addPathSegment("profile")
                .addPathSegment(symbol)
                .addQueryParameter("apikey", apiKey)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }

    public String getStockPrice(String symbol) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("api")
                .addPathSegment("v3")
                .addPathSegment("quote")
                .addPathSegment(symbol)
                .addQueryParameter("apikey", apiKey)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }

    public String getStockHistoricalDataLastWeek(String symbol) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("api")
                .addPathSegment("v3")
                .addPathSegment("historical-price-full")
                .addPathSegment(symbol)
                .addQueryParameter("from", LocalDate.now().minusWeeks(1).toString())
                .addQueryParameter("to", LocalDate.now().toString())
                .addQueryParameter("apikey", apiKey)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }

    public String getStockHistoricalDataLastMonth(String symbol) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("api")
                .addPathSegment("v3")
                .addPathSegment("historical-price-full")
                .addPathSegment(symbol)
                .addQueryParameter("from", LocalDate.now().minusMonths(1).toString())
                .addQueryParameter("to", LocalDate.now().toString())
                .addQueryParameter("apikey", apiKey)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }

    public String getStockHistoricalDataLastYear(String symbol) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("api")
                .addPathSegment("v3")
                .addPathSegment("historical-price-full")
                .addPathSegment(symbol)
                .addQueryParameter("from", LocalDate.now().minusYears(1).toString())
                .addQueryParameter("to", LocalDate.now().toString())
                .addQueryParameter("apikey", apiKey)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }

    public String getStockHistoricalDataLastFiveYears(String symbol) {
        String url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .addPathSegment("api")
                .addPathSegment("v3")
                .addPathSegment("historical-price-full")
                .addPathSegment(symbol)
                .addQueryParameter("from", LocalDate.now().minusYears(5).toString())
                .addQueryParameter("to", LocalDate.now().toString())
                .addQueryParameter("apikey", apiKey)
                .build().toString();

        return restTemplate.getForObject(url, String.class);
    }
}