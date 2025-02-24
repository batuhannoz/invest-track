package com.finartz.investtrack.controller;

import com.finartz.investtrack.service.FinanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final FinanceService financeService;

    public StockController(FinanceService financeService) {
        this.financeService = financeService;
    }

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

    @GetMapping("/{symbol}/historical/last-week")
    public ResponseEntity<String> getStockHistoricalDataLastWeek(@PathVariable String symbol) {
        return new ResponseEntity<>(financeService.getStockHistoricalDataLastWeek(symbol), HttpStatus.OK);
    }

    @GetMapping("/{symbol}/historical/last-month")
    public ResponseEntity<String> getStockHistoricalDataLastMonth(@PathVariable String symbol) {
        return new ResponseEntity<>(financeService.getStockHistoricalDataLastMonth(symbol), HttpStatus.OK);
    }

    @GetMapping("/{symbol}/historical/last-year")
    public ResponseEntity<String> getStockHistoricalDataLastYear(@PathVariable String symbol) {
        return new ResponseEntity<>(financeService.getStockHistoricalDataLastYear(symbol), HttpStatus.OK);
    }

    @GetMapping("/{symbol}/historical/last-five-years")
    public ResponseEntity<String> getStockHistoricalDataLastFiveYears(@PathVariable String symbol) {
        return new ResponseEntity<>(financeService.getStockHistoricalDataLastFiveYears(symbol), HttpStatus.OK);
    }

}
