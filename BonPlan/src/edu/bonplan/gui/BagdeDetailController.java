/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.gui;

import edu.bonplan.entities.Badge;
import edu.bonplan.util.Vars;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author morrta
 */
public class BagdeDetailController implements Initializable {

    @FXML
    private Label badgeName;
    @FXML
    private Label action_count;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        badgeName.setText(Vars.current_badge.getName()); 
        action_count.setText(Vars.current_badge.getActionCount()+"");
    }    
    
}
