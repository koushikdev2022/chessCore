package com.iksen.chessCore.controller.api.user.auth;

import java.util.Map;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.auth.LoginDTO;
import com.iksen.chessCore.model.User;
import com.iksen.chessCore.serviceImpl.user.auth.login.LoginServiceImpl;
import com.iksen.chessCore.utill.JwtUtill;

@RestController()
@RequestMapping("/api/auth")
public class LoginController {
        @Autowired
        LoginServiceImpl loginServiceImpl;
        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private JwtUtill jwtUtill;
        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
            try {
                // Authenticate user
                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserNameOrEmail(),
                        loginDTO.getPassword()
                    )
                );

                // Get user details after successful authentication
                Optional<User> userOptional = loginServiceImpl.login(loginDTO); // Fixed parameter name
                  if (userOptional.isEmpty()) {
                    return ResponseEntity.status(401).body(Map.of(
                        "status", false,
                        "message", "User not found",
                        "status_code", 401
                    ));
                }
                if (!userOptional.isPresent()) {
                    return ResponseEntity.status(401).body(Map.of(
                        "status", false,
                        "message", "User not found",
                        "status_code", 401
                    ));
                }
                
                User user = userOptional.get();
                
                // Generate JWT token
                String jwt = jwtUtill.generateToken(user);

                return ResponseEntity.status(200).body(Map.of(
                    "status", true,
                    "message", "Login successful",
                    "status_code", 200,
                    "jwt", jwt,
                    "user", Map.of(
                        "id", user.getId(),
                        "username", user.getUserName(),
                        "email", user.getEmail(),
                        "fullName", user.getFirstName()
                    )
                ));
            } catch (Exception e) {
                return ResponseEntity.status(500).body(Map.of(
                    "status", false,
                    "message", "Internal server error",
                    "status_code", 500
                ));
            }
        }
}

