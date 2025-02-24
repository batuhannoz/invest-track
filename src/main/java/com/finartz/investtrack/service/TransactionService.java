package com.finartz.investtrack.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finartz.investtrack.exception.*;
import com.finartz.investtrack.model.*;
import com.finartz.investtrack.repository.PortfolioRepository;
import com.finartz.investtrack.repository.StockRepository;
import com.finartz.investtrack.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

@Service
public class TransactionService {

    private final FinanceService financeService;

    private final StockRepository stockRepository;

    private final TransactionRepository transactionRepository;

    private final PortfolioRepository portfolioRepository;

    private final UserService userService;

    private final WalletService walletService;

    public TransactionService(
            FinanceService financeService,
            StockRepository stockRepository,
            TransactionRepository transactionRepository,
            PortfolioRepository portfolioRepository,
            UserService userService,
            WalletService walletService
    ) {
        this.financeService = financeService;
        this.stockRepository = stockRepository;
        this.transactionRepository = transactionRepository;
        this.portfolioRepository = portfolioRepository;
        this.userService = userService;
        this.walletService = walletService;
    }

    public List<Transaction> getUserTransactions(int userId) {
        User user = userService.getUserById(userId);
        return transactionRepository.findByUser(user).orElseThrow(
                () -> new EntityNotFoundException("Transactions not found")
        );
    }

    public List<Transaction> getUserTransactionsByStock(int userId, String stockSymbol) {
        User user = userService.getUserById(userId);
        Stock stock = stockRepository.findBySymbol(stockSymbol)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found"));
        return transactionRepository.findByUserAndStock(user, stock)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
    }

    @Transactional
    public Transaction sellStock(int userId, String stockSymbol, BigDecimal quantity) {
        if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        User user = userService.getUserById(userId);

        String upperSymbol = stockSymbol.toUpperCase(Locale.US);
        Stock stock = stockRepository.findBySymbol(upperSymbol)
                .orElseThrow(() -> new StockNotFoundException("Stock not found: " + upperSymbol));

        BigDecimal currentPrice = null;
        try {
            String response = financeService.getStockPrice(stockSymbol);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            currentPrice = rootNode.get(0).get("price").decimalValue();
        } catch (Exception e) {
            throw new StockPriceRetrievalException("Price could not be received: " + upperSymbol);
        }

        BigDecimal totalEarnings = currentPrice.multiply(quantity);

        Portfolio portfolio = portfolioRepository.findByUserAndStock(user, stock)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found for stock: " + upperSymbol));

        if (portfolio.getQuantity().compareTo(quantity) < 0) {
            throw new IllegalArgumentException("Insufficient stock quantity");
        }

        BigDecimal newQuantity = portfolio.getQuantity().subtract(quantity);
        portfolio.setQuantity(newQuantity);
        portfolioRepository.save(portfolio);

        walletService.handleStockSale(user, totalEarnings, upperSymbol);

        Transaction transaction = new Transaction(user, stock, Transaction.TransactionType.SELL, quantity, currentPrice);
        transactionRepository.save(transaction);

        return transaction;
    }

    @Transactional
    public Transaction buyStock(int userId, String stockSymbol, BigDecimal quantity) {
        if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        User user = userService.getUserById(userId);

        String upperSymbol = stockSymbol.toUpperCase(Locale.US);
        Stock stock = stockRepository.findBySymbol(stockSymbol.toUpperCase(Locale.US))
                .orElseGet(() -> financeService.createStockFromAPI(stockSymbol));

        BigDecimal currentPrice = null;
        try {
            String response = financeService.getStockPrice(stockSymbol);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            currentPrice =  rootNode.get(0).get("price").decimalValue();
        } catch (Exception e) {
            throw new StockPriceRetrievalException("Price could not be received: " + upperSymbol);
        }

        BigDecimal totalCost = currentPrice.multiply(quantity);

        walletService.handleStockPurchase(user, totalCost, upperSymbol);

        Transaction transaction = new Transaction(user, stock, Transaction.TransactionType.BUY, quantity, currentPrice);
        transactionRepository.save(transaction);

        Portfolio portfolio = portfolioRepository.findByUserAndStock(user, stock)
                .orElse(new Portfolio(user, stock, BigDecimal.ZERO, BigDecimal.ZERO));

        BigDecimal newQuantity = portfolio.getQuantity().add(quantity);
        BigDecimal newAveragePrice = (portfolio.getQuantity().multiply(portfolio.getAveragePrice())
                .add(totalCost)).divide(newQuantity, RoundingMode.HALF_UP);

        portfolio.setQuantity(newQuantity);
        portfolio.setAveragePrice(newAveragePrice);
        portfolioRepository.save(portfolio);

        return transaction;
    }

}
