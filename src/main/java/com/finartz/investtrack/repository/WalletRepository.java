package com.finartz.investtrack.repository;

import com.finartz.investtrack.model.User;
import com.finartz.investtrack.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Optional<Wallet> findByUser(User userId);
}
