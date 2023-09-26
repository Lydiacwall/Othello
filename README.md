# **Othello**
 
## Introduction
  Othello, also known as Reversi, is a classic two-player strategy board game.We made it with one mode : play against the computer.  This project was created by **Lydia Wallace** , **Miltiadis Tsolkas** and **Harris Karakostas**. 
  


## The Game  
  The __primary goal__ of Othello is to have the majority of your colored discs on the game board when the game ends. The game board consists of an 8x8 grid, and each player starts with two discs of their color placed in the center.

- Players take turns placing one of their discs on the board. They must place their disc adjacent to an opponent's disc in such a way that it "sandwiches" one or more of the opponent's discs between two of their own.

- When a player successfully makes such a move, all the sandwiched opponent discs between the two newly placed discs are flipped to the player's color.

- Players continue to take turns, and the game continues until the entire board is filled or no legal moves are left.

The game __ends__ when there are no legal moves left or the board is completely filled. The player with the most discs of their color on the board wins.

## Details
  The computer chooses the move by a Min-Max algorithm.You input the depth of the tree in the start of the game. Also you can choose if you want to play first or second.

## A glimpse of the game 
  
  ```
  Type please the max depth.

  X
 
 Do you want to play first? Type YES or NO.

 Yes
 
***************** 
  0 1 2 3 4 5 6 7 
0 - - - - - - - - 
1 - - - - - - - - 
2 - - - - - - - - 
3 - - - W B - - - 
4 - - - B W - - - 
5 - - - - - - - - 
6 - - - - - - - - 
7 - - - - - - - - 
***************** 
black plays       
Type row
X
Type column 
Y
***************** 
```
