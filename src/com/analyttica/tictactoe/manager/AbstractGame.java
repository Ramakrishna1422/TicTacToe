package com.analyttica.tictactoe.manager;

import com.analyttica.tictactoe.models.Players;

public abstract class AbstractGame implements Game {

    protected int[][] board = new int[BOARDSIZE][BOARDSIZE];
    protected int currentPlayer = Players.PLAYER_1;
    protected boolean isSinglePlayerMode;
    protected int[] winningScore = {10, -10};

    /***
     * creating empty board
     */
    @Override
    public void initializeBoard() {
        board = new int[BOARDSIZE][BOARDSIZE];
    }

    /***
     * display board with user values
     */
    @Override
    public void displayBoard() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if(board[i][j] == 0) {
                    builder.append("   ");
                } else if(board[i][j] == (int) 'X' || board[i][j] == (int) 'O') {
                    builder.append(" " + (char) board[i][j] + " ");
                } else {
                    builder.append(" " + board[i][j] + " ");
                }

                if(j < BOARDSIZE -1) {
                    builder.append("|");
                }
            }
            if(i < BOARDSIZE -1) {
                builder.append("\n-----------\n");
            }
        }
        System.out.println(builder.toString());
    }

    /***
     * checking board having empty places or not?
     * @return
     */
    @Override
    public boolean isBoardFull() {
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if(board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * single play mode enabling to play with AI based computer
     * @param singlePlayerMode
     */
    @Override
    public void setPlayerMode(boolean singlePlayerMode) {
        isSinglePlayerMode = singlePlayerMode;
    }

    @Override
    public boolean isSinglePlayer() {
        return isSinglePlayerMode;
    }

    /***
     * function will return the current player.
     * @return
     */
    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    /***
     * this funcation will switch the players
     * @return int playerId
     */
    @Override
    public int switchPlayer() {
        if(getCurrentPlayer() == Players.PLAYER_1) {
            currentPlayer = isSinglePlayer() ? Players.COMPUTER : Players.PLAYER_2;
        } else if(getCurrentPlayer() == Players.PLAYER_2) {
            currentPlayer = isSinglePlayer() ? Players.COMPUTER : Players.PLAYER_1;
        } else {
            currentPlayer = Players.PLAYER_1;
        }
        return currentPlayer;
    }

    /***
     * To check if any player completed row or column or diagonal places
     * @return boolean win status (true / false)
     */

    @Override
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColsForWin() || checkDigonalsForWin());
    }

    /***
     * checking rows for winning case
     * @return
     */
    protected boolean checkRowsForWin() {
        for (int i = 0; i < BOARDSIZE; i++) {
            if (gameStrategyMatch(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    /***
     * checking columns for winning case
     * @return
     */
    protected boolean checkColsForWin() {
        for (int i = 0; i < BOARDSIZE; i++) {
            if (gameStrategyMatch(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    /***
     * checking diogonals for winning case.
     * @return
     */

    protected boolean checkDigonalsForWin() {
        return (gameStrategyMatch(board[0][0], board[1][1], board[2][2]) || gameStrategyMatch(board[0][2], board[1][1], board[2][0]));
    }

    /***
     * actual game strategy.
     * @return boolean gamestrategy (true / false)
     */
    protected abstract boolean gameStrategyMatch(int a, int b, int c);
}
