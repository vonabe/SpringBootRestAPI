package ru.vonabe.filmsonline.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import ru.vonabe.filmsonline.api.entites.api.UserEntity;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    public static final String SIGNING_KEY = "vonabe+mary=Dev";

    public static String generateToken(UserEntity userEntity) {
        Claims claims = Jwts.claims().setSubject(userEntity.getEmail());
        return Jwts.builder().setClaims(claims)
                .setIssuer("https://onlineinteractivecinema.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public static String formatToken(String token){
        if (token != null && token.startsWith("Bearer "))
            return token.replace("Bearer ", "");
        if (token != null && token.startsWith("Token "))
            return token.replace("Token ", "");

        return null;
    }

}
