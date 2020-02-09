package com.analyttica.tictactoe;

public class PatternGame extends AbstractGame {

    public PatternGame() {
        initializeBoard();
    }

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

    @Override
    public int getUserValue() {
        if(isSinglePlayer()) {
            if(getCurrentPlayer() == Players.COMPUTER) {
                return (int) 'X';
            } else {
                return (int) 'O';
            }
        } else {
            if(getCurrentPlayer() == Players.PLAYER_1) {
                return (int) 'X';
            } else {
                return (int) 'O';
            }
        }
    }

    @Override
    public void placeSignByComputer() {
        int bestScore = -1000;
        int bestRow = 0;
        int bestCol = 0;
        for (int i = 0; i < BOARDSIZE ; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if(board[i][j] == 0) {
                    board[i][j] = (int) 'X';
                    int score = bestMove(0, false);
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


    private int bestMove(int depth, boolean isMax) {
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
                        int score = bestMove(depth + 1, false);
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
                        int score = bestMove(depth + 1, true);
                        board[i][j] = 0;
                        best = Math.min(score, best);
                    }
                }
            }
            return best;
        }
    }
}
