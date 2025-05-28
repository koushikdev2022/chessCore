package com.iksen.chessCore.controller.api.user.lichess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.service.lichess.LichessService;
import com.iksen.chessCore.serviceImpl.LichessServiceImpl;

@RestController
@RequestMapping("/api/lichess")
public class LichessController {
            @Autowired
            private LichessServiceImpl lichessServiceImpl;

            @GetMapping("/account")
            public String getAccountInfo() {
                return lichessServiceImpl.getAccountInfo();
            }

            @GetMapping("/games")
            public String getCurrentGames() {
                return lichessServiceImpl.getCurrentGames();
            }

            @PostMapping("/challenge/{username}")
            public String challengeUser(@PathVariable String username) {
                return lichessServiceImpl.challengeUser(username);
            }

            @PostMapping("/move")
            public String makeMove(@RequestParam String gameId, @RequestParam String move) {
                return lichessServiceImpl.makeMove(gameId, move);
            }
}
