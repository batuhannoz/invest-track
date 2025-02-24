package com.finartz.investtrack.controller;

import com.finartz.investtrack.model.response.CurrencyResponse;
import com.finartz.investtrack.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/top")
    public ResponseEntity<List<CurrencyResponse>> getTopCurrencies() {
        return new ResponseEntity<>(currencyService.getTopCurrenciesExchangeRate(), HttpStatus.OK);
    }

}
