package edu.bonplan.gui.Enseigne;

import edu.bonplan.entities.Enseigne;
import edu.bonplan.gui.AcceuilController;
import edu.bonplan.services.EnseigneService;
import edu.bonplan.util.Vars;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EnseigneShow implements Initializable {

    @FXML
    private TableColumn<?, ?> idcol;
    @FXML
    private TableColumn<?, ?> nomcol;
    @FXML
    private TableColumn<?, ?> activecol;
    @FXML
    private TableColumn<?, ?> capacitecol;
    @FXML
    private TableView<Enseigne> tablev;
    @FXML
    private TableColumn<?, ?> upcol;

    @FXML
    private TableColumn<?, ?> delcol;
    @FXML
    private TextField txtFind;

    @FXML
    private Button btnFind;
    @FXML
    private Button evenement;
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
    public void initialize(URL location, ResourceBundle resources) {
      buildTabel();

        btnFind.setOnAction(e->
        {
            String search=txtFind.getText();
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
                ObservableList<Enseigne> datas = FXCollections.observableArrayList();
                EnseigneService ens = new EnseigneService();

                Enseigne en = ens.getEnseigneByNom(search);
                datas.add(en);


                buildTabel(datas);
            }
        });
    }
    public void buildTabel(ObservableList<Enseigne> data)
    {
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        activecol.setCellValueFactory(new PropertyValueFactory<>("active"));
        capacitecol.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        delcol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        upcol.setCellValueFactory(new PropertyValueFactory<>("Update"));



        tablev.getItems().clear();
        tablev.getItems().addAll(data);


        tablev.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Enseigne> observable,
                        Enseigne oldValue,
                        Enseigne newValue) -> {
                    if (newValue == null) {
                        return;
                    }

                    Parent parent = null;
                    FXMLLoader loader = null;
                    try {
                        loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/edu/bonplan/gui/Enseigne/readEnseigne.fxml"));

                        parent = (Parent) loader.load();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    ReadEnseigne display = loader.getController();
                    System.out.println(newValue.getId()+"aa");
                    display.setInfo(newValue.getId());

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
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        activecol.setCellValueFactory(new PropertyValueFactory<>("active"));
        capacitecol.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        delcol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        upcol.setCellValueFactory(new PropertyValueFactory<>("Update"));

        EnseigneService ens = new EnseigneService();
        ObservableList<Enseigne> data  = FXCollections.observableArrayList();

        for (Enseigne en : ens.getallEnseigne()){
            data.add(en);
        }


        tablev.getItems().clear();
        tablev.getItems().addAll(data);


        tablev.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Enseigne> observable,
                        Enseigne oldValue,
                        Enseigne newValue) -> {
                    if (newValue == null) {
                        return;
                    }

                    Parent parent = null;
                    FXMLLoader loader = null;
                    try {
                        loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/edu/bonplan/gui/Enseigne/readEnseigne.fxml"));

                        parent = (Parent) loader.load();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    ReadEnseigne display = loader.getController();
                    System.out.println(newValue.getId()+"aa");
                    display.setInfo(newValue.getId());

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

    @FXML
    private void evenement(ActionEvent event) {
        
        try {
            Parent p1=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/Evenement/evenementShow.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p1);
            
            stage.setTitle("Connexion");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EnseigneShow.class.getName()).log(Level.SEVERE, null, ex);
        }
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
