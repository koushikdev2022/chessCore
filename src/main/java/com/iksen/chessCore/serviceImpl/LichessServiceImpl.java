package com.iksen.chessCore.serviceImpl;

import com.iksen.chessCore.service.lichess.LichessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LichessServiceImpl implements LichessService {

    private static final Logger logger = LoggerFactory.getLogger(LichessServiceImpl.class);
    
    private final String token = "lip_aGGHiDZE87C6xzJXrjLZ";
    // private final String token = "lip_UQ98IARrzjJXGXCduVAn";
    private final String baseUrl = "https://lichess.org/api";

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.set("Accept", "application/json");
        return headers;
    }

    @Override
    public String getAccountInfo() {
        return callGet(baseUrl + "/account");
    }

    @Override
    public String getCurrentGames() {
        return callGet(baseUrl + "/account/playing");
    }

    // Add method to check if user exists
    public String getUserInfo(String username) {
        try {
            return callGet("https://lichess.org/api/user/" + username);
        } catch (Exception e) {
            logger.error("Error getting user info for {}: {}", username, e.getMessage());
            return null;
        }
    }

    // Simple method to extract username from JSON response
    private String extractUsernameFromJson(String json) {
        try {
            // Simple regex to find "username":"value"
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\"username\"\\s*:\\s*\"([^\"]+)\"");
            java.util.regex.Matcher matcher = pattern.matcher(json);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            logger.warn("Error extracting username from JSON: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public String challengeUser(String username) {
        try {
            // Check if trying to challenge self
            String accountInfo = getAccountInfo();
            try {
                // Parse account info to get current username
                // Simple JSON parsing to extract username
                String currentUsername = extractUsernameFromJson(accountInfo);
                if (currentUsername != null && currentUsername.equalsIgnoreCase(username)) {
                    return "{\"error\":\"Cannot challenge yourself\"}";
                }
            } catch (Exception e) {
                logger.warn("Could not parse account info to check for self-challenge: {}", e.getMessage());
            }
            
            // Check if the user exists
            String userInfo = getUserInfo(username);
            if (userInfo == null) {
                return "{\"error\":\"User not found or not accessible\"}";
            }
            
            logger.info("Attempting to challenge user: {}", username);
            
            String url = "https://lichess.org/api/challenge/" + username;
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Accept", "application/json");

            // More comprehensive challenge parameters
            String body = "rated=false&clock.limit=300&clock.increment=0&variant=standard&fen=";
            HttpEntity<String> request = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            
            logger.info("Challenge response: {}", response.getBody());
            return response.getBody();
            
        } catch (HttpClientErrorException e) {
            logger.error("HTTP Error challenging user {}: {} - {}", username, e.getStatusCode(), e.getResponseBodyAsString());
            return String.format("{\"error\":\"HTTP %s: %s\"}", e.getStatusCode(), e.getResponseBodyAsString());
        } catch (Exception e) {
            logger.error("Unexpected error challenging user {}: {}", username, e.getMessage());
            return "{\"error\":\"Unexpected error: " + e.getMessage() + "\"}";
        }
    }

    @Override
    public String makeMove(String gameId, String move) {
        try {
            String url = baseUrl + "/bot/game/" + gameId + "/move/" + move;
            HttpHeaders headers = getHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("HTTP Error making move: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return String.format("{\"error\":\"HTTP %s: %s\"}", e.getStatusCode(), e.getResponseBodyAsString());
        } catch (Exception e) {
            logger.error("Unexpected error making move: {}", e.getMessage());
            return "{\"error\":\"Unexpected error: " + e.getMessage() + "\"}";
        }
    }

    private String callGet(String url) {
        try {
            HttpHeaders headers = getHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("HTTP Error on GET {}: {} - {}", url, e.getStatusCode(), e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error on GET {}: {}", url, e.getMessage());
            throw e;
        }
    }
}