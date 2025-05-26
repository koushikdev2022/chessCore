package com.iksen.chessCore.utill;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

import com.iksen.chessCore.model.User;

/**
 * Utility class for JWT token operations like generation, validation, and data extraction.
 */
@Component
public class JwtUtill {
   
    private final String SECRET = "this_is_a_super_secure_secret_key_123!";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

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
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("username", user.getUserName());
        claims.put("email", user.getEmail());
        claims.put("mobile", user.getMobile());
        claims.put("avatar", user.getAvatar());
        claims.put("otp", user.getOtp());
        claims.put("otpExpire", user.getOtpExpire());
        claims.put("oAuth", user.getOAuth());
        claims.put("oauthProvider", user.getOauthProvider());
        claims.put("parentId", user.getParentId());
        claims.put("status", user.getStatus());
        claims.put("isDeleted", user.getIsDeleted());
        claims.put("createdAt", user.getCreatedAt().toString());
        claims.put("updatedAt", user.getUpdatedAt().toString());

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getUserName())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
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
            System.out.println("JWT Validation Error: " + e.getMessage());
            return false;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Extract user ID from token (FIXED VERSION)
     */
    public Long extractUserId(String token) {
        try {
            Claims claims = extractAllClaims(token);
            Object idClaim = claims.get("id");
            
            if (idClaim == null) {
                throw new RuntimeException("User ID not found in token");
            }
            
            // Handle different types of ID representation
            if (idClaim instanceof Long) {
                return (Long) idClaim;
            } else if (idClaim instanceof Integer) {
                return ((Integer) idClaim).longValue();
            } else if (idClaim instanceof String) {
                return Long.parseLong((String) idClaim);
            } else {
                throw new RuntimeException("Unexpected ID type in token: " + idClaim.getClass());
            }
        } catch (Exception e) {
            System.out.println("Error extracting user ID from token: " + e.getMessage());
            throw new RuntimeException("Failed to extract user ID from token", e);
        }
    }

    /**
     * Extract user email from token
     */
    public String extractUserEmail(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return (String) claims.get("email");
        } catch (Exception e) {
            System.out.println("Error extracting email from token: " + e.getMessage());
            return null;
        }
    }

    /**
     * Extract user first name from token
     */
    public String extractFirstName(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return (String) claims.get("firstName");
        } catch (Exception e) {
            System.out.println("Error extracting first name from token: " + e.getMessage());
            return null;
        }
    }

    /**
     * Extract user last name from token
     */
    public String extractLastName(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return (String) claims.get("lastName");
        } catch (Exception e) {
            System.out.println("Error extracting last name from token: " + e.getMessage());
            return null;
        }
    }

    /**
     * Extract parent ID from token
     */
    public Long extractParentId(String token) {
        try {
            Claims claims = extractAllClaims(token);
            Object parentIdClaim = claims.get("parentId");
            
            if (parentIdClaim == null) {
                return null;
            }
            
            if (parentIdClaim instanceof Long) {
                return (Long) parentIdClaim;
            } else if (parentIdClaim instanceof Integer) {
                return ((Integer) parentIdClaim).longValue();
            } else if (parentIdClaim instanceof String) {
                return Long.parseLong((String) parentIdClaim);
            }
            
            return null;
        } catch (Exception e) {
            System.out.println("Error extracting parent ID from token: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get all user data from token as a map
     */
    public Map<String, Object> getUserData(String token) {
        try {
            Claims claims = extractAllClaims(token);
            Map<String, Object> userData = new HashMap<>();
            
            userData.put("id", claims.get("id"));
            userData.put("firstName", claims.get("firstName"));
            userData.put("lastName", claims.get("lastName"));
            userData.put("username", claims.get("username"));
            userData.put("email", claims.get("email"));
            userData.put("mobile", claims.get("mobile"));
            userData.put("avatar", claims.get("avatar"));
            userData.put("parentId", claims.get("parentId"));
            userData.put("status", claims.get("status"));
            userData.put("isDeleted", claims.get("isDeleted"));
            
            return userData;
        } catch (Exception e) {
            System.out.println("Error extracting user data from token: " + e.getMessage());
            throw new RuntimeException("Failed to extract user data from token", e);
        }
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
     * Get current HTTP request from RequestContextHolder
     */
    private HttpServletRequest getCurrentRequest() {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            return attrs.getRequest();
        } catch (Exception e) {
            throw new RuntimeException("Could not get current HTTP request: " + e.getMessage());
        }
    }

    /**
     * Get current JWT token from the request
     */
    public String getCurrentToken() {
        try {
            HttpServletRequest request = getCurrentRequest();
            return extractToken(request);
        } catch (Exception e) {
            throw new RuntimeException("Could not extract JWT token from current request: " + e.getMessage());
        }
    }

    /**
     * Get current user ID directly from JWT token in the request
     */
    public Long getCurrentUserId() {
        String token = getCurrentToken();
        if (token == null) {
            throw new RuntimeException("JWT token not found in request headers");
        }
        return extractUserId(token);
    }

    /**
     * Get current username directly from JWT token in the request
     */
    public String getCurrentUsername() {
        String token = getCurrentToken();
        if (token == null) {
            throw new RuntimeException("JWT token not found in request headers");
        }
        return extractUsername(token);
    }

    /**
     * Get current email directly from JWT token in the request
     */
    public String getCurrentEmail() {
        String token = getCurrentToken();
        if (token == null) {
            throw new RuntimeException("JWT token not found in request headers");
        }
        return extractUserEmail(token);
    }

    /**
     * Get current user's full name
     */
    public String getCurrentFullName() {
        String token = getCurrentToken();
        if (token == null) {
            throw new RuntimeException("JWT token not found in request headers");
        }
        String firstName = extractFirstName(token);
        String lastName = extractLastName(token);
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }

    /**
     * Get current user's parent ID
     */
    public Long getCurrentParentId() {
        String token = getCurrentToken();
        if (token == null) {
            throw new RuntimeException("JWT token not found in request headers");
        }
        return extractParentId(token);
    }

    /**
     * Get all current user data
     */
    public Map<String, Object> getCurrentUserData() {
        String token = getCurrentToken();
        if (token == null) {
            throw new RuntimeException("JWT token not found in request headers");
        }
        return getUserData(token);
    }

    /**
     * Check if current user is authenticated (has valid JWT token)
     */
    public boolean isAuthenticated() {
        try {
            String token = getCurrentToken();
            return token != null && validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if current user is a parent (parentId == 0)
     */
    public boolean isCurrentUserParent() {
        try {
            Long parentId = getCurrentParentId();
            return parentId != null && parentId == 0L;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if current user is a child (parentId != 0)
     */
    public boolean isCurrentUserChild() {
        try {
            Long parentId = getCurrentParentId();
            return parentId != null && parentId != 0L;
        } catch (Exception e) {
            return false;
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
     * Refresh an existing token by creating a new one with same claims
     */
    public String refreshToken(String token) {
        if (!validateToken(token)) {
            throw new RuntimeException("Cannot refresh invalid token");
        }
        
        String username = extractUsername(token);
        Map<String, Object> userData = getUserData(token);
        
        return Jwts.builder()
                .setClaims(userData)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}