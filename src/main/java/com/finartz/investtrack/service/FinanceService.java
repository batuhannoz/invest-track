package com.finartz.investtrack.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finartz.investtrack.model.Stock;
import com.finartz.investtrack.repository.StockRepository;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class FinanceService {

    @Value("${fmp.api-key}")
    private String apiKey;

    @Value("${fmp.base-url}")
    private String baseUrl;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable(value = "stocksCache", key = "#keyword")
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

    @Cacheable(value = "companyInfoCache", key = "#symbol")
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

    @Cacheable(value = "stockPriceCache", key = "#symbol")
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

    @Cacheable(value = "historicalWeekCache", key = "#symbol")
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

    @Cacheable(value = "historicalMonthCache", key = "#symbol")
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

    @Cacheable(value = "historicalYearCache", key = "#symbol")
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

    @Cacheable(value = "historicalFiveYearsCache", key = "#symbol")
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

    public Stock createStockFromAPI(String symbol) {
        try {
            String companyInfo = getCompanyInfo(symbol);
            JsonNode jsonNode = objectMapper.readTree(companyInfo);

            if (jsonNode.isArray() && !jsonNode.isEmpty()) {
                JsonNode companyData = jsonNode.get(0);

                Stock stock = new Stock();
                stock.setSymbol(companyData.get("symbol").asText());
                stock.setName(companyData.get("companyName").asText());
                stock.setExchange(companyData.get("exchange").asText());
                stock.setImageUrl(companyData.get("image").asText());
                return stockRepository.save(stock);
            }
            throw new RuntimeException("Company information not found");
        } catch (Exception e) {
            throw new RuntimeException("Error creating stock: " + e.getMessage());
        }
    }

}