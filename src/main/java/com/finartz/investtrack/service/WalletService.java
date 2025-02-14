package com.finartz.investtrack.service;

import com.finartz.investtrack.exception.InsufficientFundsException;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.model.Wallet;
import com.finartz.investtrack.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    public Wallet createWalletForUser(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(BigDecimal.ZERO);
        return walletRepository.save(wallet);
    }

    public Wallet deposit(int userId, BigDecimal amount) {
        validatePositiveAmount(amount);
        User user = userService.getUserById(userId);
        Wallet wallet = walletRepository.findByUser(user)
                .orElseGet(() -> createWalletForUser(user));

        wallet.setBalance(wallet.getBalance().add(amount));
        return walletRepository.save(wallet);
    }

    public Wallet withdraw(int userId, BigDecimal amount) {
        validatePositiveAmount(amount);
        User user = userService.getUserById(userId);
        Wallet wallet = walletRepository.findByUser(user)
                .orElseGet(() -> createWalletForUser(user));
        checkSufficientBalance(wallet.getBalance(), amount);
        wallet.setBalance(wallet.getBalance().subtract(amount));
        return walletRepository.save(wallet);
    }

    public Wallet getWalletByUserId(int userId) {
        User user = userService.getUserById(userId);
        return walletRepository.findByUser(user)
                .orElseGet(() -> createWalletForUser(user));
    }

    public boolean hasWallet(int userId) {
        User user = userService.getUserById(userId);
        return walletRepository.findByUser(user).isPresent();
    }

    public BigDecimal getBalance(int userId) {
        return getWalletByUserId(userId).getBalance();
    }

    @Transactional
    public void transfer(int fromUserId, int toUserId, BigDecimal amount) {
        validatePositiveAmount(amount);
        Wallet fromWallet = getWalletByUserId(fromUserId);
        Wallet toWallet = getWalletByUserId(toUserId);
        checkSufficientBalance(fromWallet.getBalance(), amount);
        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        toWallet.setBalance(toWallet.getBalance().add(amount));
        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);
    }

    private void validatePositiveAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    private void checkSufficientBalance(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance");
        }
    }

}