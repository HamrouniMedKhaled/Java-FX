/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.entities;

import edu.bonplan.gui.Enseigne.EnseigneShow;
import edu.bonplan.gui.Enseigne.EnseigneUpdate;
import edu.bonplan.services.EnseigneService;
import java.io.Serializable;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Hamrouni
 */
@Entity
@Table(name = "enseigne")
@NamedQueries({
    @NamedQuery(name = "Enseigne.findAll", query = "SELECT e FROM Enseigne e")})
public class Enseigne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "capacite")
    private int capacite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enseigneId")
    private List<Deal> dealList;
    @OneToMany(mappedBy = "enseigneId")
    private List<Menu> menuList;
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @OneToOne
    private Image imageId;
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    @OneToOne
    private Adresse adresseId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private FosUser userId;
    @JoinColumn(name = "categorie_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categorie categorieId;
    @OneToMany(mappedBy = "enseigneId")
    private List<Reservation> reservationList;
    @OneToMany(mappedBy = "enseigneId")
    private List<Evenement> evenementList;
    
        Button Delete;
    Button Update;

    public Button getDelete() {
        return Delete;
    }

    public Button getUpdate() {
        return Update;
    }

    public Enseigne() {
        Delete = new Button("Supprimer");
        Delete.setOnAction((event -> {
            EnseigneService ens= new EnseigneService();
            ens.delete(id);

            Parent p =null;
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/edu/bonplan/gui/Enseigne/enseigneShow.fxml"));
            System.out.println(loader.getLocation());
            try{
                p = loader.load();
            }catch (Exception er){
                er.printStackTrace();
            }
            EnseigneShow c = loader.getController();
            c.buildTabel();
            Scene sc = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(sc);
            st.show();
        }));
        Update = new Button("Modifier");
        Update.setOnAction(event0001 -> {
            Parent p =null;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/bonplan/gui/Enseigne/enseigneUpdate.fxml"));
            System.out.println("update :"+ loader.getLocation());
            try{
                p = loader.load();
            }catch (Exception er){
                System.out.println("hné ?");
                er.printStackTrace();
            }
            EnseigneUpdate c = loader.getController();
            //c.setInfo(id);
            c.initUpdate(id);

            Scene sc = new Scene(p);
            Stage st = (Stage) ((Node) event0001.getSource()).getScene().getWindow();
            st.setScene(sc);
            st.show();
        });
    }

   
    public Enseigne(Integer id) {
        this.id = id;
    }

    public Enseigne(Integer id, String nom, String description, boolean active, int capacite) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.active = active;
        this.capacite = capacite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Deal> getDealList() {
        return dealList;
    }

    public void setDealList(List<Deal> dealList) {
        this.dealList = dealList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
    }

    public Adresse getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Adresse adresseId) {
        this.adresseId = adresseId;
    }

    public FosUser getUserId() {
        return userId;
    }

    public void setUserId(FosUser userId) {
        this.userId = userId;
    }

    public Categorie getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Categorie categorieId) {
        this.categorieId = categorieId;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<Evenement> getEvenementList() {
        return evenementList;
    }

    public void setEvenementList(List<Evenement> evenementList) {
        this.evenementList = evenementList;
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
        if (!(object instanceof Enseigne)) {
            return false;
        }
        Enseigne other = (Enseigne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Enseigne[ id=" + id + " ]";
    }
    
}
