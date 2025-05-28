package com.iksen.chessCore.serviceImpl.socket;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.service.socket.SocketService;

@Service
public class SocketServiceImpl implements SocketService{
    private WebSocketClient client;

    public void connectToGameStream(String gameId, String token) {
        try {
            URI uri = new URI("wss://socket.lichess.org/api/stream/game/" + gameId);

            client = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("Connected to Lichess WebSocket.");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("Game Update: " + message);
                    // You can broadcast this message to your frontend if needed
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Connection closed: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    System.err.println("WebSocket Error: " + ex.getMessage());
                }
            };

            // Set Authorization header
            client.addHeader("Authorization", "Bearer " + token);

            client.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (client != null && client.isOpen()) {
            client.close();
        }
    }
}
