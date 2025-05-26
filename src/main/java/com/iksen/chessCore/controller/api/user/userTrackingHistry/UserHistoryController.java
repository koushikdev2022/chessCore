package com.iksen.chessCore.controller.api.user.userTrackingHistry;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.config.CustomUserDetails;
import com.iksen.chessCore.dto.auth.logTracking.UserHistoryDTO;
import com.iksen.chessCore.service.user.auth.login.CustomUserDetailsService;
import com.iksen.chessCore.serviceImpl.user.auth.tracking.UserTrackingServiceImpl;
import com.iksen.chessCore.utill.AuthenticationUtility;
import com.iksen.chessCore.utill.JwtUtill;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/tracking")
public class UserHistoryController {
        @Autowired
        private JwtUtill jwtUtill;
        @Autowired
        private UserTrackingServiceImpl userTrackingServiceImpl;
        @PostMapping("/track-history")
        public ResponseEntity<?> saveHistory(@Valid @RequestBody UserHistoryDTO userHistoryDTO,
                                            Authentication authentication) {
            Long userId = jwtUtill.getCurrentUserId();
            System.out.println(userId + " userId");
            userHistoryDTO.setUserId(userId);
            UserHistoryDTO saveDetails = userTrackingServiceImpl.saveHistory(userHistoryDTO);

            return ResponseEntity.status(201).body(Map.of(
                "status", true,
                "message", "login details updated successfully",
                "status_code", 201,
                "saveDetails", saveDetails
            ));
        }
}
