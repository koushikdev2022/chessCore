package com.iksen.chessCore.controller.api.user.registration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;
import com.iksen.chessCore.model.DummyUser;
import com.iksen.chessCore.serviceImpl.user.auth.registration.RegistrationServiceImpl;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@RestController()
@RequestMapping("/api/auth")
public class RegistrationController {
            @Autowired
            private RegistrationServiceImpl registrationServiceImpl;
            @PostMapping("/first-step")
            public ResponseEntity<?> registerStepOne(@Valid @RequestBody FirstStepUserDTO dto ,BindingResult bindingResult){
            if (bindingResult.hasErrors()) {
        // Collect all validation error messages
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
            Optional<DummyUser> user = registrationServiceImpl.firstStep(dto);
            return ResponseEntity.status(200).body(Map.of(
                    "status", true,
                    "message", "Login successful",
                    "status_code", 200,
                    "user", user
            ));

        }
}
