/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Categorie;
import edu.bonplan.entities.Plan;
import edu.bonplan.services.AdminPlanServices;
import edu.bonplan.services.CategorieService;
import edu.bonplan.services.PlanServices;
import edu.bonplan.util.Vars;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author darkz
 */
public class AfficherPlansController implements Initializable {

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
    private ListView<Pane> gr;
    
    
    private Pane ap;
    private ObservableList<Pane> dd = FXCollections.observableArrayList();
    private PlanServices ds = new PlanServices();
    private AdminPlanServices ads = new AdminPlanServices();
    private List<Plan> maliste;
    private javafx.scene.image.Image img;
    @FXML
    private Button ajout;
    @FXML
    private Button plansing;
    @FXML
    private Button planattente;
    @FXML
    private Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Button rr;
    @FXML
    private Menu Reservation1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 
        try {
            planattente.setVisible(false);
            plansing.setVisible(false);
            ajout.setVisible(false);
            if (Vars.current_admin==1)
                planattente.setVisible(false);
                plansing.setVisible(false);
                if (Vars.current_choice==1)
                maliste=ads.AfficherPlans();
                if ((Vars.current_choice==6))
                maliste=ads.AfficherReported();
                 if   (Vars.current_choice==7)
                     maliste=ads.AfficherPlansEnAttente();
            else
            {
                if (Vars.current_choice==1)
                    maliste = ds.AfficherPlans();
                else
                {
                    if (Vars.current_choice==2){
                        ajout.setVisible(true);
                        maliste = ds.AfficherMesPlans();}
                    else
                    {
                        if (Vars.current_choice==3)
                            maliste = ds.RechercheScorePlan();
                        else
                        {
                            if (Vars.current_choice==4)
                                maliste = ds.RechercheCategoriePlan(Vars.current_type );
                            else
                            {
                                maliste = ds.RechercheNomPlan(Vars.current_type);
                                
                                
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
                        Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
                        
                        
                        Stage stage=new Stage();
                        Scene scene = new Scene(p4);
                        
                        stage.setTitle("Afficher Plan par catégorie");
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
    
    
    
    public Pane createPane(Plan d ){
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
            Vars.current_plan=d;
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlan.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Afficher Plan");
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
            Parent p8=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            
            ads.DesactiverPlan(d);
            Stage stage=new Stage();
            Scene scene = new Scene(p8);
            
            stage.setTitle("Afficher Plan");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
        
        nom.setText(d.getTitre());
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
        
        prix.setText("Description:" + d.getDescription());
        prix.setWrapText(false);
        prix.setPrefSize(435, 89);
        prix.setMaxWidth(435);
        prix.setLayoutX(150);
        prix.setLayoutY(26);
        
        String hhh=d.getImageId().getUrl();
        
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

    @FXML
    private void logout(ActionEvent event) {
        try {
            Vars.current_user=null;
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/logging.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Acceuil");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
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
            ((Node)(event.getSource())).getScene().getWindow().hide();
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
            ((Node)(event.getSource())).getScene().getWindow().hide();
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
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercheville(ActionEvent event) {
        try {
            Vars.current_choice= 5;
            Vars.current_type=vv.getText();
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Afficher Plan");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void recherchescore(ActionEvent event) {
        try {
            Vars.current_choice= 3;
            
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Liste des Plans les plus vus");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void reservation(ActionEvent event) {
        try {
            Vars.current_choice= 2;
            
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Mes Plans");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AjouterPlan.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Ajouter Plan");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ReportedPlans(ActionEvent event) {
        try {
            Vars.current_choice= 6;
            
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Plans signalés");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PendingPlans(ActionEvent event) {
        try {
            Vars.current_choice= 7;
            
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Plans en attente");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void reservation1(ActionEvent event) {
        
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
