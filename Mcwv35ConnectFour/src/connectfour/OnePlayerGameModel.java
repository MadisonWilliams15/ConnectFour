/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;


/**
 *
 * @author Madi 
 */
public class OnePlayerGameModel implements GameInterface, Serializable {
     int [][] board = new int[6][7];
    
    public OnePlayerGameModel(){
        createBoard();
        
    }
   
    
    @Override
    public void createBoard() {
        for(int x=0; x<6; x++)
        {
            for(int y=0; y<7; y++)
            {
                board[x][y]=0;
            }
        }
    }
    

    @Override
    public int turn(int column, int row, int player) {
        //returns -1 for full column, 0 for no winner, 1 for p1 win, 2 for p2 win
       
        //make move/place marker
        board[row][column]=player;
        
        if(victory(player, row, column)==0)//check if there is a winner
        {
            return 0;
        }
        else{
            return player;
        }
        
        
        
    }
    
    public int findOpenRow(int column){
    if(this.board[0][column]!=0)
        {
            return -1;
        }//if the column is full return -1
        
        //else find available row in column
        int row=5;
        while(board[row][column]!=0 && row > -1)
        {
            row--;
        }
        return row;
    }

    
    public int computerTurn() {
        /*This logic details were all written by me, but the idea of making productive moves instead of 
        random moves as well as a general logic outline was provided by professor Wergeles. Once 
        I finished adding this logic in place of making random moves I had trouble beating 
        the moves made by this function in one player mode*/
        
        int row=-1;
        for(int x=0; x<7; x++)//make a move to block player 1 from winning if possible
        {
            row=findOpenRow(x);
            if(row!=-1)//if there is room in this column, make a move for player 1 here and see if that would result in a win for player 1
            {
                board[row][x]=1;
                if(victory(1, row, x)==1){//if going here would result in a player 1 win, this is where cpu wants to go to prevent the win
                    board[row][x]= 0;//reset this spot, it will be set later
                    return x;
                }
                board[row][x]=0;//if this space didn't result in victory reset it to 0
            }
        }
        
        for(int x=0; x<7; x++)//make a move that will result in a player 2 win if possible
        {
            row=findOpenRow(x);
            if(row!=-1)//if there is room in this column, make a move for player 2 here and see if there is victory
            {
                board[row][x]=2;
                if(victory(2, row, x)==2){//if going here would result in a player 2 win this is where cpu wants to go
                    board[row][x]= 0;//reset this spot
                    return x;
                }
                board[row][x]=0;//if this space didnt result in victory reset it to 0
            }
        }
        
        //no moves blocked player 1 from winning or made player 2 win
        //if we've made it here without returning the cpu move just pick a random column in an open row and return it
        Random rand = new Random();
        int move = rand.nextInt(7); 
        while(findOpenRow(move)==-1)//generate new numbers while the previous numbers column was full
        {
            move = rand.nextInt(7); 
        }
        
        return move;
    }
    
    public int isTie(){//call findOpenRow for each column. If any column has an open row return 0, it it is a tie return 1
        for(int i=0; i<7; i++)
        {
            if(findOpenRow(i)!=-1)
            {
                return 0;
            }
        }
        return 1;
    }
    
    @Override
    public int victory(int player, int row, int col) {
        int count=0;
       
        //check for 4 in a row horizontally
        for(int x=0; x<7; x++)
        {
            if(board[row][x]==player)
            {
                count++;
            }
            
            if(count==4)
            {
                return player;
            }
            
            if(board[row][x]!=player)
            {
                count=0;
            }
        }
        
        count = 0;
        //check for 4 in a row vertically
        for(int y=0; y<6; y++)
            {
                if(board[y][col]==player)
                {
                    count++;
                }
            
                if(count==4)
                {
                    return player;
                }
            
                if(board[y][col]!=player)
                {
                    count=0;
                }
            }
        
        if(diagonalWin(player)==1){//check if there is a win diagonally
            return player;
        }
    
        
        return 0; 
    }
    


   int diagonalWin(int player){  
       /*
        This function's main concept is from https://stackoverflow.com/questions/32770321/connect-4-check-for-a-win-algorithm
        I slightly modified the code to fit my program but did not write this logic by myself. 
        */
       
       // ascendingDiagonalCheck 
    for (int i=3; i<6; i++){
        for (int j=0; j<3; j++){//5 not 3
            if (this.board[i][j] == player && this.board[i-1][j+1] == player && this.board[i-2][j+2] == player && this.board[i-3][j+3] == player)
                return 1;
        }
    }
    // descendingDiagonalCheck
    for (int i=3; i<6; i++){
        for (int j=3; j<7; j++){
            if (this.board[i][j] == player && this.board[i-1][j-1] == player && this.board[i-2][j-2] == player && this.board[i-3][j-3] == player)
                return 1;
        }
    }
    return 0;
   }
       
}  
