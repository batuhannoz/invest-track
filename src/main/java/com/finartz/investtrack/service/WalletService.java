package com.finartz.investtrack.service;

import com.finartz.investtrack.exception.InsufficientFundsException;
import com.finartz.investtrack.exception.WalletNotFoundException;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.model.Wallet;
import com.finartz.investtrack.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import com.finartz.investtrack.model.WalletTransaction;
import com.finartz.investtrack.repository.WalletTransactionRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    public Wallet createWalletForUser(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(BigDecimal.ZERO);
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet deposit(int userId, BigDecimal amount) {
        validatePositiveAmount(amount);
        User user = userService.getUserById(userId);
        Wallet wallet = walletRepository.findByUser(user)
                .orElseGet(() -> createWalletForUser(user));

        BigDecimal newBalance = wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);
        wallet = walletRepository.save(wallet);

        createWalletTransaction(wallet, amount, newBalance, 
            WalletTransaction.TransactionType.DEPOSIT, "Deposit to wallet");

        return wallet;
    }

    @Transactional
    public Wallet withdraw(int userId, BigDecimal amount) {
        validatePositiveAmount(amount);
        User user = userService.getUserById(userId);
        Wallet wallet = walletRepository.findByUser(user)
                .orElseGet(() -> createWalletForUser(user));
        checkSufficientBalance(wallet.getBalance(), amount);

        BigDecimal newBalance = wallet.getBalance().subtract(amount);
        wallet.setBalance(newBalance);
        wallet = walletRepository.save(wallet);

        createWalletTransaction(wallet, amount.negate(), newBalance,
            WalletTransaction.TransactionType.WITHDRAWAL, "Withdrawal from wallet");

        return wallet;
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

    @Transactional
    public void handleStockPurchase(User user, BigDecimal amount, String stockSymbol) {
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        checkSufficientBalance(wallet.getBalance(), amount);
        BigDecimal newBalance = wallet.getBalance().subtract(amount);
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);

        createWalletTransaction(wallet, amount.negate(), newBalance,
            WalletTransaction.TransactionType.STOCK_PURCHASE, 
            "Stock purchase: " + stockSymbol);
    }

    @Transactional
    public void handleStockSale(User user, BigDecimal amount, String stockSymbol) {
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        BigDecimal newBalance = wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);

        createWalletTransaction(wallet, amount, newBalance,
            WalletTransaction.TransactionType.STOCK_SALE, 
            "Stock sale: " + stockSymbol);
    }

    public List<WalletTransaction> getTransactionHistory(int userId) {
        User user = userService.getUserById(userId);
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        return walletTransactionRepository.findByWalletOrderByCreatedAtDesc(wallet);
    }

    private void createWalletTransaction(Wallet wallet, BigDecimal amount, 
            BigDecimal balanceAfterTransaction, WalletTransaction.TransactionType type, 
            String description) {
        WalletTransaction transaction = new WalletTransaction(
            wallet, amount, balanceAfterTransaction, type, description);
        walletTransactionRepository.save(transaction);
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