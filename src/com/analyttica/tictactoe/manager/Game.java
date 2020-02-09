package com.analyttica.tictactoe.manager;

/***
 * interface to build board games like tic-tac-toe models games
 */
public interface Game {
    int BOARDSIZE = 3;

    void initializeBoard();
    void displayBoard();
    boolean isBoardFull();

    boolean checkForWin();

    int switchPlayer();
    void setPlayerMode(boolean singlePlayer);
    boolean isSinglePlayer();
    int getCurrentPlayer();
    String getUserValue();

    boolean placeSign(int xPosition, int yPosition, int value);
    void placeSignByComputer();


}
