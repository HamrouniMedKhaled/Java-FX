package edu.bonplan.gui.Enseigne;

import edu.bonplan.entities.*;
import edu.bonplan.gui.AcceuilController;
import edu.bonplan.services.AdresseService;
import edu.bonplan.services.CategorieService;
import edu.bonplan.services.EnseigneService;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.bonplan.services.ImageServices;
import edu.bonplan.util.Vars;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class EnseigneAdd implements Initializable {

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
    private Button btnadd;
    //image
    private String url;
    @FXML
    private ImageView imgVw;
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
    public void initialize(URL location, ResourceBundle resources) {
            //to do

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

            btnadd.setOnAction(e->{

                Adresse ad = new Adresse();
                Categorie ct= new  Categorie();

                for (Categorie c : listCa)
                {
                    if(c.getNom().equals(CategorieChoice.getValue()))
                    {
                        ct=c;
                    }
                }
                ad.setRue(RueField.getText());
                ad.setVille(VilleField.getText());
                ad.setPays(PayField.getText());
                ad.setCodepostal(Integer.parseInt(CodePostaleField.getText()));
                AdresseService adresseService=new AdresseService();
                ad.setId(adresseService.addAdresse(ad));
                Enseigne en = new Enseigne();
        en.setCategorieId(ct);
        en.setAdresseId(ad);
                en.setCapacite(Integer.parseInt(capaciteField.getText()));
                en.setDescription(DescriptionField.getText());
                if (ActiveChoice.getValue().equals("true"))
                    en.setActive(true);
                else
                    en.setActive(false);
                en.setNom(NomField.getText());

                en.setUserId(new FosUser(1));
               //l'image
                String imgname = UploadImage();
                Image img = new Image();
                img.setUrl(imgname);
                img.setAlt("png");
                ImageServices imgservice = new ImageServices();
                Image img1=null;
                try {
                    img1=imgservice.AfficherImage(imgservice.insert(img));
                } catch (SQLException e1) {
                    System.out.println("erreur dans l'insertion d'image");
                }
                en.setImageId(img1);

                System.out.println(en.toString());

                EnseigneService ens = new EnseigneService();
                ens.insert(en);

            });
            
    }

    @FXML
    private void ajout(ActionEvent event) {

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
            imgVw.setImage(imageImported);
            url = file.getAbsolutePath();

            return file;

        } catch (IOException ex) {
            System.out.println("erreur dans la selection d'image");
        }
        return null;
    }

    private String UploadImage() {
        String title = "";
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        try {

            title = "java_" + sqlDate.getTime();
            //chemin vers le dossier d'upload
            //wamp64\\www\\BonPlan\\Projet_pidev\\web\\uploads\\images\\
            File file = new File("C:\\wamp64\\www\\BonPlan\\Projet_pidev\\web\\uploads\\images\\" + title+".png");
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(imgVw.getImage(), null);
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("erreur au niveau de la methode uploadimage");
        }
        return title;
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
