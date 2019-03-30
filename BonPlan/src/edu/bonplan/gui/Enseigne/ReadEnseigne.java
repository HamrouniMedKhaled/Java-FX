package edu.bonplan.gui.Enseigne;

import edu.bonplan.entities.Enseigne;
import edu.bonplan.entities.Image;
import edu.bonplan.gui.AcceuilController;
import edu.bonplan.services.CategorieService;
import edu.bonplan.services.EnseigneService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.bonplan.services.ImageServices;
import edu.bonplan.util.Vars;
import java.io.IOException;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ReadEnseigne implements Initializable {

    @FXML
    private Label descriptionField;

    @FXML
    private Label villeField;

    @FXML
    private Label categorieField;

    @FXML
    private Label capaciteField;

    @FXML
    private Label activeField;

    @FXML
    private Label rueField;

    @FXML
    private Label codepostaleField;

    @FXML
    private Label payField;

    @FXML
    private Label nomField;



    @FXML
    private ImageView imgVw;

    public int id;
    @FXML
    private Button ajoutdeal;
    @FXML
    private Menu acceuil;
    @FXML
    private Button ajoutres;
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


    }
    

    public void setInfo(int id)
    {
    this.id =id;
        EnseigneService ens = new EnseigneService();
        Enseigne  enseigne = ens.getEnseigneById(id);
        System.out.println(enseigne.getNom()+"aza"+id);

        capaciteField.setText(String.valueOf(enseigne.getCapacite()));
        nomField.setText(enseigne.getNom());
        descriptionField.setText(enseigne.getDescription());
        System.out.println(enseigne.getAdresseId().getPays());
        payField.setText(enseigne.getAdresseId().getPays());
        rueField.setText(enseigne.getAdresseId().getRue());
        villeField.setText(enseigne.getAdresseId().getVille());
        codepostaleField.setText(String.valueOf(enseigne.getAdresseId().getCodepostal()));
        if (enseigne.getActive()==true)
        {
            activeField.setText("active");

        }
        else {
            activeField.setText("pas active");
        }
        if (enseigne.getUserId().getId()!=Vars.current_user.getId())
        {
            ajoutdeal.setVisible(false);
        }
        categorieField.setText(enseigne.getCategorieId().getNom());
        ImageServices imgS =  new ImageServices();
        Image img=null;
        try {
            img = imgS.AfficherImage(enseigne.getImageId().getId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur dans la selection d'image");
        }
        String imgURL="";
        try {
            imgURL ="http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/"+img.getUrl();

            System.out.println(img.getAlt());
        }catch (NullPointerException e){
            System.out.println("img null");
        }
        System.out.println(imgURL);
        javafx.scene.image.Image imgToShow = new javafx.scene.image.Image(imgURL);
        imgVw.setImage(imgToShow);

    }

    @FXML
    private void AjouterDeal(ActionEvent event) {
        try {
            
            EnseigneService ens = new EnseigneService();
            
            try {
                Vars.current_enseigne=ens.AfficherEnseigne(id);
                
            } catch (SQLException ex) {
                Logger.getLogger(ReadEnseigne.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent p1=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AjouterDeal.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p1);
            
            stage.setTitle("Ajouter un deal");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReadEnseigne.class.getName()).log(Level.SEVERE, null, ex);
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
    private void AjouterResrvation(ActionEvent event) {
        try {
            
            EnseigneService ens = new EnseigneService();
            
            try {
                Vars.current_enseigne=ens.AfficherEnseigne(id);
                
            } catch (SQLException ex) {
                Logger.getLogger(ReadEnseigne.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent p1=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AjouterReservationEnseigne.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p1);
            
            stage.setTitle("Ajouter une reservation");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReadEnseigne.class.getName()).log(Level.SEVERE, null, ex);
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
