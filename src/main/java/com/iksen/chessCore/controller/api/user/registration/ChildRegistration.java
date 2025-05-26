package com.iksen.chessCore.controller.api.user.registration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.auth.registration.ChildDTO;
import com.iksen.chessCore.utill.JwtUtill;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/child")
public class ChildRegistration {
        @Autowired
        private JwtUtill jwtUtill;
        @PostMapping("/registration")
        public ResponseEntity<?> registerChild(@Valid @RequestBody ChildDTO childDTO){
        //    try {
                        Long userId = jwtUtill.getCurrentUserId();

                        String password = childDTO.getPassword();
                        String confirmPassword = childDTO.getConfirmPassword();
                        if (password != null && !password.equals(confirmPassword)) {
                                Map<String, Object> errors = Map.of(
                                        "password", new String[] { "Password and Confirm Password must match" },
                                        "confirmPassword", new String[] { "Password and Confirm Password must match" }
                                );

                                return ResponseEntity.badRequest().body(Map.of(
                                        "status", false,
                                        "message", "Validation error",
                                        "errors", errors,
                                        "status_code", 400
                                ));
                        }
                        
                        return ResponseEntity.status(200).body(Map.of(
                                "status", true,
                                "message", "Child registered successfully",
                                "status_code", 200
                        ));
                        
                // } catch (ValidationException e) {
                //         return ResponseEntity.status(400).body(Map.of(
                //                 "status", false,
                //                 "message", "Validation failed: " + e.getMessage(),
                //                 "status_code", 400
                //         ));
                        
                // } catch (DataIntegrityViolationException e) {
                //         return ResponseEntity.status(409).body(Map.of(
                //                 "status", false,
                //                 "message", "Child already exists or data conflict",
                //                 "status_code", 409
                //         ));
                        
                // } catch (Exception e) {
                //         return ResponseEntity.status(400).body(Map.of(
                //                 "status", false,
                //                 "message", "something went wrong",
                //                 "status_code", 400
                //         ));
                // }
        }
}
