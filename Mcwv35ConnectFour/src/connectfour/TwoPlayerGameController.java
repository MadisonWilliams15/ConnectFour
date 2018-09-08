/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madi
 */
public class TwoPlayerGameController extends Switchable implements Initializable  {

   @FXML
   ImageView board;
   @FXML
   private AnchorPane vizPane;
   @FXML
   private Label label;
   Stage stage;
   TwoPlayerGameModel twoPlayerGame;
   Ellipse[][] ellipses;
   public int winner;
   static int player=1;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        twoPlayerGame= new TwoPlayerGameModel();
        createEllipses();
        label.setText("Player 1 Turn");
        label.setTextFill(Color.RED);
        twoPlayerGame.createBoard();
        winner=0;
    }    

    public void ready(Stage stage) {
            this.stage = stage;
    }
    
    @FXML
    private void OnePlayerPressed(ActionEvent event) {
        Alert alertBox= new Alert(Alert.AlertType.INFORMATION);
            alertBox.setTitle("About");
            alertBox.setHeaderText(null);
            alertBox.setContentText("Object:\n" +
            "To win Connect Four you must be the first player to get four of your colored"
            + " checkers in a row either horizontally, vertically or diagonally."
            + "To make a move click anywhere in the column that you would like to place your marker, then the "
            + "computer will make a move. ");
            
            alertBox.show();
    }

    @FXML
    private void TwoPlayersPressed(ActionEvent event) {
        Alert alertBox= new Alert(Alert.AlertType.INFORMATION);
            alertBox.setTitle("About");
            alertBox.setHeaderText(null);
            alertBox.setContentText("Object:\n" +
            "To win Connect Four you must be the first player to get four of your colored"
            + " checkers in a row either horizontally, vertically or diagonally."
            + "To make a move click anywhere in the column that you would like to place your marker, then let"
                    + " the other player do the same. ");
            
            alertBox.show();
    }

    @FXML
    private void AboutPressed(ActionEvent event) {
        Alert alertBox= new Alert(Alert.AlertType.INFORMATION);
            alertBox.setTitle("About");
            alertBox.setHeaderText(null);
            alertBox.setContentText("My name is Madison Williams. This program was written for my CS 3330 Final Project. "
                    + "I am a sophomore at the University of Missouri and my major is Computer Science. "
                    + "Connect Four was one of my favorite games growing up, so I though it would be fun to write a program "
                    + "that lets users play Connect Four against a friend or the computer. "
                    + "Good Luck! Hope you enjoy!");
            
            alertBox.show();
    }

    @FXML
    private void newGamePressed(ActionEvent event) {
        twoPlayerGame= new TwoPlayerGameModel();
        createEllipses();
        twoPlayerGame.createBoard();
        label.setText("Player 1 Turn");
        label.setTextFill(Color.RED);
        winner=0;
        player=1;
    }

    @FXML
    private void MenuPressed(ActionEvent event) {
        Switchable.switchTo("StartPage");
    }

    public void createEllipses()
    {
        ellipses = new Ellipse[6][7];
        
        for(int x=0; x<6; x++){
            for(int y=0; y<7; y++){
               
                
                Ellipse ellipse = new Ellipse();
                ellipse.setRadiusX(30);
                ellipse.setRadiusY(30);  
                ellipse.setFill(Color.WHITE);  
                
                if(x==0){
                  
                    ellipse.setCenterY(137); 
                }
                else if(x==1){   
               ellipse.setCenterY(204);
                }
                else if(x==2){
                ellipse.setCenterY(268);
                }
                else if(x==3){
                ellipse.setCenterY(334);
                }
                else if(x==4){
                ellipse.setCenterY(398);
                }
                else if(x==5){
                ellipse.setCenterY(464);
                }
                else if(x==6){
                ellipse.setCenterY(520);
                }
                
                
                if(y==0){
                    ellipse.setCenterX(83);
                }
                else if(y==1){
                    ellipse.setCenterX(158);
                }
                else if(y==2){
                    ellipse.setCenterX(230);
                }
                else if(y==3){
                    ellipse.setCenterX(305);
                }
                else if(y==4){
                    ellipse.setCenterX(376);
                }
                else if(y==5){
                    ellipse.setCenterX(451);
                }
                else if(y==6){
                    ellipse.setCenterX(525);
                       
                }
                                
                
                vizPane.getChildren().add(ellipse);
                ellipses[x][y]=ellipse;
                
                
            }
        }
          
       
    }

    @FXML
    private void AnchorPressed(MouseEvent event) {
        if(winner==0){
        int col=-1;
        int row=-1, winner=-1;
        double x=event.getX();
        double y=event.getY();
        if(x>53 && x<112)
        {
            col=0;
        }
        else if(x>129 && x<188){
            col=1;
        }
        else if(x>200 && x<268){
            col=2;
        }
        else if(x>275 && x<335){
            col=3;
        }
        else if(x>346 && x<405){
            col=4;
        }
        else if(x>421 && x<482){
            col=5;
        }
        else if(x>495 && x<555){
            col=6;
        }
        
        if(col!=-1){
            update(col);
        }//if the click was in bounds
        }
    }
    
    public void update(int col){
        if(twoPlayerGame.isTie()==1)
        {
            label.setText("Tie");
            label.setTextFill(Color.GREEN);
        }
        else{
        int row = twoPlayerGame.findOpenRow(col);
        if(row==-1){
            if(player==1){
                label.setText("Player 1 That Column is Full!");
               label.setTextFill(Color.RED);
            }
            else{
                label.setText("Player 2 That Column is Full!");
               label.setTextFill(Color.ORANGE);
            }
            
        }
        else
        {             
           winner = twoPlayerGame.turn(col, row, player);//call the turn function if column was available 
          
           
           if (player==1)
           {
               ellipses[row][col].setFill(Color.RED);
               label.setText("Player 2 Turn");
               label.setTextFill(Color.ORANGE);
               player = 2;
           }
           else{
               ellipses[row][col].setFill(Color.YELLOW);
               label.setText("Player 1 Turn");
               label.setTextFill(Color.RED);
               player = 1;
           }
           
           
           
           if(winner==1){
            //player 1 wins
            label.setText("Player 1 Wins!");
            label.setTextFill(Color.RED);
        }
        else if(winner ==2){
            //player 2 wins
            label.setText("Player 2 Wins!");
            label.setTextFill(Color.ORANGE);
        }
        } 
        }
    }  

    @FXML
    private void ResumeGamePressed(ActionEvent event) {
    /*
        This code is from the serialization example done in class. It allows the user to resume a game that was saved to the file. 
    */
        if(twoPlayerGame == null){
            return;
            
        }
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try{
                FileInputStream fileIn = new FileInputStream(file.getPath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                OnePlayerGameModel game = new OnePlayerGameModel();
                game = (OnePlayerGameModel) in.readObject();
                in.close();
                fileIn.close();
                fillBoard(game);
                twoPlayerGame.board=game.board;
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @FXML
    private void SaveGamePressed(ActionEvent event) {
    /*
        This code is from the serialization example done in class. It allows the user to save a game to continue it later. 
    */    
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
             try {
                if(!file.getName().endsWith(".ser")){
                    file = new File(file.getPath()+ ".ser");
                }
                FileOutputStream fileOut = new FileOutputStream(file.getPath());
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(this.twoPlayerGame);//does serialization
                out.close();
                fileOut.close();
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }     
        
    }

    private void fillBoard(OnePlayerGameModel game) {//update the ellipses to match the saved game board
        createEllipses();
        winner=0;
        player=1;
        label.setText("Player 1 Turn");
        label.setTextFill(Color.RED);
        for(int x=0; x<6; x++)
        {
            for(int y=0; y<7; y++)
            {
                
                if(game.board[x][y]==1)
                {
                    ellipses[x][y].setFill(Color.RED);
                }
                else if(game.board[x][y]==2)
                {
                    ellipses[x][y].setFill(Color.YELLOW);
                }
            }
           
        }
    }

    @FXML
    private void undoPressed(ActionEvent event) {
        if(twoPlayerGame.colstack.isEmpty()==false && winner==0){
            int col=(int)twoPlayerGame.colstack.pop();
            int row=(int)twoPlayerGame.rowstack.pop();

            ellipses[row][col].setFill(Color.WHITE);

            twoPlayerGame.board[row][col]=0;
            if(player==1)
            {
                player=2;
                label.setText("Player 2 Turn");
                label.setTextFill(Color.ORANGE);
            }
            else{
                player=1;
                label.setText("Player 1 Turn");
                label.setTextFill(Color.RED);
            }
            }
    }
}
