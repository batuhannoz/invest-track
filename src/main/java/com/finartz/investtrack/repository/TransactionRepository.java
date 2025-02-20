package com.finartz.investtrack.repository;


import com.finartz.investtrack.model.Stock;
import com.finartz.investtrack.model.Transaction;
import com.finartz.investtrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<List<Transaction>> findByUserAndStock(User user, Stock stock);
    Optional<List<Transaction>> findByUser(User user);

}
