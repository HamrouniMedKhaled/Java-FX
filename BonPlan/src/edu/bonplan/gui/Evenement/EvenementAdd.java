package edu.bonplan.gui.Evenement;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.bonplan.entities.Enseigne;
import edu.bonplan.entities.Evenement;
import edu.bonplan.entities.Image;
import edu.bonplan.entities.Menu;
import edu.bonplan.gui.AcceuilController;
import edu.bonplan.services.EnseigneService;
import edu.bonplan.services.EvenementService;
import edu.bonplan.services.ImageServices;
import edu.bonplan.util.Vars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EvenementAdd implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField description;

    @FXML
    private ChoiceBox<String> Enseigne;


    @FXML
    private Button save;

    @FXML
    private DatePicker datep;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ArrayList<String> enseignes = new ArrayList<>();
        EnseigneService enseigneService = new EnseigneService();
        List<edu.bonplan.entities.Enseigne> listEn = enseigneService.getallEnseigne();
        for (Enseigne e : listEn) {
            enseignes.add(e.getNom());
        }
        ObservableList<String> list = FXCollections.observableArrayList(enseignes);


        Enseigne.setItems(list);
        save.setOnAction(e -> {
            Evenement ev = new Evenement();
            ev.setNom(nom.getText());
            ev.setDescription(description.getText());
            int id_enseigne;
            for (Enseigne en : listEn) {
                if (en.getNom().equals(Enseigne.getValue())) {
                    ev.setEnseigneId(en);
                }
            }
            ev.setImageId(new Image(1));
            LocalDate localDate = datep.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            ev.setDate(sqlDate);
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
            ev.setImageId(img1);
            EvenementService es=new EvenementService();
            es.insert(ev);
            System.out.println("insertion effectuer avec succés");
//mailing api
            //add your eamil nd email password
            final String username = "fethi.wuerfelli@gmail.com";
            final String password = "54181809";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            javax.mail.Session session = javax.mail.Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                //add your email
                message.setFrom(new InternetAddress("fethi.wuerfelli@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        //ev.getEnseigneId().getUserId().getEmail())
                        InternetAddress.parse("fathi.ouerfelli@esprit.tn"));
                message.setSubject("BonPlan");
                //+ev.getEnseigneId().getUserId().getUsername()
                message.setText("Dear "
                        + "\n\n votre evenemnt a ete "
                        + "cree avec succes!");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException maile) {
                throw new RuntimeException(maile);
            }
        });


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

            title = "java_" + sqlDate.getTime()+".png";
            //chemin vers le dossier d'upload
            File file = new File("C:\\wamp64\\www\\BonPlan\\Projet_pidev\\web\\uploads\\images\\" + title);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(imgVw.getImage(), null);
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException ex) {
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
