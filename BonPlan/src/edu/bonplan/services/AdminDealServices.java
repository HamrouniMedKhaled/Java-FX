/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Deal;
import edu.bonplan.entities.FosUser;
import edu.bonplan.entities.Reservationdeal;
import edu.bonplan.entities.Stat;
import edu.bonplan.util.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamrouni
 */
public class AdminDealServices {
    public List<Deal> AfficherDeals() {
        List<Deal> maliste = new ArrayList<Deal>();
        UserServices uu=new UserServices();
        ImageServices i=new ImageServices();
        EnseigneService e = new EnseigneService();
        try {
            String req="SELECT * FROM Deal";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Deal d = new Deal();
            d.setId(rs.getInt("id"));
            d.setNom(rs.getString("nom"));
            d.setScore(rs.getInt("score"));
            d.setPrix(rs.getDouble("prix"));
            d.setTred(rs.getInt("tred"));
            d.setVisite(rs.getInt("visite"));
            d.setDatedebut(rs.getDate("datedebut"));
            d.setDatefin(rs.getDate("datefin"));
            d.setActive(rs.getBoolean("active"));
            d.setDescription(rs.getString("description"));
            d.setEnseigneId(e.AfficherEnseigne((Integer) rs.getInt("enseigne_id")));
            d.getEnseigneId().setUserId(uu.AfficherUser(d.getEnseigneId().getUserId().getId()));
            d.setImageId(i.AfficherImage((Integer) rs.getInt("image_id")));
            
                maliste.add(d);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    public void DesactiverDeal(Deal d){
        try {
            
            String req="Update Deal SET active=? WHERE id=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setBoolean(1,false);
            ps.setInt(2,d.getId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationdealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Stat> StatisticNdv(){
    List<Stat> maliste = new ArrayList<Stat>();
        try {
            String req="SELECT COUNT(d.id) AS nbr, a.ville AS ville FROM Deal d JOIN enseigne e JOIN adresse a WHERE d.enseigne_id=e.id AND e.adresse_id=a.id GROUP BY a.ville";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Stat sv = new Stat();
            sv.setNbr(rs.getInt("nbr"));
            sv.setNom(rs.getString("ville"));
            
            
                maliste.add(sv);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
        
    }    
    public List<Stat> StatisticNde(){
    List<Stat> maliste = new ArrayList<Stat>();
        try {
            String req="SELECT COUNT(d.id) AS nbr, enseigne.nom AS ens FROM Deal d JOIN enseigne WHERE d.enseigne_id=enseigne.id GROUP BY enseigne.nom";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Stat sv = new Stat();
            sv.setNbr(rs.getInt("nbr"));
            sv.setNom(rs.getString("ens"));
            
            
                maliste.add(sv);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
        
    }    
    public List<Stat> StatisticNrv(){
    List<Stat> maliste = new ArrayList<Stat>();
        try {
            String req="SELECT COUNT(r.id) AS nbr, adresse.ville AS ville FROM Reservationdeal r JOIN deal JOIN enseigne JOIN adresse WHERE r.deal_id=deal.id AND deal.enseigne_id=enseigne.id AND enseigne.adresse_id=adresse.id GROUP BY adresse.ville";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Stat sv = new Stat();
            sv.setNbr(rs.getInt("nbr"));
            sv.setNom(rs.getString("ville"));
            
            
                maliste.add(sv);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
        
    }
    public List<Stat> StatisticNre(){
    List<Stat> maliste = new ArrayList<Stat>();
        try {
            String req="SELECT COUNT(r.id) AS nbr, enseigne.nom AS ens FROM Reservationdeal r JOIN deal JOIN enseigne WHERE r.deal_id=deal.id AND deal.enseigne_id=enseigne.id GROUP BY enseigne.nom";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Stat sv = new Stat();
            sv.setNbr(rs.getInt("nbr"));
            sv.setNom(rs.getString("ens"));
            
            
                maliste.add(sv);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
        
    }
}
