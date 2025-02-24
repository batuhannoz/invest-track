package com.finartz.investtrack.service;

import com.finartz.investtrack.exception.CardNotFoundException;
import com.finartz.investtrack.exception.UserNotFoundException;
import com.finartz.investtrack.model.Card;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.repository.CardRepository;
import com.finartz.investtrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class CardService {

    private final CardRepository cardRepository;

    private final UserRepository userRepository;

    @Value("${card.encryption.key}")
    private String encryptionKey;
    
    private static final String ALGORITHM = "AES";

    private static final int AES_KEY_LENGTH = 16;

    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public Card createRandomCard(User user) {
        String cardNumber = generateRandomCardNumber();
        String cvv = generateCVV();
        String cardHolderName = user.getName() + " " + user.getSurname();
        String expirationDate = generateExpirationDate();
        
        Card card = new Card()
                .setUser(user)
                .setCardNumber(encrypt(cardNumber))
                .setCardHolderName(encrypt(cardHolderName))
                .setExpirationDate(encrypt(expirationDate))
                .setCvv(encrypt(cvv));
        
        cardRepository.save(card);
        return decryptCardData(card);
    }

    public List<Card> getCardsByUser(User user) {
        List<Card> cards = cardRepository.findByUser(user);
        cards.forEach(this::decryptCardData);
        return cards;
    }

    public Card getCardByIdAndUser(Integer cardId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Card card = cardRepository.findByIdAndUser(cardId, user)
                .orElseThrow(() -> new CardNotFoundException("Card not found"));
        return decryptCardData(card);
    }

    public void deleteCardByIdAndUser(Integer cardId, Integer userId) {
        Card card = getCardByIdAndUser(cardId, userId);
        cardRepository.delete(card);
    }

    private Card decryptCardData(Card card) {
        card.setCardNumber(decrypt(card.getCardNumber()));
        card.setCardHolderName(decrypt(card.getCardHolderName()));
        card.setExpirationDate(decrypt(card.getExpirationDate()));
        card.setCvv(decrypt(card.getCvv()));
        return card;
    }

    private byte[] normalizeKey(String key) {
        byte[] originalKey = key.getBytes(StandardCharsets.UTF_8);
        byte[] normalizedKey = new byte[AES_KEY_LENGTH];

        System.arraycopy(originalKey, 0, normalizedKey, 0, 
                Math.min(originalKey.length, AES_KEY_LENGTH));
        
        return normalizedKey;
    }

    private String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(normalizeKey(encryptionKey), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    private String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(normalizeKey(encryptionKey), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
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
