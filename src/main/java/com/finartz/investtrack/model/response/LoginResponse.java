package com.finartz.investtrack.model.response;

public class LoginResponse {
    private String token;
    private long expiresIn;
    private String refreshToken;
    private long refreshExpiresIn;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
    public LoginResponse setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
    public long getRefreshExpiresIn() {
        return refreshExpiresIn;
    }
    public LoginResponse setRefreshExpiresIn(long refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
        return this;
    }

}
