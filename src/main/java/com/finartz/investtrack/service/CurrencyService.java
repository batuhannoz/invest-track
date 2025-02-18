package com.finartz.investtrack.service;

import com.finartz.investtrack.controller.response.CurrencyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private static final List<String> POPULAR_CURRENCIES = List.of(
        "EUR", "TRY", "CAD", "GBP", "CNY", "CHF", "JPY", "AUD", "INR", "NZD"
    );

    @Value("${currency-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<CurrencyResponse> getTopCurrenciesExchangeRate() {
        Map<String, Object> response = restTemplate.getForObject(baseUrl, Map.class);
        
        Map<String, Double> rates = (Map<String, Double>) response.get("conversion_rates");
        
        return rates.entrySet().stream()
                .filter(entry -> POPULAR_CURRENCIES.contains(entry.getKey()))
                .map(entry -> new CurrencyResponse(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(CurrencyResponse::getExchangeRate).reversed())
                .collect(Collectors.toList());
    }

}
