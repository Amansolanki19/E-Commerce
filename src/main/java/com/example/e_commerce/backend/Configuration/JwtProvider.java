package com.example.e_commerce.backend.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {

    SecretKey key= Keys.hmacShaKeyFor(JwtConstantKey.SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication){
        String jwt= Jwts.builder().setIssuedAt(new Date()).claim("email",authentication.getName())
                .signWith(key).compact();

        return jwt;
    }

    public String getEmail(String jwt){
        jwt=jwt.substring(7);

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return String.valueOf(claims.get("email"));


    }
}
