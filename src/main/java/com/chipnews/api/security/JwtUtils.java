package com.chipnews.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtils {

    private static final String SECRET = "umaChaveSecretaMuitoSeguraQueDeveSerMaiorQue32Bytes";
    private static final SecretKey SECRET_KEY = new SecretKeySpec(
            Decoders.BASE64.decode(SECRET),
            "HmacSHA256"
    );
    private static final int MINUTES = 60;

    public static String generateToken(String email) {
        var now = Instant.now();
        return Jwts.builder()
                .subject(email)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String extractUsername(String token) throws AccessDeniedException {
        return getTokenBody(token).getSubject();
    }

    public static Boolean validateToken(String token, UserDetails userDetails) throws AccessDeniedException {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private static Claims getTokenBody(String token) throws AccessDeniedException {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException | ExpiredJwtException e) {
            throw new AccessDeniedException("Access denied! " + e.getMessage());
        }
    }

    private static boolean isTokenExpired(String token) throws AccessDeniedException {
        Claims claims = getTokenBody(token);
        return claims.getExpiration().before(new Date());
    }

}
