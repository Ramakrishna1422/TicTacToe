package com.analyttica.tictactoe.controller;

import com.analyttica.tictactoe.manager.Game;
import com.analyttica.tictactoe.utils.GameFactory;
import com.analyttica.tictactoe.utils.PlayerCache;
import com.analyttica.tictactoe.models.Players;

import java.util.Scanner;

public class TicTacToeStarter {

    /***
     * controller to initiate the game
     */
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        String playOrSkip = "y";
        do {
            System.out.println("TicTocToe !!!");
            System.out.println("1. Pattern Game");
            System.out.println("2. Number Game");
            System.out.print("Enter your choice: ");
            int gameMode = scanner.nextInt();
            GameFactory factory = new GameFactory();
            Game game = factory.loadGame(gameMode);
            if(game != null) {
                System.out.println("1. Single Player");
                System.out.println("2. Multi Player");
                System.out.print("Enter your choice: ");
                int playerMode = scanner.nextInt();
                if(playerMode == 1) {
                    game.setPlayerMode(true);
                    game.switchPlayer();
                }
                game.displayBoard();
                System.out.println();
                do {
                    System.out.println(PlayerCache.cache.get(game.getCurrentPlayer()).getName() + " turn !\n");
                    if((game.isSinglePlayer() && game.getCurrentPlayer() == Players.PLAYER_1) || game.getCurrentPlayer() != Players.COMPUTER) {
                        System.out.print("Enter x & y positions to place " + game.getUserValue() + ":");
                        int xPosition = scanner.nextInt();
                        int yPosition = scanner.nextInt();
                        int userValue;
                        if(gameMode == 2) {
                            System.out.print("Enter your value from (1-9):");
                            userValue = scanner.nextInt();
                        } else {
                            userValue = (int) game.getUserValue().charAt(0);
                        }

                        if(game.placeSign(xPosition, yPosition, userValue)) {
                            game.switchPlayer();
                        } else {
                            System.out.println("Invalid selection. Please try again");
                        }
                    } else {
                        game.placeSignByComputer();
                        game.switchPlayer();
                    }
                    game.displayBoard();
                } while (!game.checkForWin() && !game.isBoardFull());

                if (game.isBoardFull() && !game.checkForWin()) {
                    System.out.println(":( Game tie!");
                } else {
                    System.out.println("Board Layout:");
                    game.displayBoard();
                    game.switchPlayer();
                    System.out.println(PlayerCache.cache.get(game.getCurrentPlayer()).getName() + " Wins!");
                }
                System.out.print("Do you want play again (y/n) : ");
                playOrSkip = scanner.next();
            } else {
                System.out.println("Invalid Game Selection.");
            }
        } while(playOrSkip.equalsIgnoreCase("y"));
        scanner.close();
    }
}
