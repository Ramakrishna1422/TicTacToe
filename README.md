# TicTacToe
Command Line Game.

## Problem Statement
Design a tic-tac-toe game that has two modes.

### 1.Pattern based
First user places X on any position and the second player places O
The first player to get his a row, column or diagonal filled with his pattern wins.

```
 X | X |   
-----------
   | O |   
-----------
   | X |   
````

### 2.Number based
First user places any of the odd numbers from 1 to 9 and the second player uses even numbers from 2 to 8
First player to get a sum of 15 on any row, column or diagonal wins (The row or column or diagonal can contain numbers entered by other player as well). Each digit should be entered only once and cannot be repeated

```
 6 | 3 |   
-----------
   | 1 |   
-----------
   | 4 |   
````

## Player Mode
Two player modes available. 
1. Single Player (Computer vs Player 1)
2. Multi Player (Player 1 vs Player 2)


To complie, go to project dierctory and run below command in terminal
```
mkdir bin
javac -d bin @sources.txt
```

To run the application execute below commands in terminal,
```
cd bin
java com.analyttica.GameApplication
```

#Sample Output:
```
TicTocToe !!!
1. Pattern Game
2. Number Game
Enter your choice: 1
1. Single Player
2. Multi Player
Enter your choice: 1
   |   |   
-----------
   |   |   
-----------
   |   |   

Computer turn !

 X |   |   
-----------
   |   |   
-----------
   |   |   
Player 1 turn !

Enter x & y positions to place O:1 1
 X |   |   
-----------
   | O |   
-----------
   |   |   
Computer turn !

 X | X |   
-----------
   | O |   
-----------
   |   |   
Player 1 turn !

Enter x & y positions to place O:0 2
 X | X | O 
-----------
   | O |   
-----------
   |   |   
Computer turn !

 X | X | O 
-----------
   | O |   
-----------
 X |   |   
Player 1 turn !

Enter x & y positions to place O:1 0
 X | X | O 
-----------
 O | O |   
-----------
 X |   |   
Computer turn !

 X | X | O 
-----------
 O | O | X 
-----------
 X |   |   
Player 1 turn !

Enter x & y positions to place O:2 0
Invalid selection. Please try again
 X | X | O 
-----------
 O | O | X 
-----------
 X |   |   
Player 1 turn !

Enter x & y positions to place O:2 1
 X | X | O 
-----------
 O | O | X 
-----------
 X | O |   
Computer turn !

 X | X | O 
-----------
 O | O | X 
-----------
 X | O | X 
:( Game tie!
Do you want play again (y/n) : n
```
