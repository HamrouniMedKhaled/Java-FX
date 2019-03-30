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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author darkz
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmpass;
    @FXML
    private Button inscri;
    @FXML
    private Button annuler;
    @FXML
    private ChoiceBox<String> choix;
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
        choix.getItems().add("Utilisateur");
        choix.getItems().add("Respensable");
    }    

    @FXML
    private void inscri(ActionEvent event) {
        
        FosUser user = new FosUser();
                String p1 = password.getText();
                String p2 = confirmpass.getText();

if (!p1.equals(p2)){
    JOptionPane.showMessageDialog(null, "Mot de passe invalise");
}else 
{
            try {
                UserServices userser = new UserServices();
                String p1crypte = BCrypt.hashpw(p1, BCrypt.gensalt());
                user.setUsername(username.getText());
                user.setUsernameCanonical(username.getText());
                user.setPassword(p1crypte);
                user.setEmail(email.getText());
                user.setEmailCanonical(email.getText());
                if (choix.getValue()=="Respensable")
                {
                    user.setRoles("a:1:{i:0;s:10:\"ROLE_OWNER\";}");
                }
                else
                {
                   user.setRoles("a:0:{}"); 
                }
                String r = userser.inscription(user);
                System.out.println(r);
                Vars.current_user=user;
                
                Stage stage = new Stage();
                Parent login = FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/Acceuil.fxml"));
                
                Scene loginscene = new Scene(login);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(loginscene);
                app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
}


    


    }

    @FXML
    private void Annuler(ActionEvent event) {
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
