package com.finartz.investtrack.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User userId;

    @Column(nullable = false, name = "card_number")
    private String cardNumber;

    @Column(nullable = false, name = "card_holder_name")
    private String cardHolderName;

    @Column(nullable = false, name = "expiration_date")
    private String expirationDate;

    @Column(nullable = false, name = "cvv")
    private String cvv;

    @CreationTimestamp
    @Column(nullable = false , name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
