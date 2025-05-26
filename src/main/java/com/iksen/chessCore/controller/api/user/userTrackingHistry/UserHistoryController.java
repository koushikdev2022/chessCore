package com.iksen.chessCore.controller.api.user.userTrackingHistry;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.auth.logTracking.UserHistoryDTO;
import com.iksen.chessCore.serviceImpl.user.auth.tracking.UserTrackingServiceImpl;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/tracking")
public class UserHistoryController {
        @Autowired
        private UserTrackingServiceImpl userTrackingServiceImpl;
        @PostMapping("/track-history")
        public ResponseEntity<?> saveHistory(@Valid @RequestBody UserHistoryDTO userHistoryDTO){
                 
                UserHistoryDTO saveDetails = userTrackingServiceImpl.saveHistory(userHistoryDTO);
                return ResponseEntity.status(201).body(Map.of(
                                "status", true,
                                "message", "login details update successfully",
                                "status_code", 201,
                                "saveDetails",saveDetails
                            ));
        }
}
