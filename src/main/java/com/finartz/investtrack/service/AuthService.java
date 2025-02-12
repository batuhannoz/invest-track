package com.finartz.investtrack.service;

import com.finartz.investtrack.controller.request.LoginUserRequest;
import com.finartz.investtrack.controller.request.RegisterUserRequest;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User signup(RegisterUserRequest request) {
        var user = new User()
                .setName(request.getName())
                .setEmail(request.getEmail())
                .setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return userRepository.findByEmail(request.getEmail()).orElseThrow();
    }

}
