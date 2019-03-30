   package edu.bonplan.gui.Menu;

import edu.bonplan.entities.Enseigne;
import edu.bonplan.entities.Menu;
import edu.bonplan.gui.AcceuilController;
import edu.bonplan.services.EnseigneService;
import edu.bonplan.services.MenuService;
import edu.bonplan.util.Vars;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
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

public class MenuUpdate implements Initializable {

    @FXML
    private TextField Contenu;

    @FXML
    private TextField Prix;

    @FXML
    private ChoiceBox<String > Enseigne;


    @FXML
    private Button save;

    private int id ;
    @FXML
    private javafx.scene.control.Menu acceuil;
    @FXML
    private javafx.scene.control.Menu logout;
    @FXML
    private Button dc;
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
    private javafx.scene.control.Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private javafx.scene.control.Menu Reservation;
    @FXML
    private Button rr;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> enseignes=new ArrayList<>();
        EnseigneService enseigneService=new EnseigneService();
        List<edu.bonplan.entities.Enseigne> listEn= enseigneService.getallEnseigne();
        for (Enseigne e : listEn)
        {
            enseignes.add(e.getNom());
        }
        ObservableList<String> list = FXCollections.observableArrayList(enseignes);


        Enseigne.setItems(list);
        save.setOnAction(e1->{
            MenuService menuService = new MenuService();




            Menu menu=new Menu();
            menu.setContenu( Contenu.getText());
            menu.setPrix(Float.parseFloat(Prix.getText()));

            int id_enseigne;
            for(Enseigne en : listEn)
            {
                if(en.getNom().equals(Enseigne.getValue()))
                {
                    menu.setEnseigneId(en);
                }
            }
            System.out.println(menu);

            menuService.updateMenu(menu,id);
        });
    }

    public void setInfo(Menu menu)
    {
        id=menu.getId();
        Contenu.setText(menu.getContenu());
        Prix.setText(String.valueOf(menu.getPrix()));

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
