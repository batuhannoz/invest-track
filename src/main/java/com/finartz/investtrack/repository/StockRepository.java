package com.finartz.investtrack.repository;

import com.finartz.investtrack.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findBySymbol(String symbol);
}