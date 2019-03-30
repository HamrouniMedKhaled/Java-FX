package edu.bonplan.gui.Enseigne;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
          Parent root=null;
        try{
         root = FXMLLoader.load(getClass().getResource("enseigneShow.fxml"));
        }catch (IOException e){
            System.out.println("erreur dans la generation du root");
        }
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}