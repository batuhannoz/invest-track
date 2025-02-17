package com.finartz.investtrack.service;

import com.finartz.investtrack.util.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    public List<Currency> getTopCurrenciesExchangeRate() {
        // TODO
        return List.of(
                new Currency("USD", 1),
                new Currency("EUR", 0.95),
                new Currency("TRY", 36.23),
                new Currency("GBP", 0.79),
                new Currency("CAD", 1.42),
                new Currency("CNY", 7.25),
                new Currency("CHF", 0.90)
        );
    }

}
