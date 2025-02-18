package com.finartz.investtrack.service;

import com.finartz.investtrack.model.Card;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.repository.CardRepository;
import com.finartz.investtrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    public Card createRandomCard(User user) {
        Card card = new Card()
                .setUser(user)
                .setCardNumber(generateRandomCardNumber())
                .setCardHolderName(user.getName() + " " + user.getSurname())
                .setExpirationDate(generateExpirationDate())
                .setCvv(generateCVV());

        return cardRepository.save(card);
    }

    public List<Card> getCardsByUser(User user) {
        return cardRepository.findByUser(user);
    }

    public Card getCardByIdAndUser(Integer cardId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cardRepository.findByIdAndUser(cardId, user)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public void deleteCardByIdAndUser(Integer cardId, Integer userId) {
        Card card = getCardByIdAndUser(cardId, userId);
        cardRepository.delete(card);
    }

    private String generateRandomCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    private String generateExpirationDate() {
        LocalDate future = LocalDate.now().plusYears(4);
        return String.format("%02d/%d", future.getMonthValue(), future.getYear() % 100);
    }

    private String generateCVV() {
        Random random = new Random();
        return String.format("%03d", random.nextInt(1000));
    }

}
