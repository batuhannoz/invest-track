package com.finartz.investtrack.controller;

import com.finartz.investtrack.service.CurrencyService;
import com.finartz.investtrack.util.Currency;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("")
    public ResponseEntity<List<Currency>> getTopCurrencies(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        return new ResponseEntity<>(currencyService.getTopCurrenciesExchangeRate(), HttpStatus.OK);
    }

}
