package com.analyttica.tictactoe;


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

    boolean placeSign(int xPosition, int yPosition, int value);

    default int getUserValue() {
        return 0;
    }

    void placeSignByComputer();
}
