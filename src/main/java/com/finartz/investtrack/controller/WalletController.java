package com.finartz.investtrack.controller;

import com.finartz.investtrack.model.Wallet;
import com.finartz.investtrack.service.WalletService;
import com.finartz.investtrack.model.WalletTransaction;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Wallet> deposit(HttpServletRequest request, @RequestParam BigDecimal amount) {
        Integer uid = (Integer) request.getAttribute("uid");
        return ResponseEntity.ok(walletService.deposit(uid, amount));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Wallet> withdraw(HttpServletRequest request, @RequestParam BigDecimal amount) {
        Integer uid = (Integer) request.getAttribute("uid");
        return ResponseEntity.ok(walletService.withdraw(uid, amount));
    }

    @GetMapping("")
    public ResponseEntity<Wallet> getWallet(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        return ResponseEntity.ok(walletService.getWalletByUserId(uid));
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<WalletTransaction>> getTransactionHistory(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        return ResponseEntity.ok(walletService.getTransactionHistory(uid));
    }
    
}
