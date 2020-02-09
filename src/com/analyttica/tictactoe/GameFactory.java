package com.analyttica.tictactoe;

public class GameFactory {

    public Game loadGame(int gameType) {
        if(gameType == 1) {
            return new PatternGame();
        } else if(gameType == 2) {
            return new NumberGame();
        } else {
            return null;
        }
    }
}
