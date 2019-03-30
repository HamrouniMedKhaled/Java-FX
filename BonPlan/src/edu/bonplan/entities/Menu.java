/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.entities;

import edu.bonplan.gui.Menu.MenuUpdate;
import edu.bonplan.gui.Menu.ShowMenu;
import edu.bonplan.services.MenuService;
import java.io.Serializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Hamrouni
 */
@Entity
@Table(name = "menu")
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "contenu")
    private String contenu;
    @Basic(optional = false)
    @Column(name = "prix")
    private double prix;
    @JoinColumn(name = "enseigne_id", referencedColumnName = "id")
    @ManyToOne
    private Enseigne enseigneId;

    Button Delete;
    Button Update;

    public Button getDelete() {
        return Delete;
    }

    public Button getUpdate() {
        return Update;
    }

    public Menu() {
        Delete = new Button("Supprimer");
        Delete.setOnAction((event -> {
            MenuService menuService= new MenuService();
            menuService.delete(id);

            Parent p =null;
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/edu/bonplan/gui/Menu/showMenu.fxml"));
            System.out.println(loader.getLocation());
            try{
                p = loader.load();
            }catch (Exception er){
                er.printStackTrace();
            }
            ShowMenu c = loader.getController();
            c.buildTable();
            Scene sc = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(sc);
            st.show();
        }));
        Update = new Button("Modifier");
        Update.setOnAction(event0001 -> {
            Parent p =null;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/bonplan/gui/Menu/menuUpdate.fxml"));
            System.out.println("update :"+ loader.getLocation());
            try{
                p = loader.load();
            }catch (Exception er){
                System.out.println("hné ?");
                er.printStackTrace();
            }
            MenuUpdate c = loader.getController();
            //c.setInfo(id);
            c.setInfo(this);

            Scene sc = new Scene(p);
            Stage st = (Stage) ((Node) event0001.getSource()).getScene().getWindow();
            st.setScene(sc);
            st.show();
        });
    }


    public Menu(Integer id) {
        this.id = id;
    }

    public Menu(Integer id, String contenu, double prix) {
        this.id = id;
        this.contenu = contenu;
        this.prix = prix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Enseigne getEnseigneId() {
        return enseigneId;
    }

    public void setEnseigneId(Enseigne enseigneId) {
        this.enseigneId = enseigneId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Menu[ id=" + id + " ]";
    }
    
}
