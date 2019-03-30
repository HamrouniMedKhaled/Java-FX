/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Comment;
import edu.bonplan.entities.Deal;
import edu.bonplan.entities.FosUser;
import edu.bonplan.services.CommentServices;
import edu.bonplan.util.Vars;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author morrta
 */
public class AddCommentController implements Initializable {

    @FXML
    private ListView<Pane> commentList;
    @FXML
    private TextArea CommentContent;
    @FXML
    private ListView<?> tags;
    @FXML
    private Button Commenter;
    private ObservableList<Pane> comments = FXCollections.observableArrayList();
    public CommentServices cs =  new CommentServices();
    public List<Comment> listCom ;
    public FosUser f1 = new FosUser(7,"123");
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
    

   // public Button delC=new Button();
     private void sendSms() {
    
    try {
			// Construct data
			String apiKey = "apikey=" + "sn9Jwsj8TA4-XMeIr6IOoML0nBybSfBGR6dq5llDIk";
			String message = "&message=" + "Vous avez débloquer un nouveau badge parfait";
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
    public Pane createPane(Comment c ){
        //Vars.current_user;
        
        Pane p = new Pane();
        Label coml = new Label();
        coml.setText(c.getContent());
        //System.out.println(c.getUserId().getUsername());
        coml.setPrefSize(435, 89);
        coml.setLayoutX(87);
        coml.setLayoutY(13);
        coml.setMaxWidth(435);
        coml.setWrapText(true);
        
        Button delC=new Button();
        delC.setPrefWidth(25);
        delC.setPrefHeight(25);
        delC.setText("X");
        delC.setLayoutX(500);
        
        delC.setVisible(false);
        delC.setOnAction((ActionEvent e) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("Are you shure you want to delete this comment ? ");
                alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                              
                CommentServices service = new CommentServices();
                //Comment c1=new Comment();
                service.removeCommentById(c.getId());
                comments.remove(p);
                commentList.setItems(comments);  
                
                }
                });
        });
       
      if(c.getUserId().getId()==Vars.current_user.getId()){
            delC.setVisible(true);
        }
      
        p.getChildren().add(delC);
        
       
        
        //p.getChildren().add(delC);
        p.getChildren().add(coml);
        
        
        return p;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //listCom = cs.getAll();
            //remplacer getAll() par getAllForDeal
            listCom = cs.getAllForDeal(Vars.current_deal.getId());
            listCom.forEach(c->{
            Pane p = new Pane();
            p = createPane(c);
            //p.getChildren().get(0);
           // p.getChildren().remove(delC);
            comments.add(p);
               
        });
        
       commentList.setItems(comments);
    }    

    @FXML
    private void CommentAdd(ActionEvent event) {
        Comment comment = new Comment();
        Pane p = new Pane();
        //Deal d1 = new Deal(1);
        //FosUser f1 = new FosUser(7);
        //Vars.current_user =f1;
        comment.setUserId(Vars.current_user);
        comment.setContent(comment.getUserId().getUsername()+" : "+CommentContent.getText());
        comment.setDealId(Vars.current_deal);
        cs.addComment(comment);
        p = createPane(comment);
        comments.add(p);
        //System.out.println(comment.getUserId().getUsername());
        cs.checkAndUnlock(Vars.current_user);
        if(Vars.unlock_newMarker == 1){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez debloquez un nouveau badge ! do you want to see your badges  ? ");
           // sendSms();
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        Parent p3=FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/testAcceuil.fxml"));
                        Stage stage=new Stage();
                        Scene scene = new Scene(p3);
                        
                        stage.setTitle("these are your badges");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AddCommentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
            });

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
