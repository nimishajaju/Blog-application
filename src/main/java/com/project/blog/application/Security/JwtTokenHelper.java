package com.project.blog.application.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper {

    // secret key (keep it private in real projects)
        private String secret = "mySuperSecretKeyForJwtAuthenticationProject2026SecureKey";

        // token validity (5 hours)
        private long jwtTokenValidity = 5 * 60 * 60 * 1000;

        // ==============================
        // 1️⃣ Get username from token
        // ==============================
        public String getUsernameFromToken(String token) {
            return getClaimsFromToken(token).getSubject();
        }

        // ==============================
        // 2️⃣ Get expiration date
        // ==============================
        public Date getExpirationDateFromToken(String token) {
            return getClaimsFromToken(token).getExpiration();
        }

        // ==============================
        // 3️⃣ Get all claims (payload data)
        // ==============================
        private Claims getClaimsFromToken(String token) {

            return Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        // ==============================
        // 4️⃣ Check token expired or not
        // ==============================
        private Boolean isTokenExpired(String token) {
            Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }

        // ==============================
        // 5️⃣ Generate token (LOGIN time)
        // ==============================
        public String generateToken(UserDetails userDetails) {

            Map<String, Object> claims = new HashMap<>();

            return doGenerateToken(claims, userDetails.getUsername());
        }

        private String doGenerateToken(
                Map<String, Object> claims,
                String subject) {

            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)   // email/username
                    .setIssuedAt(new Date())
                    .setExpiration(
                            new Date(System.currentTimeMillis() + jwtTokenValidity)
                    )
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                    .compact();
        }

        // ==============================
        // 6️⃣ Validate token
        // ==============================
        public Boolean validateToken(String token, UserDetails userDetails) {

            String username = getUsernameFromToken(token);

            return (username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
        }
    }
