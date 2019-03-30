/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.BCrypt;
import edu.bonplan.entities.FosUser;
import edu.bonplan.services.UserServices;
import edu.bonplan.util.Vars;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author darkz
 */
public class LoggingController implements Initializable {

    @FXML
    private TextField uname;
    @FXML
    private TextField mdp;
    @FXML
    private Button authen;
    @FXML
    private Menu connexion;
    @FXML
    private Button cc;
    @FXML
    private Menu inscription;
    @FXML
    private Button ii;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Loggin(ActionEvent event) throws SQLException {
        
        UserServices userser = new UserServices();
        String use = uname.getText();

        String pass = mdp.getText();

        FosUser user = userser.login(use);
        UserServices uu = new UserServices();
       

        if (user.getId() == 0) 
        {
            JOptionPane.showMessageDialog(null, "Nom d'utilisateur invalide");
        } 
        else 
        {
            if (BCrypt.checkpw(pass, user.getPassword()))
            {
                try 
                {
                    
                    Vars.current_choice=1;
                     Vars.current_user=uu.AfficherUser(user.getId());
                        
                        if(Vars.current_user.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
                        Vars.current_admin=1;
                        
                        else
                            Vars.current_admin=0;
                
                    Stage stage = new Stage();
                    Parent home = FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/Acceuil.fxml"));
                
                    Scene hoomescene = new Scene(home);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(hoomescene);
                    app_stage.show();
                }
                catch (IOException ex) 
                {
                Logger.getLogger(LoggingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Mot de passe invalide");
            }
        }
    }

    @FXML
    private void connexion(ActionEvent event) {
        try {
            Parent p1=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/logging.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p1);
            
            stage.setTitle("Connexion");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inscription(ActionEvent event) {
        try {
            Parent p2=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/Inscription.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p2);
            
            stage.setTitle("Inscription");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
