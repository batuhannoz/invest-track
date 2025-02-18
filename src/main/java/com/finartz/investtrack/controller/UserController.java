package com.finartz.investtrack.controller;

import com.finartz.investtrack.model.User;
import com.finartz.investtrack.service.UserService;
import com.finartz.investtrack.model.Card;
import com.finartz.investtrack.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(
            HttpServletRequest request,
            @RequestBody User updatedUser
    ) {
        Integer uid = (Integer) request.getAttribute("uid");
        User updated = userService.updateUser(uid, updatedUser);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        return ResponseEntity.ok(userService.getUserById(uid));
    }

    @GetMapping("/card")
    public ResponseEntity<List<Card>> getUserCards(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        User user = userService.getUserById(uid);
        return ResponseEntity.ok(cardService.getCardsByUser(user));
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<Card> getCard(
            HttpServletRequest request,
            @PathVariable Integer cardId
    ) {
        Integer uid = (Integer) request.getAttribute("uid");
        return ResponseEntity.ok(cardService.getCardByIdAndUser(cardId, uid));
    }

    @PostMapping("/card")
    public ResponseEntity<Card> createCard(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        User user = userService.getUserById(uid);
        return ResponseEntity.ok(cardService.createRandomCard(user));
    }

    @DeleteMapping("/card/{cardId}")
    public ResponseEntity<Void> deleteCard(
            HttpServletRequest request,
            @PathVariable Integer cardId
    ) {
        Integer uid = (Integer) request.getAttribute("uid");
        cardService.deleteCardByIdAndUser(cardId, uid);
        return ResponseEntity.ok().build();
    }

}
