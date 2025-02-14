package com.finartz.investtrack.controller;

import com.finartz.investtrack.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/{symbol}")
    public ResponseEntity<String> getStockInformation(
            @PathVariable String symbol
    ) {
        return new ResponseEntity<>(financeService.getCompanyInfo(symbol), HttpStatus.OK);
    }

}
