package com.iksen.chessCore.controller.api.user.lichess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.service.lichess.LichessService;
import com.iksen.chessCore.serviceImpl.LichessServiceImpl;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/lichess")
public class LichessController {

    private final LichessServiceImpl lichessServiceImpl;

    public LichessController(LichessServiceImpl lichessServiceImpl) {
        this.lichessServiceImpl = lichessServiceImpl;
    }

    @GetMapping("/account")
    public ResponseEntity<String> getAccount() {
        return ResponseEntity.ok(lichessServiceImpl.getAccountInfo());
    }

    @GetMapping("/games")
    public ResponseEntity<String> getGames() {
        return ResponseEntity.ok(lichessServiceImpl.getCurrentGames());
    }

    // Add endpoint to check user info
    @GetMapping("/user/{username}")
    public ResponseEntity<String> getUserInfo(@PathVariable String username) {
        String userInfo = lichessServiceImpl.getUserInfo(username);
        if (userInfo != null) {
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/challenge/{username}")
    public ResponseEntity<String> challenge(@PathVariable String username) {
        // Validate username is not empty
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\":\"Username cannot be empty\"}");
        }
        
        return ResponseEntity.ok(lichessServiceImpl.challengeUser(username.trim()));
    }

    @PostMapping("/move")
    public ResponseEntity<String> makeMove(
            @RequestParam String gameId,
            @RequestParam String move) {
        return ResponseEntity.ok(lichessServiceImpl.makeMove(gameId, move));
    }
}