package com.iksen.chessCore.controller.api.user.plan;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/plan-key")
public class PlanKeyController {
            @GetMapping("/list/{payment_id}/{plan_details_id}")
            public ResponseEntity<?> list(
                    @PathVariable("payment_id") int paymentId,
                    @PathVariable("plan_details_id") int planDetailsId) {

                try {
                   
                    Map<String, Object> response = Map.of(
                            "status", true,
                            "status_code", 200,
                            "paymentId", paymentId,
                            "planDetailsId", planDetailsId
                    );
                    return ResponseEntity.ok(response);

                } catch (Exception e) {
                    return ResponseEntity.status(400).body(Map.of(
                            "status", false,
                            "status_code", 400,
                            "message", "Something went wrong",
                            "error", e.getMessage()
                    ));
                }
    }

}
