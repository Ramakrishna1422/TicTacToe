package com.analyttica.tictactoe;

import java.util.Scanner;

public class TicTacToeStarter {

    public static void main(String[] args1) {
        Scanner scanner = new Scanner(System.in);
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
                System.out.println("Player " + game.getCurrentPlayer() + " turn !" + game.isSinglePlayer());
                if((game.isSinglePlayer() && game.getCurrentPlayer() == Players.PLAYER_1) || game.getCurrentPlayer() != Players.COMPUTER) {
                    System.out.print("Enter x & y positions:");
                    int xPosition = scanner.nextInt();
                    int yPosition = scanner.nextInt();
                    int userValue = 0;
                    if(gameMode == 2) {
                        System.out.print("Enter your value from (1-9):");
                        userValue = scanner.nextInt();
                    } else {
                        userValue = game.getUserValue();
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
                System.out.println("The game was a tie!");
            } else {
                System.out.println("Current board layout:");
                game.displayBoard();
                game.switchPlayer();
                System.out.println("Player " + game.getCurrentPlayer() + " Wins!");
            }
        } else {
            System.out.println("Invalid Game Selection.");
        }
    }
}
