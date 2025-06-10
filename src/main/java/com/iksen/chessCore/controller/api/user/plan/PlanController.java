package com.iksen.chessCore.controller.api.user.plan;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
       @GetMapping("/list/{ip}")
       public ResponseEntity<?> allPlan(@PathVariable("ip") String ip  ){
            try{

                    RestTemplate restTemplate = new RestTemplate();
                    String url = "https://ipwho.is/" + ip;
                    
                    Map response = restTemplate.getForObject(url, Map.class);

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
