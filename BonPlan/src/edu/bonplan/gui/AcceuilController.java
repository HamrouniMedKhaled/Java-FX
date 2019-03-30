/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.util.Vars;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamrouni
 */
public class AcceuilController implements Initializable {

    @FXML
    private Menu connexion;
    @FXML
    private Menu inscription;
    @FXML
    private Menu logout;
    @FXML
    private Menu acceuil;
    @FXML
    private Menu plan;
    @FXML
    private Menu deal;
    @FXML
    private ImageView b1;
    private Image img;
    @FXML
    private Button cc;
    @FXML
    private Button ii;
    @FXML
    private Button dc;
    @FXML
    private Button aa;
    @FXML
    private Button pp;
    @FXML
    private Button dd;
    @FXML
    private Button enseigne;
    @FXML
    private Menu afficherenseigne;
    @FXML
    private Menu Reservation;
    @FXML
    private Button rr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        img = new Image("file:///C:\\wamp64\\www\\BonPlan\\Projet_pidev\\web\\uploads\\images\\aceuil.png");
        b1.setImage(img);
        if (Vars.current_user==null)
                {
                    logout.setVisible(false);
                    acceuil.setVisible(false);
                    plan.setVisible(false);
                    deal.setVisible(false);
                    enseigne.setVisible(false);
                    Reservation.setVisible(false);
                }
        else
        {
            connexion.setVisible(false);
            inscription.setVisible(false);
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

    @FXML
    private void logout(ActionEvent event) {
        try {
            Vars.current_user=null;
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/Acceuil.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Acceuil");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void acceuil(ActionEvent event) {
        try {
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/Acceuil.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Acceuil");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherplan(ActionEvent event) {
        try {
            Vars.current_choice=1;
            Parent p5=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p5);
            
            stage.setTitle("Liste des plans");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void afficherdeal(ActionEvent event) {
        try {
            Vars.current_choice=1;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("liste des deals");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void afficherEnseigne(ActionEvent event) {
        try {
            Parent p7=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/Enseigne/enseigneShow.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p7);
            
            stage.setTitle("liste des enseigne");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reservation(ActionEvent event) {
        
            try {
            Parent p10=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservation.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p10);
            
            stage.setTitle("Afficher Reservation");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
