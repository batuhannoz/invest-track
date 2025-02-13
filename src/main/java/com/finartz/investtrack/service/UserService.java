package com.finartz.investtrack.service;

import com.finartz.investtrack.model.User;
import com.finartz.investtrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
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

}
