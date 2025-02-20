package com.finartz.investtrack.controller;

import com.finartz.investtrack.model.Portfolio;
import com.finartz.investtrack.service.PortfolioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping()
    public ResponseEntity<List<Portfolio>> getUserPortfolio(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        return ResponseEntity.ok(portfolioService.getUserPortfolio(uid));
    }

}
