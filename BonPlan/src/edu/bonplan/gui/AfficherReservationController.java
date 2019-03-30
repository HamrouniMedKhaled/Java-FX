/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Reservation;
import edu.bonplan.services.ReservationEnseigneService;
import edu.bonplan.util.Vars;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamrouni
 */
public class AfficherReservationController implements Initializable {

    private ObservableList<Pane> rr = FXCollections.observableArrayList();
    private ReservationEnseigneService rs = new ReservationEnseigneService();
    @FXML    
    private ListView<Pane> gr;    
    
    private List<Reservation> maliste;
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
    /**
     * Initializes the controller class.
     */
    public Pane createPane(Reservation rd ){
        
        
        Pane p = new Pane();
        
        Label nom = new Label();
        Label place = new Label();
        Label date = new Label();
        Button supp =new Button("Suprimer");
        supp.setLayoutX(150);
        supp.setLayoutY(150);
        supp.setVisible(true);
        supp.setOnAction( (ActionEvent e) -> {try {
            rs.SupprimerReservationEnseigne(rd.getId());
            Parent p8=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservation.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p8);
            
            stage.setTitle("Afficher reservation");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
        Button modif =new Button("Modifier");
        modif.setLayoutX(250);
        modif.setLayoutY(150); 
        modif.setOnAction((ActionEvent e) -> {try {
            Vars.reservationEnseigne=new Reservation(rd.getId());
            Vars.reservationEnseigne.setNbplaces(rd.getNbplaces());
            Vars.reservationEnseigne.setDatereservation(rd.getDatereservation());
            
            Parent p8=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/ModifierReservationEnseigne.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p8);
            
            stage.setTitle("Afficher reservation");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        });
        
        nom.setText(rd.getEnseigneId().getNom());
        nom.setWrapText(false);
        nom.setPrefSize(435, 89);
        nom.setLayoutX(150);
        nom.setLayoutY(1);
        nom.setMaxWidth(435);
        
        
        place.setText("Nombre de places : "+String.valueOf(rd.getNbplaces()));
        place.setWrapText(false);
        place.setPrefSize(435, 89);
        place.setLayoutX(150);
        place.setLayoutY(50);
        place.setMaxWidth(435);
        
        date.setText("Date de reservation : "+String.valueOf(rd.getDatereservation()));
        date.setWrapText(false);
        date.setPrefSize(435, 89);
        date.setLayoutX(150);
        date.setLayoutY(100);
        date.setMaxWidth(435);
        if (Vars.current_admin==1)
        {
            modif.setVisible(false);
            supp.setVisible(false);
        }
        
        gr.setItems(rr);
        
        p.getChildren().add(nom);
        p.getChildren().add(place);
        p.getChildren().add(date);
        p.getChildren().add(supp);
        p.getChildren().add(modif);
        
        return p;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        
        maliste=rs.AfficherMesReservation(Vars.current_user);
        maliste.forEach(r->{
            
                Pane p = new Pane();
                p = createPane(r);
                rr.add(p);
            });
    } catch (SQLException ex) {
        Logger.getLogger(AfficherReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
