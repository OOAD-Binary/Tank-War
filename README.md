# Tank-War
OOAD Assignment.

## Group Members
1. Khor Kia Kin
2. Ng Chin Ann
3. Low Pau Ek

## Question
The playing field is a 10X10 grid. The computer-programmed robot starts in the top left square, while the human-player’s robot starts in the bottom right square.

The robots can only move up, down, left or right, and shoot up, down, left or right. The program sequence is 15 of these moves.

At the start of the game, the computer generates a new random sequence of commands for the computer’s robot. The human then has to select 15 commands for the human robot. Then the round starts, and the robots run their respective sequences, animated on the screen.

If the human’s robot manages to kill the computer’s robot, then the human has won the game. If not, then the human has to write a new sequence to try to do that.

The aim of the game is for the human to take the least number of tries before their robot manages to kill the computer’s robot. So, for example, if it only takes the human 3 tries, it’s a better result than if it takes the human 10 tries.

## Instructions
1. To compile the .java files:
 ```
 javac –d bin model/*.java
 javac –d bin view/*.java
 javac –d bin controller/*.java
 javac –d bin Main.java
 ```

2. To run the binary file:
 ```
 java -cp bin Main
 ```

Alternatively, Windows user can run `compile.bat` and `run.bat`.

## Screenshots
![Screenshot](https://github.com/OOAD-Binary/Tank-War/blob/master/screenshot.png)
