package edu.bonplan.gui.Evenement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
          Parent root=null;
        try{
         root = FXMLLoader.load(getClass().getResource("evenementAdd.fxml"));
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