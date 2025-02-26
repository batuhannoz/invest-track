package com.finartz.investtrack.service;

import com.finartz.investtrack.model.Transaction;
import com.finartz.investtrack.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FraudDetectionService {

    private static final Logger logger = LoggerFactory.getLogger(FraudDetectionService.class);

    private static final BigDecimal SUSPICIOUS_AMOUNT_THRESHOLD = new BigDecimal("10000");

    private static final int MAX_TRANSACTIONS_PER_HOUR = 10;

    private final Map<Long, Map<LocalDateTime, Integer>> userTransactionCounts = new ConcurrentHashMap<>();

    @KafkaListener(topics = KafkaConfig.TRANSACTION_TOPIC)
    public void processTransaction(Transaction transaction) {
        try {
            if (transaction == null) {
                logger.error("Received null transaction");
                return;
            }

            Long transactionId = transaction.getId();
            if (transactionId == null) {
                logger.error("Transaction ID is null");
                return;
            }

            if (isLargeTransaction(transaction)) {
                logger.warn("Suspicious large transaction detected for transaction ID {}: Amount = {}",
                        transactionId,
                        transaction.getPrice().multiply(transaction.getQuantity()));
            }

            if (isHighFrequencyTrading(transaction)) {
                logger.warn("Suspicious high-frequency trading detected for transaction ID {}",
                        transactionId);
            }
        } catch (Exception e) {
            logger.error("Error processing transaction: ", e);
        }
    }

    private boolean isLargeTransaction(Transaction transaction) {
        if (transaction.getPrice() == null || transaction.getQuantity() == null) {
            logger.error("Transaction price or quantity is null");
            return false;
        }

        try {
            BigDecimal totalAmount = transaction.getPrice().multiply(transaction.getQuantity());
            return totalAmount.compareTo(SUSPICIOUS_AMOUNT_THRESHOLD) > 0;
        } catch (Exception e) {
            logger.error("Error calculating transaction amount: ", e);
            return false;
        }
    }

    private boolean isHighFrequencyTrading(Transaction transaction) {
        try {
            Long transactionId = transaction.getId();
            if (transactionId == null) {
                logger.error("Transaction ID is null during high-frequency check");
                return false;
            }

            LocalDateTime now = LocalDateTime.now();

            userTransactionCounts.computeIfAbsent(transactionId, k -> new HashMap<>());
            Map<LocalDateTime, Integer> userCounts = userTransactionCounts.get(transactionId);

            userCounts.entrySet().removeIf(entry ->
                Duration.between(entry.getKey(), now).toHours() >= 1);

            int recentTransactions = userCounts.values().stream().mapToInt(Integer::intValue).sum();
            userCounts.put(now, 1);

            return recentTransactions >= MAX_TRANSACTIONS_PER_HOUR;
        } catch (Exception e) {
            logger.error("Error checking high-frequency trading: ", e);
            return false;
        }
    }

}
