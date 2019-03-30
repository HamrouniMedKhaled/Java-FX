/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Categorie;
import edu.bonplan.entities.Deal;
import edu.bonplan.entities.FosUser;
import edu.bonplan.entities.Image;
import edu.bonplan.services.AdminDealServices;
import edu.bonplan.services.CategorieService;
import edu.bonplan.services.DealServices;
import edu.bonplan.util.Vars;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamrouni
 */
public class AfficherDealsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<Pane> gr;
    private FosUser u;
    private Pane ap;
    private ObservableList<Pane> dd = FXCollections.observableArrayList();
    private DealServices ds = new DealServices();
    private AdminDealServices ads = new AdminDealServices();
    private List<Deal> maliste;
    private javafx.scene.image.Image img;
    @FXML
    private Menu logout;
    @FXML
    private Menu acceuil;
    @FXML
    private Menu plan;
    @FXML
    private Menu deal;
    @FXML
    private ChoiceBox<String> categories;
    @FXML
    private TextField vv;
    @FXML
    private Button rechv;
    @FXML
    private Button dc;
    @FXML
    private Button aa;
    @FXML
    private Button pp;
    @FXML
    private Button ddd;
    @FXML
    private Button scsc;
    @FXML
    private Button reservation;
    @FXML
    private Button mesdeals;
    @FXML
    private Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Button statistic;
    @FXML
    private AnchorPane xx;
    @FXML
    private Menu Reservation;
    @FXML
    private Button rr;
      
     public Pane createPane(Deal d ){
        //Vars.current_user;
        Pane p = new Pane();
        ImageView imgd=new ImageView();
        Label nom = new Label();
        Label score = new Label();
        Label prix = new Label();
        Button aff =new Button("Afficher");
        aff.setLayoutX(150);
        aff.setLayoutY(150); 
        aff.setVisible(true);
        aff.setOnAction((ActionEvent e) -> {  try {
            Vars.current_deal=d;
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeal.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Afficher Deal");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
        Button des =new Button("Desactiver");
        des.setLayoutX(150);
        des.setLayoutY(150); 
        des.setVisible(false);
        des.setOnAction((ActionEvent e) -> {  try {
            ads.DesactiverDeal(d);
            Parent p8=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p8);
            
            stage.setTitle("Afficher Deal");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
        
        nom.setText(d.getNom());
        nom.setWrapText(false);
        nom.setPrefSize(435, 89);
        nom.setLayoutX(150);
        nom.setLayoutY(1);
        nom.setMaxWidth(435);
        
        score.setText("Score : "+String.valueOf(d.getScore()));
        score.setWrapText(false);
        score.setPrefSize(435, 89);
        score.setMaxWidth(435);
        score.setLayoutX(150);
        score.setLayoutY(13);
        
        prix.setText("Prix : "+d.getPrix()+" TND "+String.valueOf(d.getPrix()-((d.getPrix()*d.getTred())/100)));
        prix.setWrapText(false);
        prix.setPrefSize(435, 89);
        prix.setMaxWidth(435);
        prix.setLayoutX(150);
        prix.setLayoutY(26);
        
        //String hhh=d.getImageId().getAlt().substring(0,d.getImageId().getAlt().length()-1);
        
        img = new javafx.scene.image.Image("http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/"+d.getImageId().getUrl()+".png");
       
        imgd.setImage(img);
        imgd.setFitHeight(100);
        imgd.setFitWidth(100);
        if (Vars.current_admin==1)
        {
            aff.setVisible(false);
            des.setVisible(true);
        }
        gr.setItems(dd); 
        p.getChildren().add(imgd);     
        p.getChildren().add(nom);
        p.getChildren().add(score);
        p.getChildren().add(prix);
        p.getChildren().add(aff);
        p.getChildren().add(des);
        return p;
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            if (Vars.current_admin==1)
            {
                categories.setVisible(false);
                vv.setVisible(false);
                rechv.setVisible(false);
                scsc.setVisible(false);
                reservation.setVisible(false);
                mesdeals.setVisible(false);
                statistic.setVisible(true);
                maliste=ads.AfficherDeals();
            }
            else
            {
                statistic.setVisible(false);
                if (Vars.current_choice==1)
                    maliste = ds.AfficherDeals(Vars.current_user);
                else
                {
                    if (Vars.current_choice==2)
                        maliste = ds.AfficherMesDeals(Vars.current_user);
                    else
                    {
                        if (Vars.current_choice==3)
                            maliste = ds.RechercheScoreDeal(Vars.current_user);
                        else
                        {
                            if (Vars.current_choice==4)
                                maliste = ds.RechercheCategorieDeal(Vars.current_user,Vars.current_type );
                            else
                            {
                                maliste = ds.RechercheVillesDeal(Vars.current_user,Vars.current_type );
                                
                                
                            }
                        }
                    }
                }
            }
            CategorieService cs=new CategorieService();
            List<Categorie> c = cs.AllCategories();
            c.forEach(e->{
                categories.getItems().add(e.getNom());
            });
            categories.valueProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue observable, String oldValue, String newValue) {
                    try {
                        Vars.current_choice= 4;
                        Vars.current_type=newValue;
                        System.out.println(newValue);
                        System.out.println(categories.getValue());
                        Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
                        
                        
                        Stage stage=new Stage();
                        Scene scene = new Scene(p4);
                        
                        stage.setTitle("Afficher Deal");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            maliste.forEach(d->{
                Pane p = new Pane();
                p = createPane(d);
                dd.add(p);
            });
        } catch (SQLException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void rechercheville(ActionEvent event) {
        try {
            Vars.current_choice= 5;
            Vars.current_type=vv.getText();
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("liste des deal par ville");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @FXML
    private void recherchescore(ActionEvent event) {
        try {
            Vars.current_choice= 3;
            
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Liste des deals par score");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void reservation(ActionEvent event) {
        try {
            
            
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservationDeal.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Liste de mes reservations");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mesdeals(ActionEvent event) {
        try {
            Vars.current_choice=2;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("liste de mes deals");
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
    private void satistic(ActionEvent event) {
        try {
            Parent p7=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/StatisticDealAdmin.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p7);
            
            stage.setTitle("Statistic des deal et reservation par ville ou enseigne");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void reservation1(ActionEvent event) {
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
