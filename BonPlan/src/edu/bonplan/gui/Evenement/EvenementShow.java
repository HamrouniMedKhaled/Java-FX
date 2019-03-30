package edu.bonplan.gui.Evenement;

import edu.bonplan.entities.Enseigne;
import edu.bonplan.entities.Evenement;
import edu.bonplan.gui.AcceuilController;
import edu.bonplan.gui.Enseigne.ReadEnseigne;
import edu.bonplan.services.EvenementService;
import edu.bonplan.util.Vars;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class EvenementShow implements Initializable {



    @FXML
    private TableView<Evenement> tableEvent;

    @FXML
    private TableColumn<String, ?> idColmn;

    @FXML
    private TableColumn<String, ?> nomColumn;

    @FXML
    private TableColumn<String, ?> descColumn;

    @FXML
    private TableColumn<String, ?> dateColumn;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnFind;

    @FXML
    private TableColumn<?, ?> delcol;

    @FXML
    private TableColumn<?, ?> upcol;
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
    private Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Menu Reservation;
    @FXML
    private Button rr;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildTabel();
        btnFind.setOnAction(e->{
            String search=txtSearch.getText();
            if (search.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setHeaderText("Information Alert");
                ;
                alert.setContentText("find somthing to search");
                alert.show();
            }
            else {
                ObservableList<Evenement> datas = FXCollections.observableArrayList();
                EvenementService ens = new EvenementService();

                Evenement en = ens.getEventByNom(search);
                datas.add(en);


                buildTabel(datas);
            }

        });
    }

    public void buildTabel(ObservableList<Evenement> data)
    {
        idColmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        delcol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        upcol.setCellValueFactory(new PropertyValueFactory<>("Update"));


        tableEvent.getItems().clear();
        tableEvent.getItems().addAll(data);


        tableEvent.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Evenement> observable,
                        Evenement oldValue,
                        Evenement newValue) -> {
                    if (newValue == null) {
                        return;
                    }

                    Parent parent = null;
                    FXMLLoader loader = null;
                    try {
                        loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/edu/bonplan/gui/Evenement/evenementRead.fxml"));

                        parent = (Parent) loader.load();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    EvenementRead display = loader.getController();
                    System.out.println(newValue.getId()+"aa");
                    EvenementService evenementService=new EvenementService();

                    display.setInfo(evenementService.getEvenementById(newValue.getId()));

                    Stage newStage = new Stage();

                    Scene scene = new Scene(parent);

                    scene.setFill(Color.TRANSPARENT);
                    newStage.setScene(scene);
                    newStage.initModality(Modality.APPLICATION_MODAL);
                    newStage.initStyle(StageStyle.TRANSPARENT);
                    newStage.getScene().getRoot().setEffect(new DropShadow());

                    newStage.centerOnScreen();

                    newStage.show();

                });
    }

    public void buildTabel()
    {
        idColmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        delcol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        upcol.setCellValueFactory(new PropertyValueFactory<>("Update"));

        EvenementService evs = new EvenementService();
        ObservableList<Evenement> data  = FXCollections.observableArrayList();

        for (Evenement en : evs.getallEvenement()){
            data.add(en);
        }

        tableEvent.getItems().clear();
        tableEvent.getItems().addAll(data);


        tableEvent.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Evenement> observable,
                        Evenement oldValue,
                        Evenement newValue) -> {
                    if (newValue == null) {
                        return;
                    }

                    Parent parent = null;
                    FXMLLoader loader = null;
                    try {
                        loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/edu/bonplan/gui/Evenement/evenementRead.fxml"));

                        parent = (Parent) loader.load();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    EvenementRead display = loader.getController();
                    System.out.println(newValue.getId()+"aa");
                    EvenementService evenementService=new EvenementService();

                    display.setInfo(evenementService.getEvenementById(newValue.getId()));

                    Stage newStage = new Stage();

                    Scene scene = new Scene(parent);

                    scene.setFill(Color.TRANSPARENT);
                    newStage.setScene(scene);
                    newStage.initModality(Modality.APPLICATION_MODAL);
                    newStage.initStyle(StageStyle.TRANSPARENT);
                    newStage.getScene().getRoot().setEffect(new DropShadow());

                    newStage.centerOnScreen();

                    newStage.show();

                });
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