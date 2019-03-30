/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Deal;
import edu.bonplan.entities.*;
import edu.bonplan.services.CategorieService;
import edu.bonplan.services.DealServices;
import edu.bonplan.services.ReservationdealServices;
import edu.bonplan.util.Vars;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamrouni
 */
public class AfficherDealController implements Initializable {

    @FXML
    private Button BtModif;
    @FXML
    private Button BtSupp;
    @FXML
    private Button BtReser;
    @FXML
    private Label nb;
    @FXML
    private Button moin;
    @FXML
    private Button plus;
    private int nbPlace=0;
    @FXML
    private ImageView imgd;
    @FXML
    private Label nomd;
    @FXML
    private Label nbv;
    @FXML
    private Label cat;
    @FXML
    private Label score;
    @FXML
    private Label prixd;
    @FXML
    private Label tred;
    @FXML
    private Label desc;
    @FXML
    private Button com;
    private Deal d;
    private Image img;
    private FosUser u;
    
    /**
     * Initializes the controller class.
     */
     DealServices ds = new DealServices();
    @FXML
    private javafx.scene.control.Menu logout;
    @FXML
    private Button dc;
    @FXML
    private javafx.scene.control.Menu acceuil;
    @FXML
    private Button aa;
    @FXML
    private javafx.scene.control.Menu plan;
    @FXML
    private Button pp;
    @FXML
    private javafx.scene.control.Menu deal;
    @FXML
    private Button ddd;
    @FXML
    private ChoiceBox<String> categories;
    @FXML
    private TextField vv;
    @FXML
    private Button rechv;
    @FXML
    private Button scsc;
    @FXML
    private Button reservation;
    @FXML
    private Button mesdeals;
    @FXML
    private javafx.scene.control.Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Label xxx;
    @FXML
    private javafx.scene.control.Menu Reservation;
    @FXML
    private Button rr;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

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
            
            nb.setText(String.valueOf(nbPlace));
            d=ds.AfficherDeal(Vars.current_deal.getId());
            nbv.setText(String.valueOf(d.getVisite()));
            nomd.setText(d.getNom());
            prixd.setText(String.valueOf(d.getPrix()));
            tred.setText(String.valueOf(d.getPrix()-((d.getPrix()*d.getTred())/100)));
            cat.setText(d.getEnseigneId().getCategorieId().getNom());
            score.setText(String.valueOf(d.getScore()));
            desc.setText(d.getDescription());
            String hhh=d.getImageId().getAlt().substring(0,d.getImageId().getAlt().length()-1);
            
            img = new javafx.scene.image.Image("http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/"+ d.getImageId().getUrl()+".png");
            imgd.setImage(img);
            if (d.getEnseigneId().getUserId().getId()!=Vars.current_user.getId())
            {
                BtModif.setVisible(false);
                BtSupp.setVisible(false);
                xxx.setVisible(true);
                moin.setVisible(true);
                nb.setVisible(true);
                plus.setVisible(true);
                BtReser.setVisible(true);
            }
            else
            {
                BtModif.setVisible(true);
                BtSupp.setVisible(true);
                xxx.setVisible(false);
                moin.setVisible(false);
                nb.setVisible(false);
                plus.setVisible(false);
                BtReser.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    


    @FXML
    private void labelmoinmoin(ActionEvent event) {
        
        if(nbPlace>0)
        {
            nbPlace--;
        }
        
        nb.setText(String.valueOf(nbPlace));
    }

    @FXML
    private void labelplusplus(ActionEvent event) {
        if(nbPlace<d.getEnseigneId().getCapacite())
        {
            nbPlace++;
        }
       nb.setText(String.valueOf(nbPlace)); 
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/ModifierDeal.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Modifier Deal");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {

        try {
            Vars.current_choice=2;
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
            ds.SupprimerDeal(d.getId());
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Mes Deals");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Reserver(ActionEvent event) {
        try {
            
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservationDeal.fxml"));
            
            ReservationdealServices rds=new ReservationdealServices();
            if (nbPlace>0)
            {
                rds.AjouterReservationDeal(d, Vars.current_user, nbPlace);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "vous devez selectionner un nombre de place");
            }
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Mes Reservation");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
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
            
            stage.setTitle("Liste des deal par ville");
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
    private void commentaire(ActionEvent event) {
         try {
            Parent p2=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AddComment.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(p2);
            
            stage.setTitle("Liste des commentaires");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AjouterDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mesdeals(ActionEvent event) {
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
