package com.iksen.chessCore.controller.api.user.address;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.auth.userAddress.UserAddressDTO;
import com.iksen.chessCore.serviceImpl.user.auth.registration.UserAddressServiceImpl;
import com.iksen.chessCore.utill.JwtUtill;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/user")
public class UserAddressController {
        @Autowired
        private UserAddressServiceImpl userAddressServiceImpl;
        @Autowired
        private JwtUtill jwtUtill;

        @PostMapping("/add-address")
        public ResponseEntity<?> updateAddress(@Valid @RequestBody UserAddressDTO userAddressDTO){
                        try{
                               userAddressDTO.setUserId(jwtUtill.getCurrentUserId());
                               UserAddressDTO user = userAddressServiceImpl.saveAddressToData(userAddressDTO);
                               
                                if(user.getId()>0){
                                        return ResponseEntity.status(201).body(Map.of(
                                                "status", true,
                                                "message", "address Created",
                                                "status_code", 201
                                         ));
                                }else{
                                         return ResponseEntity.status(400).body(Map.of(
                                                "status", false,
                                                "message", "address not Created",
                                                "status_code", 400
                                         ));
                                }
                               
                        }catch (ValidationException e) {
                                return ResponseEntity.status(400).body(Map.of(
                                        "status", false,
                                        "message", "Validation failed: " + e.getMessage(),
                                        "status_code", 400
                                ));
                                
                        // } catch (DataIntegrityViolationException e) {
                        //         return ResponseEntity.status(409).body(Map.of(
                        //                 "status", false,
                        //                 // "message", "Child already exists or data conflict",
                        //                 "status_code", 409
                        //         ));
                                
                        } catch (Exception e) {
                                return ResponseEntity.status(400).body(Map.of(
                                        "status", false,
                                        "message", "something went wrong",
                                        "status_code", 400
                                ));
                        }
        }
}
