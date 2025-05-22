package com.iksen.chessCore.controller.api.user.auth;

import java.util.Map;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
                if (loginDTO.getUserNameOrEmail() == null || loginDTO.getUserNameOrEmail().trim().isEmpty()) {
                    return ResponseEntity.status(400).body(Map.of(
                        "status", false,
                        "message", "Username or email is required",
                        "status_code", 400
                    ));
                }

                if (loginDTO.getPassword() == null || loginDTO.getPassword().trim().isEmpty()) {
                    return ResponseEntity.status(400).body(Map.of(
                        "status", false,
                        "message", "Password is required",
                        "status_code", 400
                    ));
                }

                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserNameOrEmail(),
                        loginDTO.getPassword()
                    )
                );

                Optional<User> userOptional = loginServiceImpl.login(loginDTO);
                 
                if (userOptional.isEmpty()) {
                    return ResponseEntity.status(422).body(Map.of(
                        "status", false,
                        "message", "User not found",
                        "status_code", 422
                    ));
                }

                User user = userOptional.get();
               
                String token = jwtUtill.generateToken(user);

                return ResponseEntity.status(200).body(Map.of(
                    "status", true,
                    "message", "Login successful",
                    "status_code", 200,
                    "token", token,
                    "user", Map.of(
                        "id", user.getId(),
                        "username", user.getUserName(),
                        "email", user.getEmail(),
                        "parentId", user.getParentId()
                    )
                ));

            } catch (BadCredentialsException ex) {
                return ResponseEntity.status(422).body(Map.of(
                    "status", false,
                    "message", "Invalid username or password",
                    "status_code", 422
                ));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(400).body(Map.of(
                    "status", false,
                    "message", "Internal server error: " + e.getMessage(),
                    "status_code", 400
                ));
            }
        }

}

