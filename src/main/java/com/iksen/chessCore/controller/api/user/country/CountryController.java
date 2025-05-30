package com.iksen.chessCore.controller.api.user.country;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.country.CountryDTO;
import com.iksen.chessCore.serviceImpl.user.country.CountryServiceImpl;

@RestController
@RequestMapping("/api/country")
public class CountryController {
        @Autowired
        private CountryServiceImpl countryServiceImpl;
        @GetMapping("/list")
        public ResponseEntity<?> country(){
                try {
                        List<CountryDTO> country = countryServiceImpl.list();
                        if(!country.isEmpty()){
                              return ResponseEntity.ok(Map.of(
                                "status", false,
                                "message", "country found",
                                "status_code", 400,
                                "country",country
                              ));
                        }else{
                              return ResponseEntity.ok(Map.of(
                                        "status", false,
                                        "message", "no country",
                                        "status_code", 200
                               ));
                        }
                      
                } catch (Exception e) {
                             return ResponseEntity.status(400).body(Map.of(
                                    "status", false,
                                    "message", "something went wrong",
                                    "error",e,
                                    "status_code", 400
                            ));
                }
        }
}
