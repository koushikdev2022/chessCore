package com.iksen.chessCore.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.iksen.chessCore.utill.JwtUtill;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter to process JWT tokens and set up Authentication in the SecurityContext
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtill jwtUtill;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            // Extract token from request
            String token = jwtUtill.extractToken(request);
            
            // If token exists and is valid, set up authentication
            if (token != null && jwtUtill.validateToken(token)) {
                String username = jwtUtill.extractUsername(token);
                
                // Create authentication object with username and (optionally) authorities
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, AuthorityUtils.NO_AUTHORITIES);
                
                // Add request details to authentication
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // Set authentication in context
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                // For debugging
                System.out.println("Setting authentication for user: " + username);
            }
        } catch (Exception e) {
            System.out.println("Failed to process JWT token: " + e.getMessage());
            // Don't throw exception, just continue with unauthenticated request
        }
        
        // Continue filter chain
        filterChain.doFilter(request, response);
    }
}

