package com.analyttica.tictactoe.utils;

import com.analyttica.tictactoe.manager.Game;
import com.analyttica.tictactoe.manager.NumberGame;
import com.analyttica.tictactoe.manager.PatternGame;

public class GameFactory {

    public Game loadGame(int gameType) {
        if(gameType == 1) {
            return new PatternGame();
        } else if(gameType == 2) {
            return new NumberGame();
        } else {
            //@TODO: to add more games
            return null;
        }
    }
}
