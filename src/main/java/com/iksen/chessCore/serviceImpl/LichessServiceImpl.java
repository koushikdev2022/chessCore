package com.iksen.chessCore.serviceImpl;

import com.iksen.chessCore.service.lichess.LichessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LichessServiceImpl implements LichessService {
            @Value("${lichess.api.token}")
            private String token;

            private final String baseUrl = "https://lichess.org/api";

            private HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(token);
                headers.set("Accept", "application/json");
                return headers;
            }

            public String getAccountInfo() {
                String url = baseUrl + "/account";
                return callGet(url);
            }

            public String getCurrentGames() {
                String url = baseUrl + "/account/playing";
                return callGet(url);
            }

            public String challengeUser(String username) {
                String url = baseUrl + "/challenge/" + username;
                HttpHeaders headers = getHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                HttpEntity<String> request = new HttpEntity<>("rated=false&clock.limit=300&clock.increment=0", headers);
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
                return response.getBody();
            }

            public String makeMove(String gameId, String move) {
                String url = baseUrl + "/bot/game/" + gameId + "/move/" + move;
                HttpHeaders headers = getHeaders();
                HttpEntity<String> entity = new HttpEntity<>(headers);

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                return response.getBody();
            }

            private String callGet(String url) {
                HttpHeaders headers = getHeaders();
                HttpEntity<String> entity = new HttpEntity<>(headers);

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                return response.getBody();
            }
}
