/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
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
@Table(name = "deal")
@NamedQueries({
    @NamedQuery(name = "Deal.findAll", query = "SELECT d FROM Deal d")})
public class Deal implements Serializable {

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
    @Column(name = "score")
    private int score;
    @Basic(optional = false)
    @Column(name = "prix")
    private double prix;
    @Basic(optional = false)
    @Column(name = "tred")
    private int tred;
    @Basic(optional = false)
    @Column(name = "visite")
    private int visite;
    @Basic(optional = false)
    @Column(name = "datedebut")
    @Temporal(TemporalType.DATE)
    private Date datedebut;
    @Basic(optional = false)
    @Column(name = "datefin")
    @Temporal(TemporalType.DATE)
    private Date datefin;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Image imageId;
    @JoinColumn(name = "enseigne_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Enseigne enseigneId;
    @OneToMany(mappedBy = "dealId")
    private List<Comment> commentList;
    @OneToMany(mappedBy = "dealId")
    private List<Reservationdeal> reservationdealList;

    public Deal() {
    }

    public Deal(Integer id) {
        this.id = id;
    }

    public Deal(Integer id, String nom, int score, double prix, int tred, int visite, Date datedebut, Date datefin, boolean active, String description) {
        this.id = id;
        this.nom = nom;
        this.score = score;
        this.prix = prix;
        this.tred = tred;
        this.visite = visite;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.active = active;
        this.description = description;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getTred() {
        return tred;
    }

    public void setTred(int tred) {
        this.tred = tred;
    }

    public int getVisite() {
        return visite;
    }

    public void setVisite(int visite) {
        this.visite = visite;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Reservationdeal> getReservationdealList() {
        return reservationdealList;
    }

    public void setReservationdealList(List<Reservationdeal> reservationdealList) {
        this.reservationdealList = reservationdealList;
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
        if (!(object instanceof Deal)) {
            return false;
        }
        Deal other = (Deal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Deal{" + "id=" + id + ", nom=" + nom + ", score=" + score + ", prix=" + prix + ", tred=" + tred + ", visite=" + visite + ", datedebut=" + datedebut + ", datefin=" + datefin + ", active=" + active + ", description=" + description + ", imageId=" + imageId + ", enseigneId=" + enseigneId + ", commentList=" + commentList + ", reservationdealList=" + reservationdealList + '}';
    }

 
}
