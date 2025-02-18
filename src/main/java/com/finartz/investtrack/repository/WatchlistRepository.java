package com.finartz.investtrack.repository;

import com.finartz.investtrack.model.User;
import com.finartz.investtrack.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
    Optional<List<Watchlist>> findByUser(User user);
}
