package com.analyttica.tictactoe;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGame extends AbstractGame {

    private List<Integer> oddNumbers = new ArrayList<>();
    private List<Integer> evenNumbers = new ArrayList<>();
    private Random random = new Random();

    public NumberGame() {
        initializeBoard();
        oddNumbers.add(1);
        oddNumbers.add(3);
        oddNumbers.add(5);
        oddNumbers.add(7);
        oddNumbers.add(9);

        evenNumbers.add(2);
        evenNumbers.add(4);
        evenNumbers.add(6);
        evenNumbers.add(8);

    }

    @Override
    public boolean placeSign(int xPosition, int yPosition, int value) {
        if (xPosition >= 0 && xPosition < BOARDSIZE  && (yPosition >=0 && yPosition < BOARDSIZE)) {
            if (board[xPosition][yPosition] == 0) {
                if (value >= 1 && value <= 9 && !isValueExists(value) && isValidPlayerOption(value)) {
                    board[xPosition][yPosition] = value;
                    if(currentPlayer != Players.COMPUTER) {
                        evenNumbers.remove(new Integer(value));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPlayerOption(int value) {
        if(isSinglePlayer()) {
            return (value % 2 == (currentPlayer == Players.COMPUTER ? 1 : 0));
        } else {
            return (value % 2 == (currentPlayer == Players.PLAYER_1 ? 1 : 0));
        }
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
        return (a != 0 && b != 0 && c != 0) && (a + b + c) == 15;
    }

    @Override
    public void placeSignByComputer() {
        if(oddNumbers.size() == 5) {
            board[0][0] = random.nextInt(5);
            oddNumbers.remove(new Integer(board[0][0]));
        } else {
            int bestScore = -1000;
            int bestRow = 0;
            int bestCol = 0;
            int bestValue = 0;
            List<Integer> remainOdd = new ArrayList<>(oddNumbers);
            List<Integer> remainEven = new ArrayList<>(evenNumbers);
            for (Integer value : oddNumbers) {
                for (int i = 0; i < BOARDSIZE ; i++) {
                    for (int j = 0; j < BOARDSIZE; j++) {
                        if(board[i][j] == 0) {
                            board[i][j] = value;
                            remainOdd.remove(value);
                            int score = bestMove(false, remainOdd, remainEven);
                            if(score > bestScore) {
                                bestScore = score;
                                bestRow = i;
                                bestCol = j;
                                bestValue = value;
                            }
                            board[i][j] = 0;
                        }
                    }
                }
            }
            board[bestRow][bestCol] = bestValue;
            oddNumbers.remove(new Integer(bestValue));
        }

    }

    private int bestMove(boolean isMax, List<Integer> remainOdd, List<Integer> remainEven) {
        boolean isWon = checkForWin();
        if(isWon) {
            return winningScore[isMax ? Players.PLAYER_1 : Players.COMPUTER];
        } else if(isBoardFull()) {
            return 0;
        }
        if(isMax) {
            int best = -1000;
            //System.out.println("remainOdd====> " + remainOdd);
            for (Integer value : remainOdd) {
                for (int i = 0; i < BOARDSIZE; i++) {
                    for (int j = 0; j < BOARDSIZE; j++) {
                        if(board[i][j] == 0) {
                            board[i][j] = value;
                            int score = bestMove(false, remainOdd.subList(remainOdd.indexOf(value), remainOdd.size() - 1), remainEven);
                            board[i][j] = 0;
                            best = Math.max(score, best);
                        }
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (Integer value : remainEven) {
                for (int i = 0; i < BOARDSIZE; i++) {
                    for (int j = 0; j < BOARDSIZE; j++) {
                        if(board[i][j] == 0 && !remainEven.isEmpty()) {
                            board[i][j] = value;
                            int score = bestMove(true, remainOdd, remainEven.subList(remainEven.indexOf(value), remainEven.size() - 1));
                            board[i][j] = 0;
                            best = Math.min(score, best);
                        }
                    }
                }
            }
            return best;
        }
    }
}
