/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Deal;
import edu.bonplan.entities.FosUser;
import edu.bonplan.util.DataSource;

import edu.bonplan.entities.Reservation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.bonplan.services.UserServices;
import edu.bonplan.util.Vars;

/**
 *
 * @author yassine
 */
public class ReservationEnseigneService {
    public void ajouterReservationEnseigne(Reservation r) {
        
        try {
            String req="insert into reservation (enseigne_id,user_id,datereservation,nbplaces) values (?,?,?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,Vars.current_enseigne.getId());
            ps.setInt(2,Vars.current_user.getId());
            ps.setDate(3, (Date) r.getDatereservation());
            ps.setInt(4,r.getNbplaces() );
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationEnseigneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Reservation afficher(int x){
         Reservation r = new Reservation();
         EnseigneService e = new EnseigneService();
        try {
           
            String req="SELECT * FROM reservation where id="+x;
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                r.setId(rs.getInt("id"));
                r.setNbplaces(rs.getInt("nbplaces"));
                r.setDatereservation(rs.getDate("datereservation"));
                r.setEnseigneId(e.AfficherEnseigne((Integer) rs.getInt("enseigne_id")));
            }   
        } catch (SQLException ex) {
            Logger.getLogger(ReservationEnseigneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public List<Reservation> lister() throws SQLException {
        UserServices userserv=new UserServices();
        EnseigneService enseignserv=new EnseigneService();
        List<Reservation> maliste = new ArrayList<Reservation>();
        
            String req="SELECT * FROM reservation ";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Reservation r = new Reservation();
            r.setId(rs.getInt("id"));
            r.setUserId(userserv.AfficherUser(rs.getInt("user_id")));
            r.setEnseigneId(enseignserv.AfficherEnseigne(rs.getInt("enseigne_id")));
            r.setDatereservation(rs.getDate("Datereservation"));
            r.setNbplaces(rs.getInt("Nbplaces"));
            
            maliste.add(r);
            }  
       
         
        return maliste;
    }
    
    public List<Reservation> AfficherMesReservation(FosUser u) throws SQLException {
        List<Reservation> maliste = new ArrayList<Reservation>();
        UserServices uu=new UserServices();
        EnseigneService e = new EnseigneService();
       
            String req="SELECT r.* FROM Reservation r  where r.user_id="+u.getId()+";";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
            Reservation r = new Reservation();
            r.setId(rs.getInt("id"));
            r.setDatereservation(rs.getDate("Datereservation"));
            r.setNbplaces(rs.getInt("Nbplaces"));
            
            r.setEnseigneId(e.AfficherEnseigne((Integer) rs.getInt("enseigne_id")));
            r.getEnseigneId().setUserId(uu.AfficherUser(r.getEnseigneId().getUserId().getId()));
                
                maliste.add(r);
            }
        
        return maliste;
    }
    
    public void ModifierReservationEnseigne(Reservation r) {
        try {

            String req="Update Reservation SET datereservation=?,nbplaces=?  where id=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setDate(1, (Date) r.getDatereservation());
            ps.setInt(2,r.getNbplaces() );
            ps.setInt(3,r.getId());
            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationEnseigneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void SupprimerReservationEnseigne(int x){
        try {
            String req="DELETE FROM Reservation WHERE id=?";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,x);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationEnseigneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
