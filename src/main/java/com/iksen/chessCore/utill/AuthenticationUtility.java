package com.iksen.chessCore.utill;
import com.iksen.chessCore.config.CustomUserDetails;
import com.iksen.chessCore.model.User;
import com.iksen.chessCore.serviceImpl.user.auth.login.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationUtility {

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    /**
     * Extract user ID from Authentication object
     * @param authentication Spring Security Authentication object
     * @return User ID
     * @throws RuntimeException if user ID cannot be extracted
     */
    public Long getUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null) {
            throw new RuntimeException("Authentication object is null");
        }

        Object principal = authentication.getPrincipal();
        return extractUserIdFromPrincipal(principal);
    }

    /**
     * Get current authenticated user ID from SecurityContext
     * @return User ID of currently authenticated user
     * @throws RuntimeException if no authentication found or user ID cannot be extracted
     */
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("No authentication found in SecurityContext");
        }
        return getUserIdFromAuthentication(authentication);
    }

    /**
     * Get current authenticated user from SecurityContext
     * @return User object of currently authenticated user
     * @throws RuntimeException if no authentication found or user cannot be found
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("No authentication found in SecurityContext");
        }

        Object principal = authentication.getPrincipal();
        
        // If principal is CustomUserDetails, return the user directly
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUser();
        }
        
        // Otherwise, fetch from database
        String username = extractUsernameFromPrincipal(principal);
        return loginServiceImpl.findByUsernameOrEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    /**
     * Get current authenticated username from SecurityContext
     * @return Username of currently authenticated user
     * @throws RuntimeException if no authentication found
     */
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("No authentication found in SecurityContext");
        }

        Object principal = authentication.getPrincipal();
        return extractUsernameFromPrincipal(principal);
    }

    /**
     * Check if current user is authenticated
     * @return true if user is authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && 
               authentication.isAuthenticated() && 
               !"anonymousUser".equals(authentication.getPrincipal());
    }

    /**
     * Extract user ID from principal object
     * @param principal Authentication principal
     * @return User ID
     * @throws RuntimeException if user ID cannot be extracted
     */
    private Long extractUserIdFromPrincipal(Object principal) {
        // Case 1: CustomUserDetails (your custom implementation)
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getId();
        }

        // Case 2: String username - fetch user from database
        if (principal instanceof String) {
            String username = (String) principal;
            Optional<User> userOpt = loginServiceImpl.findByUsernameOrEmail(username);
            return userOpt.map(User::getId)
                    .orElseThrow(() -> new RuntimeException("User not found: " + username));
        }

        // Case 3: Other UserDetails implementations
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Optional<User> userOpt = loginServiceImpl.findByUsernameOrEmail(username);
            return userOpt.map(User::getId)
                    .orElseThrow(() -> new RuntimeException("User not found: " + username));
        }

        throw new RuntimeException("Unexpected authentication principal type: " + 
                                   (principal != null ? principal.getClass().getName() : "null"));
    }

    /**
     * Extract username from principal object
     * @param principal Authentication principal
     * @return Username
     * @throws RuntimeException if username cannot be extracted
     */
    private String extractUsernameFromPrincipal(Object principal) {
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUsername();
        }

        if (principal instanceof String) {
            return (String) principal;
        }

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        throw new RuntimeException("Cannot extract username from principal type: " + 
                                   (principal != null ? principal.getClass().getName() : "null"));
    }
}