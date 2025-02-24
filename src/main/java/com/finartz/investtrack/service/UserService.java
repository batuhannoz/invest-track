package com.finartz.investtrack.service;

import com.finartz.investtrack.exception.UserNotFoundException;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final CardService cardService;

    public UserService(UserRepository userRepository, CardService cardService) {
        this.userRepository = userRepository;
        this.cardService = cardService;
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        cardService.createRandomCard(savedUser);
        return savedUser;
    }
    
    public User updateUser(Integer uid, User updatedUser) {
        User currentUser = userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return userRepository.save(currentUser
                .setName(updatedUser.getName())
                .setSurname(updatedUser.getSurname())
                .setEmail(updatedUser.getEmail())
        );
    }
    
    public User getUserById(Integer uid) {
        return userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
