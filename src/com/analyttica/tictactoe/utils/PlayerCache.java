package com.analyttica.tictactoe.utils;

import com.analyttica.tictactoe.models.Player;
import com.analyttica.tictactoe.models.Players;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ramakrishna on 9/2/20.
 */
public class PlayerCache {
    public static Map<Integer, Player> cache = new HashMap<>(3);
    static {
        cache.put(Players.COMPUTER, new Player(Players.COMPUTER, "Computer"));
        cache.put(Players.PLAYER_1, new Player(Players.PLAYER_1, "Player 1"));
        cache.put(Players.PLAYER_2, new Player(Players.PLAYER_2, "Player 2"));
    }
}
