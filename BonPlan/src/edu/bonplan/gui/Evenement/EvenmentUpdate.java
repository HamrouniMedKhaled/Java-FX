package edu.bonplan.gui.Evenement;

import edu.bonplan.entities.Enseigne;
import edu.bonplan.entities.Evenement;
import edu.bonplan.entities.Image;
import edu.bonplan.entities.Menu;
import edu.bonplan.gui.AcceuilController;
import edu.bonplan.services.EnseigneService;
import edu.bonplan.services.EvenementService;
import edu.bonplan.util.Vars;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EvenmentUpdate implements Initializable {


    @FXML
    private Button btnsave;

    @FXML
    private TextField nom;

    @FXML
    private TextField desc;

    @FXML
    private DatePicker date;

    private ChoiceBox<String> enseigne;

    int id;
    @FXML
    private Menu acceuil;
    @FXML
    private Menu logout;
    @FXML
    private Button dc;
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
    private Menu Reservation;
    @FXML
    private Button rr;
    @FXML
    private Menu enseigne1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnsave.setOnAction(e->
        {

            ArrayList<String> enseignes = new ArrayList<>();
            EnseigneService enseigneService = new EnseigneService();
            List<Enseigne> listEn = enseigneService.getallEnseigne();

            Evenement event=new Evenement();
            event.setNom(nom.getText());
            event.setDescription(desc.getText());
            event.setImageId(new Image(1));
            LocalDate localDate = date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            event.setDate(sqlDate);
            for (Enseigne en : listEn) {
                if (en.getNom().equals(enseigne.getValue())) {
                    event.setEnseigneId(en);
                }
            }
            System.out.println(event.getDescription());
            EvenementService evenementService=new EvenementService();
            evenementService.update(event, id);

        });

    }

    public void setInfo(Evenement evenement)
    {System.out.println(evenement.getId());
        this.id=evenement.getId();
        nom.setText(evenement.getNom());
        desc.setText(evenement.getDescription());
            ArrayList<String> enseignes = new ArrayList<>();
            EnseigneService enseigneService = new EnseigneService();
            List<Enseigne> listEn = enseigneService.getallEnseigne();
            for (Enseigne ee : listEn) {
                enseignes.add(ee.getNom());
            }
            ObservableList<String> list = FXCollections.observableArrayList(enseignes);
            System.out.println(list);

            enseigne.setItems(list);


    }

    private void Acceuil(ActionEvent event) {
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
            Vars.current_choice=2;
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
            Parent p8=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservation.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p8);
            
            stage.setTitle("Afficher Reservation");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
