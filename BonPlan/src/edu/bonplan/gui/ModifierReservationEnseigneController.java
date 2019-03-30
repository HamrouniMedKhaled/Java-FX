/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Deal;
import edu.bonplan.entities.Reservation;
import edu.bonplan.services.DealServices;
import edu.bonplan.services.ReservationEnseigneService;
import edu.bonplan.util.Vars;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yassine
 */
public class ModifierReservationEnseigneController implements Initializable {
    
    @FXML
    private TextField nbrePlace;
    @FXML
    private DatePicker date_reservation_Enseigne;
    @FXML
    private Button modifier_reservation;
    private Reservation r;
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
              // TODO
              ReservationEnseigneService re = new ReservationEnseigneService();
        r=re.afficher(Vars.reservationEnseigne.getId());
           // date_reservation_Enseigne.setValue(r.getDatereservation());
            nbrePlace.setText(String.valueOf(r.getNbplaces()));
    }    
    
    @FXML
    private void ModifierReservationEnseigne(ActionEvent event) throws IOException {
       
              
        ReservationEnseigneService re = new ReservationEnseigneService();
        
            
        LocalDate dr =date_reservation_Enseigne.getValue();
        Date datef = Date.from(dr.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date dd = new java.sql.Date(datef.getTime());
       
        Vars.reservationEnseigne.setDatereservation(dd);
        Vars.reservationEnseigne.setNbplaces(Integer.parseInt(nbrePlace.getText()));
            
        re.ModifierReservationEnseigne(Vars.reservationEnseigne);
        Parent p10=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservation.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p10);
            
            stage.setTitle("Afficher Reservation");
            stage.setScene(scene);
            stage.show();
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
    private void connexion(ActionEvent event) {
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
