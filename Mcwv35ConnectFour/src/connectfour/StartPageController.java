/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import static java.awt.SystemColor.text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * FXML Controller class
 *
 * @author Madi
 */
public class StartPageController extends Switchable implements Initializable {


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    private void OnePlayer(ActionEvent event) {
        Switchable.switchTo("Game");
        
        
    }

    @FXML
    private void TwoPlayers(ActionEvent event) {
        Switchable.switchTo("TwoPlayerGame");
    }

    @FXML
    private void ResumeGame(ActionEvent event) {
        //pull data from file and resume game
        
    }

    @FXML
    private void OnePlayerPressed(ActionEvent event) {
        Alert alertBox= new Alert(AlertType.INFORMATION);
            alertBox.setTitle("About");
            alertBox.setHeaderText(null);
            alertBox.setContentText("Object:\n" +
            "To win Connect Four you must be the first player to get four of your colored"
            + " checkers in a row either horizontally, vertically or diagonally."
            + "To make a move_____");
            
            alertBox.show();
    }

    @FXML
    private void TwoPlayerPressed(ActionEvent event) {
        Alert alertBox= new Alert(AlertType.INFORMATION);
            alertBox.setTitle("About");
            alertBox.setHeaderText(null);
            alertBox.setContentText("Object:\n" +
            "To win Connect Four you must be the first player to get four of your colored"
            + " checkers in a row either horizontally, vertically or diagonally."
            + "To make a move_____");
            
            alertBox.show();
    }

    @FXML
    private void AboutPressed(ActionEvent event) {
        Alert alertBox= new Alert(AlertType.INFORMATION);
            alertBox.setTitle("About");
            alertBox.setHeaderText(null);
            alertBox.setContentText("My name is Madison Williams. This program was written for my CS 3330 Final Project. "
                    + "I am a sophomore at the University of Missouri and my major is Computer Science. "
                    + "Connect Four was one of my favorite games growing up, so I though it would be fun to write a program "
                    + "that lets users play Connect Four against a friend or the computer."
                    + "Good Luck! Hope you enjoy!");
            
            alertBox.show();
    }
    
    
    
}
