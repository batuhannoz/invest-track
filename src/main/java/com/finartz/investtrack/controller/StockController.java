package com.finartz.investtrack.controller;

import com.finartz.investtrack.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/search")
    public ResponseEntity<String> searchStock(@RequestParam String keyword) {
        return new ResponseEntity<>(financeService.searchStocks(keyword), HttpStatus.OK);
    }

    @GetMapping("/{symbol}/company")
    public ResponseEntity<String> getCompanyInfo(@PathVariable String symbol) {
        return new ResponseEntity<>(financeService.getCompanyInfo(symbol), HttpStatus.OK);
    }

    @GetMapping("/{symbol}/price")
    public ResponseEntity<String> getStockInformation(@PathVariable String symbol) {
        return new ResponseEntity<>(financeService.getStockPrice(symbol), HttpStatus.OK);
    }

}
