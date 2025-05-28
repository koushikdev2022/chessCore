package com.iksen.chessCore.service.lichess;

public interface LichessService {
     String getAccountInfo();

    String getCurrentGames();

    String challengeUser(String username);

    String makeMove(String gameId, String move);

    String streamGame(String gameId);
}
