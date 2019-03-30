/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Categorie;
import edu.bonplan.entities.Image;
import edu.bonplan.entities.Plan;
import edu.bonplan.services.CategorieService;
import edu.bonplan.services.PlanServices;
import edu.bonplan.util.Vars;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import org.apache.commons.text.RandomStringGenerator;

/**
 * FXML Controller class
 *
 * @author darkz
 */
public class AjouterPlanController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextArea descriptiond;
    @FXML
    private ImageView imaged;
    @FXML
    private ComboBox<String> categorie;
    private String imageUrl;
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
    private Button ajouterd;
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
        
        
        try {
            CategorieService cs=new CategorieService();
            List<Categorie> c = cs.AllCategories();
            c.forEach(e->{
                categorie.getItems().add(e.getNom());
            });
        } catch (SQLException ex) {
            Logger.getLogger(AjouterPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        
    }  
    
    
    
   
    
    
    
    @FXML
    private File importBtn(ActionEvent event) {
        
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose image");
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage imageImported = SwingFXUtils.toFXImage(bufferedImage, null);
            imaged.setImage(imageImported);
            imageUrl = file.getAbsolutePath();
            return file;
            
           
        } catch (IOException ex) {
            Logger.getLogger(AjouterDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    
    
    public String createUploadedImage(){
        String title="";
        try {
            RandomStringGenerator randomStringGenerator =
            new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .filteredBy(DIGITS)
                .build();
            title=randomStringGenerator.generate(3).toLowerCase(); 
            File file = new File("C:\\wamp64\\www\\BonPlan\\Projet_pidev\\web\\uploads\\images\\"+title+".png");
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(imaged.getImage(), null);
            ImageIO.write(renderedImage,"png",file);
        } catch (IOException ex) {
            Logger.getLogger(AjouterDealController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return title;
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
            Vars.current_choice=1;
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
    private void Ajouter(ActionEvent event) throws SQLException {
        try {
            Vars.current_choice=2;
                    Parent p1=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherPlans.fxml"));
            
            
            
            CategorieService cs = new CategorieService();
            
            Categorie c = cs.getCategorie(categorie.getValue());
            System.out.println(c.getNom()+" ajoute");
            
            
            
            
            PlanServices ps = new PlanServices();
            Plan p = new Plan();
            p.setTitre(titre.getText());
            p.setCategorieId(c);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            p.setDateAjout(date);
            p.setDescription(descriptiond.getText());
           
            String alt = createUploadedImage();
            
            RandomStringGenerator randomStringGenerator =
                    new RandomStringGenerator.Builder()
                            .withinRange('0','9')
                            .filteredBy(DIGITS)
                            .build();
            int aaa = Integer.parseInt(randomStringGenerator.generate(3).toLowerCase());
            Image img=new Image(aaa,alt,"jpeg");
            p.setImageId(img);
            ps.AjouterPlan(p);
            System.out.println(p.toString());
     
            Stage stage=new Stage();
            Scene scene = new Scene(p1);
            
            stage.setTitle("Mes Plans");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AjouterDealController.class.getName()).log(Level.SEVERE, null, ex);
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
