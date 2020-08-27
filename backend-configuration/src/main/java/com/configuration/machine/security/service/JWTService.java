package com.configuration.machine.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class JWTService {

    private String SECRET_KEY = "7ihYamlmZJa6yVHtoiIfrpYBmYInoH-OO258TtDUKKXVd5MusP2Y7lNPqx3cZpute_6Ry8E2bW0E53OpKWmz2nL3-Tp3ffBE-l5NZ5kRLjj2M7XhlSui_eeu_NBwlqkaPz0NG2zCwP5pKsfwmnNcl9nX29aNuKefTBhopcgQvjU";
    private String USERNAME = "username";
    private String EXPIRE = "exp";

    public String extractUsername(String token){
        return extractAllClaims(token).get(USERNAME, String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails){
        log.info("token generation start");
        Map<String, Object> claims = new HashMap<>();
        String token = createToken(claims, userDetails.getUsername());
        log.trace("token generation end, generated token: ", token);
        return token;
    }

    private String createToken(Map<String, Object> claims, String username) {
        log.trace("start token creation for the username: " + username);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .claim(USERNAME, username)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
    
    public Boolean validateToken(String token, UserDetails userDetails){
        log.trace("start token validation, token: ", token);
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).get(EXPIRE, Date.class);
    }
}
