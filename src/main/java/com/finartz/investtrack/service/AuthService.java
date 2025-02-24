package com.finartz.investtrack.service;

import com.finartz.investtrack.model.request.LoginUserRequest;
import com.finartz.investtrack.model.request.RegisterUserRequest;
import com.finartz.investtrack.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final WalletService walletService;

    public AuthService(
            UserService userService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            WalletService walletService
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.walletService = walletService;
    }

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
