package com.finartz.investtrack.controller;

import com.finartz.investtrack.model.Transaction;
import com.finartz.investtrack.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getUserTransactions(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        List<Transaction> transactions = transactionService.getUserTransactions(uid);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{stockSymbol}")
    public ResponseEntity<List<Transaction>> getUserTransactionsByStock(
            HttpServletRequest request,
            @PathVariable String stockSymbol
    ) {
        Integer uid = (Integer) request.getAttribute("uid");
        List<Transaction> transactions = transactionService.getUserTransactionsByStock(uid, stockSymbol);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/{stockSymbol}/buy")
    public ResponseEntity<Transaction> buyStock(
            HttpServletRequest request,
            @PathVariable String stockSymbol,
            @RequestParam BigDecimal quantity) {
        Integer uid = (Integer) request.getAttribute("uid");
        Transaction transaction = transactionService.buyStock(uid, stockSymbol, quantity);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/{stockSymbol}/sell")
    public ResponseEntity<Transaction> sellStock(
            HttpServletRequest request,
            @PathVariable String stockSymbol,
            @RequestParam BigDecimal quantity) {
        Integer uid = (Integer) request.getAttribute("uid");
        Transaction transaction = transactionService.sellStock(uid, stockSymbol, quantity);
        return ResponseEntity.ok(transaction);
    }

}