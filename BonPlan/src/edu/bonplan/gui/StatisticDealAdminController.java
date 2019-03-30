/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Stat;
import edu.bonplan.services.AdminDealServices;
import edu.bonplan.util.Vars;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamrouni
 */
public class StatisticDealAdminController implements Initializable {

    @FXML
    private PieChart ndv;
    @FXML
    private PieChart nde;
    @FXML
    private PieChart nrv;
    @FXML
    private PieChart nre;
    private List<Stat> lndv =new ArrayList<Stat>();
    private List<Stat> lnde =new ArrayList<Stat>();
    private List<Stat> lnrv =new ArrayList<Stat>();
    private List<Stat> lnre =new ArrayList<Stat>();
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
         
        AdminDealServices ads=new AdminDealServices();
        lndv=ads.StatisticNdv();
        ObservableList<PieChart.Data> chartndv =  FXCollections.observableArrayList();
        lndv.forEach(v->{
            
                chartndv.add(new PieChart.Data(v.getNom(), v.getNbr()));
                
        });       
         ndv.setTitle("Nombre des deal par Ville");
         ndv.setData(chartndv);
         ndv.setLabelLineLength(10);
         ndv.setLegendSide(Side.LEFT);
         
         
         
        lnde=ads.StatisticNde();
        ObservableList<PieChart.Data> chartnde =  FXCollections.observableArrayList();
        lnde.forEach(e->{
            
                chartnde.add(new PieChart.Data(e.getNom(), e.getNbr()));
                
        });       
         nde.setTitle("Nombre des deal par enseigne");
         nde.setData(chartnde);
         nde.setLabelLineLength(10);
         nde.setLegendSide(Side.LEFT);
        
        lnrv=ads.StatisticNrv();
        ObservableList<PieChart.Data> chartnrv =  FXCollections.observableArrayList();
        lnrv.forEach(r->{
            
                chartnrv.add(new PieChart.Data(r.getNom(), r.getNbr()));
                
        });       
         nrv.setTitle("Nombre des reservation par Ville");
         nrv.setData(chartnrv);
         nrv.setLabelLineLength(10);
         nrv.setLegendSide(Side.LEFT);
        
        lnre=ads.StatisticNre();
        ObservableList<PieChart.Data> chartnre =  FXCollections.observableArrayList();
        lnre.forEach(v->{
            
                chartnre.add(new PieChart.Data(v.getNom(), v.getNbr()));
                
        });       
         nre.setTitle("Nombre des reservation par enseigne");
         nre.setData(chartnre);
         nre.setLabelLineLength(10);
         nre.setLegendSide(Side.LEFT);
        
        
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
