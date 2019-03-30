/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.entities;

import edu.bonplan.gui.Evenement.EvenementShow;
import edu.bonplan.gui.Evenement.EvenmentUpdate;
import edu.bonplan.services.EvenementService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Hamrouni
 */
@Entity
@Table(name = "evenement")
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e")})
public class Evenement implements Serializable {

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
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(mappedBy = "evenementId")
    private List<Reservationevent> reservationeventList;
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @OneToOne
    private Image imageId;
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

    public Evenement() {
        Delete = new Button("Supprimer");
        Delete.setOnAction((event -> {
            EvenementService envs = new EvenementService();
            envs.delete(id);

            Parent p =null;
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/edu/bonplan/gui/Evenement/evenementShow.fxml"));
            System.out.println(loader.getLocation());
            try{
                p = loader.load();
            }catch (Exception er){
                er.printStackTrace();
            }
            EvenementShow c = loader.getController();
            c.buildTabel();
            Scene sc = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(sc);
            st.show();
        }));
        Update = new Button("Modifier");
        Update.setOnAction(event0001 -> {
            Parent p1 =null;
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("/edu/bonplan/gui/Evenement/evenmentUpdate.fxml"));
            System.out.println("update :"+ loader1.getLocation());
            try{
                p1 = loader1.load();
            }catch (Exception er){
                System.out.println("hné ?");
                er.printStackTrace();
            }
            EvenmentUpdate c = loader1.getController();
            //c.setInfo(id);
            c.setInfo(this);

            Scene sc = new Scene(p1);
            Stage st = (Stage) ((Node) event0001.getSource()).getScene().getWindow();
            st.setScene(sc);
            st.show();
        });
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(Integer id, String nom, String description, Date date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Reservationevent> getReservationeventList() {
        return reservationeventList;
    }

    public void setReservationeventList(List<Reservationevent> reservationeventList) {
        this.reservationeventList = reservationeventList;
    }

    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
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
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Evenement[ id=" + id + " ]";
    }
    
}
