package com.iksen.chessCore.controller.api.user.child;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.serviceImpl.batch.UserBatchMapServiceImpl;
import com.iksen.chessCore.serviceImpl.user.ChildServiceImpl;
import com.iksen.chessCore.utill.JwtUtill;

@RestController
@RequestMapping("/api/child")
public class ChildController {
        @Autowired
        private ChildServiceImpl childServiceImpl;
        @Autowired
        private JwtUtill jwtUtill;
        @Autowired
        private UserBatchMapServiceImpl userBatchMapServiceImpl;
        @GetMapping("/list")
        public ResponseEntity<?> batchList(){
              try{
                    Long userId = jwtUtill.getCurrentUserId();
                    List<UserDTO> child = childServiceImpl.childDetails(userId);
                    return ResponseEntity.ok(Map.of(
                        "status", true,
                        "message", "batch found",
                        "status_code", 200,
                        "child_account",child
                    ));
              }catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(400).body(Map.of(
                        "status", false,
                        "message", "Internal server error: " + e.getMessage(),
                        "status_code", 400
                    ));
              }
        }
}
