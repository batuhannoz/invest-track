package com.finartz.investtrack.repository;

import com.finartz.investtrack.model.Wallet;
import com.finartz.investtrack.model.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Integer> {
    List<WalletTransaction> findByWalletOrderByCreatedAtDesc(Wallet wallet);
}
