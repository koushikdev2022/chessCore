package com.iksen.chessCore.controller.api.user.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.serviceImpl.socket.SocketServiceImpl;

@RestController
@RequestMapping("/api/lichess/socket")
public class LichessSocketController {

    @Autowired
    private SocketServiceImpl socketServiceImpl;

    @PostMapping("/connect/{gameId}")
    public String connectToSocket(@PathVariable String gameId, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        socketServiceImpl.connectToGameStream(gameId, token);
        return "Connecting to game stream for game ID: " + gameId;
    }

    @PostMapping("/disconnect")
    public String disconnect() {
        socketServiceImpl.closeConnection();
        return "Disconnected from Lichess WebSocket.";
    }
}
