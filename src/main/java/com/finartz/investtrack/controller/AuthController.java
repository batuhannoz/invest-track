package com.finartz.investtrack.controller;

import com.finartz.investtrack.controller.request.RefreshTokenRequest;
import com.finartz.investtrack.controller.response.LoginResponse;
import com.finartz.investtrack.controller.request.LoginUserRequest;
import com.finartz.investtrack.controller.request.RegisterUserRequest;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.repository.UserRepository;
import com.finartz.investtrack.service.AuthService;
import com.finartz.investtrack.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequest registerUserRequest) {
        User registeredUser = authService.signup(registerUserRequest);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserRequest loginUserRequest) {
        User authenticatedUser = authService.authenticate(loginUserRequest);

        String accessToken = jwtService.generateToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse()
                .setToken(accessToken)
                .setExpiresIn(jwtService.getExpirationTime())
                .setRefreshToken(refreshToken)
                .setRefreshExpiresIn(jwtService.getRefreshExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userRepository.findByEmail(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (jwtService.isRefreshTokenValid(refreshToken, userDetails)) {
            String newAccessToken = jwtService.generateToken(userDetails);
            String newRefreshToken = jwtService.generateRefreshToken(userDetails);

            LoginResponse response = new LoginResponse()
                    .setToken(newAccessToken)
                    .setExpiresIn(jwtService.getExpirationTime())
                    .setRefreshToken(newRefreshToken)
                    .setRefreshExpiresIn(jwtService.getRefreshExpirationTime());

            return ResponseEntity.ok(response);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }
    }

}