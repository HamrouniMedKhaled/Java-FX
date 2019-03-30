/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Plan;
import edu.bonplan.services.PlanServices;
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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darkz
 */
public class AfficherPlanController implements Initializable {

    @FXML
    private ImageView imgd;
    @FXML
    private Label nbv;
    @FXML
    private Label categorie;
    @FXML
    private Label ajoutepar;
    @FXML
    private Button BtModif;
    @FXML
    private Button BtSupp;
    @FXML
    private Label description;
    @FXML
    private Label ajoutdate;
    @FXML
    private Label nbs;
    @FXML
    private Label titre;
    
    private Image img;
    
    private Plan p ;
    
    PlanServices ps = new PlanServices();
    @FXML
    private Button BtSignaler;
    @FXML
    private Menu logout;
    @FXML
    private Button dc;
    @FXML
    private Menu acceuil;
    @FXML
    private Button aa;
    @FXML
    private Menu plan;
    @FXML
    private Button pp;
    @FXML
    private Menu deal;
    @FXML
    private Button ddd;
    @FXML
    private Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Menu Reservation;
    @FXML
    private Button rr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        p=ps.AfficherPlan(Vars.current_plan.getId());
        titre.setText(p.getTitre());
        nbs.setText(String.valueOf(p.getReportnumber()));
        ajoutepar.setText(p.getUserId().getUsername());
        categorie.setText(p.getCategorieId().getNom());
        nbv.setText(String.valueOf(p.getScore()));
        description.setText(p.getDescription());
        ajoutdate.setText(String.valueOf(p.getDateAjout()));
        String hhh=p.getImageId().getAlt().substring(0,p.getImageId().getAlt().length()-1);
        
        img = new Image("http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/"+p.getImageId().getUrl()+".png");
        imgd.setImage(img);
        
        if (p.getUserId().getId()==Vars.current_user.getId())
        {
            BtModif.setVisible(true);
            BtSupp.setVisible(true);
            BtSignaler.setVisible(false);
            
            
            
            
        }
        else
        {
            BtModif.setVisible(false);
            BtSupp.setVisible(false);
            BtSignaler.setVisible(true);
        }
    }    

    @FXML
    private void modifier(ActionEvent event) {
        
        try {
            
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/ModifierPlan.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Modifier Plan");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        try {
            Vars.current_choice=2;
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            ps.SupprimerPlan(p.getId());
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Mes Plans");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Signaler(ActionEvent event) {
        try {
            ps.SignalerPlan(p.getId());
            BtSignaler.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherPlanController.class.getName()).log(Level.SEVERE, null, ex);
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
