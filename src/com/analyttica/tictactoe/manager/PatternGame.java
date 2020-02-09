package com.analyttica.tictactoe.manager;

import com.analyttica.tictactoe.models.Players;

public class PatternGame extends AbstractGame {

    public PatternGame() {
        initializeBoard();
    }

    /***
     * to place the user value in the board
     * @param xPosition
     * @param yPosition
     * @param value
     * @return true will indicates value accepted and placed. false will not accepted.
     */
    @Override
    public boolean placeSign(int xPosition, int yPosition, int value) {
        if (xPosition >= 0 && xPosition < BOARDSIZE  && (yPosition >=0 && yPosition < BOARDSIZE)) {
            if (board[xPosition][yPosition] == 0) {
                board[xPosition][yPosition] = value;
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean gameStrategyMatch(int a, int b, int c) {
        return (a + b + c) == 3 * (int) 'X' || (a + b + c) == 3 * (int) 'O';
    }

    /***
     * to identify user value type
     * @return
     */
    @Override
    public String getUserValue() {
        if(isSinglePlayer()) {
            if(getCurrentPlayer() == Players.COMPUTER) {
                return "X";
            } else {
                return "O";
            }
        } else {
            if(getCurrentPlayer() == Players.PLAYER_1) {
                return "X";
            } else {
                return "O";
            }
        }
    }

    /***
     * AI based best position identification to win or make it tie.
     */
    @Override
    public void placeSignByComputer() {
        int bestScore = -1000;
        int bestRow = 0;
        int bestCol = 0;
        for (int i = 0; i < BOARDSIZE ; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if(board[i][j] == 0) {
                    board[i][j] = (int) 'X';
                    int score = bestMove(false);
                    board[i][j] = 0;
                    if(score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }
        board[bestRow][bestCol] = (int) 'X';
    }

    /***
     * recursively comparing to get the best move (minimax algorithm)
     * @param isMax
     * @return
     */
    private int bestMove(boolean isMax) {
        boolean isWon = checkForWin();
        if(isWon) {
            return winningScore[isMax ? Players.PLAYER_1 : Players.COMPUTER];
        } else if(isBoardFull()) {
            return 0;
        }
        if(isMax) {
            int best = -1000;
            for (int i = 0; i < BOARDSIZE; i++) {
                for (int j = 0; j < BOARDSIZE; j++) {
                    if(board[i][j] == 0) {
                        board[i][j] = (int) 'X';
                        int score = bestMove(false);
                        board[i][j] = 0;
                        best = Math.max(score, best);
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < BOARDSIZE; i++) {
                for (int j = 0; j < BOARDSIZE; j++) {
                    if(board[i][j] == 0) {
                        board[i][j] = (int) 'O';
                        int score = bestMove(true);
                        board[i][j] = 0;
                        best = Math.min(score, best);
                    }
                }
            }
            return best;
        }
    }
}
