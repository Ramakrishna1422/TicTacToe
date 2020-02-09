package com.analyttica.tictactoe;


public class NumberGame extends AbstractGame {

    public NumberGame() {
        initializeBoard();
    }

    @Override
    public boolean placeSign(int xPosition, int yPosition, int value) {
        if (xPosition >= 0 && xPosition < BOARDSIZE  && (yPosition >=0 && yPosition < BOARDSIZE)) {
            if (board[xPosition][yPosition] == 0) {
                if (value >= 1 && value <= 9 && !isValueExists(value) && isValidPlayerOption(value)) {
                    board[xPosition][yPosition] = value;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPlayerOption(int value) {
        return (value % 2 == (currentPlayer == Players.PLAYER_1 ? 1 : 0));
    }

    private boolean isValueExists(int value) {
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if(board[i][j] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected boolean gameStrategyMatch(int a, int b, int c) {
        return (a + b + c) == 15;
    }

    @Override
    public void placeSignByComputer() {
        board[1][1] = 3;
    }
}
