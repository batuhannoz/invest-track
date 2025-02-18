package com.finartz.investtrack.repository;

import com.finartz.investtrack.model.Card;
import com.finartz.investtrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByUser(User user);
    Optional<Card> findByIdAndUser(Integer id, User user);
}
