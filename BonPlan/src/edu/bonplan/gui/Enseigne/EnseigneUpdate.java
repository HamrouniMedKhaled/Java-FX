package edu.bonplan.gui.Enseigne;

import edu.bonplan.entities.Adresse;
import edu.bonplan.entities.Categorie;
import edu.bonplan.entities.Enseigne;
import edu.bonplan.gui.AcceuilController;
import edu.bonplan.services.AdresseService;
import edu.bonplan.services.CategorieService;
import edu.bonplan.services.EnseigneService;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EnseigneUpdate implements Initializable{

    @FXML
    private TextField NomField;

    @FXML
    private TextField DescriptionField;

    @FXML
    private TextField capaciteField;

    @FXML
    private ChoiceBox<String> ActiveChoice;

    @FXML
    private ChoiceBox<String> CategorieChoice;


    @FXML
    private TextField PayField;

    @FXML
    private TextField VilleField;

    @FXML
    private TextField RueField;

    @FXML
    private TextField CodePostaleField;
    @FXML
    private Button btnupdate;

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
    private Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Menu Reservation;
    @FXML
    private Button rr;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //for test
        
    }


    public void setInfo(int id)
    {
        this.id=id;

    }
    public void initUpdate(int id){
        this.id=id;

        ArrayList<String> categorie=new ArrayList<>();
        CategorieService categorieService=new CategorieService();
        List<Categorie> listCa= categorieService.getAll();
        for (Categorie c : listCa)
        {
            categorie.add(c.getNom());
        }
        ObservableList<String> list = FXCollections.observableArrayList(categorie);


        CategorieChoice.setItems(list);

        ObservableList <String> temps = FXCollections.observableArrayList("true","false");
        ActiveChoice.setItems(temps);


        EnseigneService ens = new EnseigneService();
        Enseigne  EnToModify = ens.getEnseigneById(id);
        System.out.println(EnToModify.toString());
        CategorieChoice.setValue(EnToModify.getCategorieId().getNom());
        if(EnToModify.getActive())
            ActiveChoice.setValue("true");
        else
            ActiveChoice.setValue("false");
        capaciteField.setText(String.valueOf(EnToModify.getCapacite()));
        NomField.setText(EnToModify.getNom());
        DescriptionField.setText(EnToModify.getDescription());
        PayField.setText(EnToModify.getAdresseId().getPays());
        RueField.setText(EnToModify.getAdresseId().getRue());
        VilleField.setText(EnToModify.getAdresseId().getVille());
        CodePostaleField.setText(String.valueOf(EnToModify.getAdresseId().getCodepostal()));
         btnupdate.setOnAction(e->{
            Adresse ad = new Adresse();
            ad.setRue(RueField.getText());
            ad.setVille(VilleField.getText());
            ad.setPays(PayField.getText());
            ad.setCodepostal(Integer.parseInt(CodePostaleField.getText()));
            //TODO : il faut ajouter sl'adresse dans la bd
            AdresseService adresseService = new AdresseService();
            ad.setId(adresseService.addAdresse(ad));

            Enseigne en = new Enseigne();
            en.setAdresseId(ad);
            en.setCapacite(Integer.parseInt(capaciteField.getText()));
            en.setDescription(DescriptionField.getText());
            if (ActiveChoice.getValue().equals("true"))
                en.setActive(true);
            else
                en.setActive(false);
            en.setNom(NomField.getText());
            Categorie ct= new  Categorie();

            for (Categorie c : listCa)
            {
                if(c.getNom().equals(CategorieChoice.getValue()))
                {
                    ct=c;
                }
            }

            en.setCategorieId(ct);

            ens.update(en,EnToModify.getId());
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
