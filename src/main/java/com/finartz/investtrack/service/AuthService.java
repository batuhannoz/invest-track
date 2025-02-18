package com.finartz.investtrack.service;

import com.finartz.investtrack.controller.request.LoginUserRequest;
import com.finartz.investtrack.controller.request.RegisterUserRequest;
import com.finartz.investtrack.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private WalletService walletService;

    public User signup(RegisterUserRequest request) {
        var user = new User()
                .setName(request.getName())
                .setEmail(request.getEmail())
                .setSurname(request.getSurname())
                .setPassword(passwordEncoder.encode(request.getPassword()));
        user = userService.createUser(user);
        walletService.createWalletForUser(user);
        return user;
    }

    public User authenticate(LoginUserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return userService.getUserByEmail(request.getEmail());
    }

}
