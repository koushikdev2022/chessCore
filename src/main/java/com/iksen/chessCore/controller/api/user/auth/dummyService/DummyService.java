package com.iksen.chessCore.controller.api.user.auth.dummyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyService {
        @GetMapping("/access")
        public String log(){
            return "working";
        }
}
