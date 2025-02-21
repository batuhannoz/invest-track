package com.finartz.investtrack.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "wallet_transaction")
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "wallet", nullable = false)
    private Wallet wallet;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal balanceAfterTransaction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, STOCK_PURCHASE, STOCK_SALE
    }

    public WalletTransaction() {}

    public WalletTransaction(
            Wallet wallet,
            BigDecimal amount,
            BigDecimal balanceAfterTransaction,
            TransactionType type,
            String description
    ) {
        this.wallet = wallet;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.type = type;
        this.description = description;
    }

    public BigDecimal getAmount() { return amount; }

    public TransactionType getType() { return type; }

    public Date getCreatedAt() { return createdAt; }

    public String getDescription() { return description; }

    public BigDecimal getBalanceAfterTransaction() { return balanceAfterTransaction; }

}
