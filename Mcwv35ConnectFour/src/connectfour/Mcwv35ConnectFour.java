/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Madi
 */
public class Mcwv35ConnectFour extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("TwoPlayerGame.fxml"));
        
        Parent root2 = (Parent)loader.load();
        Parent root3 = (Parent)loader2.load();
        HBox root = new HBox();
        root.setPrefSize(600, 600);
        root.setAlignment(Pos.CENTER);
        Text message = new Text("This is a failure!");
        message.setFont(Font.font(STYLESHEET_MODENA, 32));
        root.getChildren().add(message);
       
        
        GameController controller = (GameController)loader.getController();
        TwoPlayerGameController controller2 = (TwoPlayerGameController) loader2.getController();
        
        Scene scene = new Scene(root);
        Switchable.scene=scene;
        Switchable.switchTo("StartPage");
        stage.setScene(scene);
        stage.show();
        
        controller.ready(stage);
        controller2.ready(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
