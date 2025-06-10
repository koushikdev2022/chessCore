package com.iksen.chessCore.controller.api.user.plan;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iksen.chessCore.dto.country.CountryDTO;
import com.iksen.chessCore.serviceImpl.user.country.CountryServiceImpl;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
       @Autowired
        private CountryServiceImpl countryServiceImpl;
       @GetMapping("/list/{ip}")
       public ResponseEntity<?> allPlan(@PathVariable("ip") String ip  ){
            try{
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "https://ipwho.is/" + ip;

                    Map response = restTemplate.getForObject(url, Map.class);
                    String countryName = (String) response.get("country");

                    List<CountryDTO> countryData = countryServiceImpl.findCountryName(countryName);
                    int id ;
                    if(countryData.isEmpty()){
                         List<CountryDTO> countryDataPredefined = countryServiceImpl.findCountryName("India");
                         id = countryDataPredefined.get(0).getId();
                    }else{
                         id = countryData.get(0).getId();
                    }
                    

                    if (response == null || !(Boolean) response.get("success")) {
                        return ResponseEntity.status(400).body(Map.of(
                                "status", false,
                                "message", "Invalid IP or failed to fetch location.",
                                "status_code", 400
                        ));
                    }

                    return ResponseEntity.ok(Map.of(
                            "status", true,
                            "data", response.get("country"),
                            "status_code", 200
                    ));
            } catch (Exception e) {
                    return ResponseEntity.status(400).body(Map.of(
                            "status", false,
                            "message", "something went wrong",
                            "status_code", 400,
                            "error",e
                    ));
            }
       }
    
}
