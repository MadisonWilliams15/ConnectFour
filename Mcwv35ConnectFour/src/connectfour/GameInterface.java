/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

/**
 *
 * @author Madi
 */
public interface GameInterface {
    
    public void createBoard();//create a 2D array
    public int turn(int column, int row, int player);//prompt player to make a move
    public int victory(int player, int row, int col);//check if there is a winner
    
    
    
}
