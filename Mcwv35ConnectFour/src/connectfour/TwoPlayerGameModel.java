/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import java.util.Stack;

/**
 *
 * @author Madi
 */
public class TwoPlayerGameModel implements GameInterface {
    int [][] board = new int[6][7];
    public Stack rowstack;
    public Stack colstack;
    public TwoPlayerGameModel(){
        createBoard();
        
    }
   
    
    @Override
    public void createBoard() {
        rowstack = new Stack();
        colstack = new Stack();
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
        //return -1 for full column, 0 for no winner, 1 for p1 win, 2 for p2 win
       
        //make move
        board[row][column]=player;
        rowstack.push(row);
        colstack.push(column);
        if(victory(player, row, column)==0)//check if there is a winner
        {
            return 0;
        }
        else{
            return player;
        }
        
        
        
    }
    
    public int findOpenRow(int column){
    if(board[0][column]!=0)
        {
            return -1;
        }//if column is full return -1
        
        //if not full then find available row in column
        int row=5;
        while(board[row][column]!=0 && row > -1)
        {
            row--;
        }
        return row;
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
        if(diagonalWin(player)==1){
            System.out.println("DIAGONAL WIN");
            return player;
        }
        
        
        return 0; 
    }
    
    public int isTie(){
        for(int i=0; i<7; i++)
        {
            if(findOpenRow(i)!=-1)
            {
                return 0;
            }//if there is no open row for each column the game is tied
        }
        return 1;
    }
    
    int diagonalWin(int player){
      /*
        This function's main concept is from https://stackoverflow.com/questions/32770321/connect-4-check-for-a-win-algorithm
        I slightly modified the code to fit my program but did not write this logic by myself. 
        */
       
       // ascendingDiagonalCheck 
    for (int i=3; i<6; i++){
        for (int j=0; j<3; j++){
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
