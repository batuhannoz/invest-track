package com.finartz.investtrack.repository;

import com.finartz.investtrack.model.Portfolio;
import com.finartz.investtrack.model.Stock;
import com.finartz.investtrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Optional<Portfolio> findByUserAndStock(User user, Stock stock);
    List<Portfolio> findByUser(User user);
}
