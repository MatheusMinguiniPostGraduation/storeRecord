package br.com.minguini.storerecord.service;


import br.com.minguini.storerecord.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AuthenticationService {

    public String generateToken(Authentication authentication){

        /*User userAuthenticated = (User) authentication.getPrincipal();

        Date currentDay = new Date();

        return Jwts.builder().setIssuer("store-record")
                .setSubject(userAuthenticated.getId().toString())
                .setIssuedAt(currentDay);*/

        return null;
    }
}
