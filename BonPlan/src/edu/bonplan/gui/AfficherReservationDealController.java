/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Categorie;
import edu.bonplan.entities.Deal;
import edu.bonplan.entities.FosUser;
import edu.bonplan.entities.Reservationdeal;
import edu.bonplan.services.CategorieService;
import edu.bonplan.services.ReservationdealServices;
import edu.bonplan.util.Vars;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Hamrouni
 */
public class AfficherReservationDealController implements Initializable {

    @FXML
    private ListView<Pane> gr;

    /**
     * Initializes the controller class.
     */
    private FosUser u;
    private Deal d;
    private Pane ap;
    private ObservableList<Pane> dd = FXCollections.observableArrayList();
    private ReservationdealServices rds = new ReservationdealServices();
    private List<Reservationdeal> maliste;
    private javafx.scene.image.Image img;
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
    private Button mesdeals;
    @FXML
    private Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Menu Reservation;
    @FXML
    private Button rr;
     private void sendSms() {
    
    try {
			// Construct data
			String apiKey = "apikey=" + "sn9Jwsj8TA4-XMeIr6IOoML0nBybSfBGR6dq5llDIk";
			String message = "&message=" + "votre paiment par score a été effectuer avec succeés";
			String sender = "&sender=" + "NobleV";
			String numbers = "&numbers=" + Vars.current_user.getTelephone();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, "message"+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
			//return "Error "+e;
                        JOptionPane.showMessageDialog(null, e);
                }
    
    }
    public void saveFile(Reservationdeal rd, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file+".pdf"));

               outWriter.write("cher Mr/Mme: "+rd.getUserId().getUsername());
               outWriter.write(" vous avez payer "+rd.getNbr()+" place dans le deal "+rd.getDealId().getNom()+" à "+rd.getDealId().getScore()+" points la place ce qui donne "+(rd.getNbr()*rd.getDealId().getScore()));
               outWriter.write(" points pour la totalité de la réservation a l'aide de votre score veiller présentez cet facture pour pouvoir benificierde votre deal");
                outWriter.newLine();
            
            
            outWriter.close();
        } catch (IOException e) {
            
        }
    }
public Pane createPane(Reservationdeal rd ){
        
        Pane p = new Pane();
        ImageView imgd=new ImageView();
        Label nom = new Label();
        Label score = new Label();
        Label prix = new Label();
        Button payer =new Button("Payer");
        payer.setLayoutX(150);
        payer.setLayoutY(150);
        payer.setVisible(true);
        payer.setOnAction( (ActionEvent e) -> {
            
            if ((rd.getNbr()*rd.getDealId().getScore())<=rd.getUserId().getScore())
                {
                    Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer facture de payement par score");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("Pdf files (*.pdf)", "*.pdf");
        
       
        
        
        File file = fileChooser.showSaveDialog(secondaryStage);
        
            if(file != null) {
                saveFile(rd, file);
         }
            //sendSms();
            try {
                 
                Parent p7=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservationDeal.fxml"));
                
                rds.PayerScore(rd);
                Stage stage=new Stage();
                Scene scene = new Scene(p7);
                
                stage.setTitle("Afficher Reservation");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherReservationDealController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else
                {
                    JOptionPane.showMessageDialog(null, "Votre score est insufisant");
                }
        });
        
        Button annuler =new Button("Annuler");
        annuler.setLayoutX(250);
        annuler.setLayoutY(150); 
        annuler.setOnAction((ActionEvent e) -> {
            try {
                
                Parent p6=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservationDeal.fxml"));
                
                rds.SupprimerReservationDeal(rd.getId());
                Stage stage=new Stage();
                Scene scene = new Scene(p6);
                
                stage.setTitle("Afficher Reservation");
                stage.setScene(scene);
                stage.show();
                
            } catch (IOException ex) {
                Logger.getLogger(AfficherReservationDealController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } );
        
        nom.setText(rd.getDealId().getNom());
        nom.setWrapText(false);
        nom.setPrefSize(435, 89);
        nom.setLayoutX(150);
        nom.setLayoutY(1);
        nom.setMaxWidth(435);
        
        score.setText("Score : "+String.valueOf(rd.getDealId().getScore()));
        score.setWrapText(false);
        score.setPrefSize(435, 89);
        score.setMaxWidth(435);
        score.setLayoutX(150);
        score.setLayoutY(13);
        
        prix.setText("Prix : "+rd.getDealId().getPrix()+" TND "+String.valueOf(rd.getDealId().getPrix()-((rd.getDealId().getPrix()*rd.getDealId().getTred())/100)));
        prix.setWrapText(false);
        prix.setPrefSize(435, 89);
        prix.setMaxWidth(435);
        prix.setLayoutX(150);
        prix.setLayoutY(26);
        
        String hhh=rd.getDealId().getImageId().getAlt().substring(0,rd.getDealId().getImageId().getAlt().length()-1);
        
        img = new javafx.scene.image.Image("http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/"+rd.getDealId().getImageId().getUrl()+".png");
        imgd.setImage(img);
        imgd.setFitHeight(100);
        imgd.setFitWidth(100);
        if (rd.getPayer()==true)
        {
            payer.setVisible(false);
        }
        gr.setItems(dd);
        p.getChildren().add(imgd);     
        p.getChildren().add(nom);
        p.getChildren().add(score);
        p.getChildren().add(prix);
        p.getChildren().add(payer);
        p.getChildren().add(annuler);
        return p;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            maliste=rds.AfficherReservationDeal(Vars.current_user);
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
            maliste.forEach(rrr->{
                Pane p = new Pane();
                p = createPane(rrr);
                dd.add(p);
            });
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReservationDealController.class.getName()).log(Level.SEVERE, null, ex);
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
            
            stage.setTitle("Liste des deals par ville");
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
            
            stage.setTitle("Liste des deals par Categorie");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void mesdeals(ActionEvent event) {
        try {
            Vars.current_choice=2;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherDeals.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("liste de mes deals");
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
