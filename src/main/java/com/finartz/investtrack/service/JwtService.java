package com.finartz.investtrack.service;

import com.finartz.investtrack.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    @Value("${security.jwt.refresh-expiration}")
    private long refreshExpiration;

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh");
        claims.put("name", ((User) userDetails).getName());
        claims.put("surname", ((User) userDetails).getSurname());
        claims.put("uid", ((User) userDetails).getId());
        return buildToken(claims, userDetails, refreshExpiration);
    }

    public long getRefreshExpirationTime() {
        return refreshExpiration;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        String type = extractClaim(token, claims -> claims.get("type", String.class));
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && "access".equals(type);
    }

    public boolean isRefreshTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        String type = extractClaim(token, claims -> claims.get("type", String.class));
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && "refresh".equals(type);
    }

    // Existing methods with modifications to include 'type' claim for access tokens
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(extraClaims);
        claims.put("type", "access");
        claims.put("name", ((User) userDetails).getName());
        claims.put("surname", ((User) userDetails).getSurname());
        claims.put("uid", ((User) userDetails).getId());
        return buildToken(claims, userDetails, jwtExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    public UserDetails extractUserDetails(String token) {
        Claims claims = extractAllClaims(token);
        String username = claims.getSubject();
        String name = claims.get("name", String.class);
        String surname = claims.get("surname", String.class);
        Integer uid = claims.get("uid", Integer.class);

        return new User()
                .setEmail(username)
                .setName(name)
                .setSurname(surname)
                .setId(uid);
    }

    public Integer extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("uid", Integer.class);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}