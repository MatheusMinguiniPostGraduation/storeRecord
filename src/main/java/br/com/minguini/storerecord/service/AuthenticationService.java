package br.com.minguini.storerecord.service;


import br.com.minguini.storerecord.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication){

        User userAuthenticated = (User) authentication.getPrincipal();

        Date currentDay = new Date();
        Date expirationDate = new Date(currentDay.getTime() + Long.parseLong(this.expiration));

        return Jwts.builder().setIssuer("store-record")
                .setSubject(userAuthenticated.getId().toString())
                .setIssuedAt(currentDay)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }
}
