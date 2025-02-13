package com.finartz.investtrack.service;

import com.finartz.investtrack.controller.response.StockSearchResponse;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

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

}