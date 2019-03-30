 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import com.sun.java.swing.plaf.windows.resources.windows;
import edu.bonplan.entities.Deal;
import edu.bonplan.entities.Image;
import edu.bonplan.services.DealServices;
import edu.bonplan.gui.AfficherDealController;
import edu.bonplan.util.Vars;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import org.apache.commons.text.*;

/**
 * FXML Controller class
 *
 * @author Hamrouni
 */
public class AjouterDealController implements Initializable {

    @FXML
    private TextField nomd;
    @FXML
    private TextField scored;
    @FXML
    private TextField prixd;
    @FXML
    private TextField tredd;
    @FXML
    private DatePicker datedd;
    @FXML
    private DatePicker datefd;
    @FXML
    private TextArea descriptiond;
    @FXML
    private Button ajouterd;
    @FXML
    private ImageView imaged;
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
    }    

    @FXML
    private void AjouterDeal(ActionEvent event) {
        try {
            Vars.current_choice=2;
            Parent p1=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
            
            DealServices ds = new DealServices();
            Deal d = new Deal();
            d.setNom(nomd.getText());
            d.setScore(Integer.parseInt( scored.getText()));
            d.setPrix((double)Integer.parseInt( prixd.getText()));
            d.setTred(Integer.parseInt( tredd.getText()));
            LocalDate dd = datedd.getValue();
            Date dated = Date.from(dd.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date dateds = new java.sql.Date(dated.getTime());
            
            LocalDate fd = datefd.getValue();
            Date datef = Date.from(fd.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date datefs = new java.sql.Date(datef.getTime());
            String url = createUploadedImage();
            
            d.setDatedebut(dateds);
            
            d.setDatefin(datefs);
            d.setDescription(descriptiond.getText());
            RandomStringGenerator randomStringGenerator =
                    new RandomStringGenerator.Builder()
                            .withinRange('0','9')
                            .filteredBy(DIGITS)
                            .build();
            int aaa = Integer.parseInt(randomStringGenerator.generate(3).toLowerCase());
            Image img=new Image(aaa, url,"png");
            d.setImageId(img);
            ds.AjouterDeal(d,Vars.current_enseigne.getId());
            //JOptionPane.showMessageDialog(null, "le deal "+d.getNom()+" a été créer avec succés");
           
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p1);
            
            stage.setTitle("Mes Deals");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AjouterDealController.class.getName()).log(Level.SEVERE, null, ex);
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
