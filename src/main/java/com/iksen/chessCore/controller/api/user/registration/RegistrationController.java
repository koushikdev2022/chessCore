package com.iksen.chessCore.controller.api.user.registration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;
import com.iksen.chessCore.model.DummyUser;
import com.iksen.chessCore.serviceImpl.user.auth.registration.RegistrationServiceImpl;
import com.iksen.chessCore.utill.JwtUtill;

import jakarta.validation.Valid;

import org.springframework.validation.BindingResult;

import com.iksen.chessCore.dto.auth.registration.DummyUserDTO;
import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;
import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.mapper.DummyUserMapper;
import com.iksen.chessCore.mapper.UserMapper;
import com.iksen.chessCore.model.User;

@RestController()
@RequestMapping("/api/auth")
public class RegistrationController {
            @Autowired
            private RegistrationServiceImpl registrationServiceImpl;
            @Autowired
            private JwtUtill jwtUtill;
            @PostMapping("/first-step")
            public ResponseEntity<?> registerStepOne(@Valid @RequestBody FirstStepUserDTO dto, BindingResult bindingResult) {
                // 1. Field validation errors
                if (bindingResult.hasErrors()) {
                    Map<String, String> errors = new HashMap<>();
                    bindingResult.getFieldErrors().forEach(error ->
                            errors.put(error.getField(), error.getDefaultMessage())
                    );
            
                    return ResponseEntity.status(422).body(Map.of(
                            "status", false,
                            "message", "Validation failed",
                            "status_code", 422,
                            "errors", errors
                    ));
                }
            
                Map<String, String> validationErrors = new TreeMap<>();
            
                Optional<UserDTO> userByEmail = registrationServiceImpl.findEmail(dto.getEmail());
                if (userByEmail.isPresent()) {
                    validationErrors.put("email", "Email already exists");
                }
            
                Optional<UserDTO> userByUsername = registrationServiceImpl.findUsername(dto.getUserName());
                if (userByUsername.isPresent()) {
                    validationErrors.put("username", "Username already exists");
                }
            
                if (!validationErrors.isEmpty()) {
                    return ResponseEntity.status(422).body(Map.of(
                            "status", false,
                            "message", "Validation failed",
                            "status_code", 422,
                            "errors", validationErrors
                    ));
                }
            
             
                Optional<DummyUser> dummyUser = registrationServiceImpl.firstStep(dto);
            
                return ResponseEntity.status(200).body(Map.of(
                        "status", true,
                        "message", "First step completed successfully",
                        "status_code", 200,
                        "user", dummyUser
                ));
            }
            
        @PostMapping("/second-step")
        public ResponseEntity<?> registerSecondStep(@Valid @RequestBody SecondStepDTO dto){
    
                Optional<DummyUserDTO> dummyUserDTO = registrationServiceImpl.findDummyUser(dto.getUser_id());
                if (dummyUserDTO.isEmpty()) {
                    return ResponseEntity.status(422).body(Map.of(
                            "status", false,
                            "message", "User details not found invalid user_id",
                            "status_code", 422
                    ));
                }
                UserDTO user = dummyUserDTO.map(DummyUserMapper::toUserDTO).orElseThrow(() -> new IllegalStateException("User not found"));
                user.setMobile(dto.getMobile());
                Optional<UserDTO> saveUser = registrationServiceImpl.saveUser(user);
                saveUser.ifPresent(u -> u.setPassword(null));
                if (saveUser.isPresent() && saveUser.get().getId()> 0) {
                        boolean data = registrationServiceImpl.deleteDummy(dto.getUser_id());
                        if(data){
                                User saveUserD = saveUser.map(UserMapper::toUser).orElseThrow(() -> new IllegalStateException("User not found"));
                                String token = jwtUtill.generateToken(saveUserD);
                                return ResponseEntity.status(201).body(Map.of(
                                        "status", true,
                                        "message", "Login successful",
                                        "status_code", 201,
                                        "user",saveUser,
                                        "token",token
                                        
                                ));
                        }else{
                                return ResponseEntity.status(400).body(Map.of(
                                        "status", true,
                                        "message", "could not complete registration",
                                        "status_code", 400
                                        
                                )); 
                        }
                   
                }else{
                        return ResponseEntity.status(400).body(Map.of(
                                "status", true,
                                "message", "registration failed",
                                "status_code", 400
                                
                        ));
                }
             

        }
        
}
