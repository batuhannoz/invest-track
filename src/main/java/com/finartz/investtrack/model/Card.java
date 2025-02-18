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
    @JoinColumn(nullable = false, name = "user")
    private User user;

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

    // Add getters and setters
    public int getId() {
        return id;
    }

    public Card setId(int id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Card setUser(User userId) {
        this.user = userId;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Card setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public Card setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public Card setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public Card setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

}
