package com.iksen.chessCore.utill;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iksen.chessCore.model.User;


/**
 * Utility class for JWT token operations like generation, validation, and data extraction.
 */
@Component
public class JwtUtill {
   
    private final String SECRET = "this_is_a_super_secure_secret_key_123!";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    /**
     * Gets the signing key for JWT token generation and validation
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    /**
     * Generate JWT token from user entity
     */
   public String generateToken(User user) {
    try {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUserName());
        claims.put("email", user.getEmail());

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getUserName())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
            .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
            .compact();
    } catch (Exception e) {
        throw new RuntimeException("Error generating JWT", e);
    }
}

    /**
     * Extract all claims from token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extract a specific claim from token using a resolver function
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract username from token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract expiration date from token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Check if token is expired
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validate token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | 
                UnsupportedJwtException | IllegalArgumentException e) {
            // Log the specific type of exception for better debugging
            System.out.println("JWT Validation Error: " + e.getMessage());
            return false;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Extract user ID from token
     */
    public String extractUserId(String token) {
        try {
            Claims claims = extractAllClaims(token);
            Map<String, Object> userClaim = (Map<String, Object>) claims.get("user");
            
            if (userClaim == null) {
                throw new RuntimeException("User claim not found in token");
            }
            
            Map<String, Object> idObj = (Map<String, Object>) userClaim.get("id");
            if (idObj == null) {
                throw new RuntimeException("ID not found in user claim");
            }
            
            return String.valueOf(idObj.get("timestamp"));
        } catch (Exception e) {
            System.out.println("Error extracting user ID from token: " + e.getMessage());
            throw new RuntimeException("Failed to extract user ID from token", e);
        }
    }

    public Map<String, Object>  getUserData(String token) {
        try {
            Claims claims = extractAllClaims(token);
            Map<String, Object> userClaim = (Map<String, Object>) claims.get("user");
            
            if (userClaim == null) {
                throw new RuntimeException("User claim not found in token");
            }
            return userClaim;
        } catch (Exception e) {
            System.out.println("Error extracting user ID from token: " + e.getMessage());
            throw new RuntimeException("Failed to extract user ID from token", e);
        }
    }
    
    /**
     * Extract user email from token
     */
    public String extractUserEmail(String token) {
        Claims claims = extractAllClaims(token);
        Map<String, Object> userClaim = (Map<String, Object>) claims.get("user");
        return userClaim != null ? (String) userClaim.get("email") : null;
    }
    
    /**
     * Get the complete user object from token
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> extractUser(String token) {
        Claims claims = extractAllClaims(token);
        return (Map<String, Object>) claims.get("user");
    }
    
    /**
     * Extract token from HTTP request's Authorization header
     */
    public String extractToken(HttpServletRequest request) {
        try {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error extracting token from request: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Generate a token with custom claims
     */
    public String generateTokenWithClaims(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    /**
     * Refresh an existing token by creating a new one with same subject
     */
    public String refreshToken(String token) {
        if (!validateToken(token)) {
            throw new RuntimeException("Cannot refresh invalid token");
        }
        
        String username = extractUsername(token);
        Map<String, Object> userMap = extractUser(token);
        
        return Jwts.builder()
                .setSubject(username)
                .claim("user", userMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
