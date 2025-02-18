package com.finartz.investtrack.service;

import com.finartz.investtrack.model.User;
import com.finartz.investtrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardService cardService;

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        cardService.createRandomCard(savedUser);
        return savedUser;
    }
    
    public User updateUser(Integer uid, User updatedUser) {
        User currentUser = userRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userRepository.save(currentUser
                .setName(updatedUser.getName())
                .setSurname(updatedUser.getSurname())
                .setEmail(updatedUser.getEmail())
        );
    }
    
    public User getUserById(Integer uid) {
        return userRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
