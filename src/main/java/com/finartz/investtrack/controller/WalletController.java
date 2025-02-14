package com.finartz.investtrack.controller;

import com.finartz.investtrack.model.Wallet;
import com.finartz.investtrack.service.WalletService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

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
    
}
