/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Deal;
import edu.bonplan.entities.FosUser;
import edu.bonplan.entities.Reservationdeal;
import edu.bonplan.util.DataSource;
import edu.bonplan.util.Vars;
import java.sql.Date;
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
public class ReservationdealServices {
    public void AjouterReservationDeal(Deal d,FosUser u,int nbr) {
        EnseigneService e = new EnseigneService();
        
        try {
            String req="insert into Reservationdeal (deal_id,user_id,payer,nbr) values (?,?,?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,d.getId());
            ps.setInt(2,u.getId() );
            ps.setBoolean(3,false);
            ps.setInt(4,nbr );
            ps.executeUpdate();
            e.ModifierCapaciteEnseigne(e.AfficherEnseigne(d.getEnseigneId().getId()), nbr);
            
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public List<Reservationdeal> AfficherReservationDeal(FosUser u) {
        List<Reservationdeal> maliste = new ArrayList<>();
        DealServices ds =new DealServices();
        UserServices us =new UserServices();
        try {
            String req="SELECT * FROM `reservationdeal` WHERE user_id="+u.getId();
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Reservationdeal rd = new Reservationdeal();
            rd.setId(rs.getInt("id"));
            rd.setDealId(ds.AfficherDeal((Integer) rs.getInt("deal_id")));
            rd.setUserId(us.AfficherUser(u.getId()));
            rd.setPayer(rs.getBoolean("payer"));
            rd.setNbr(rs.getInt("nbr"));
            maliste.add(rd);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    public void SupprimerReservationDeal(int x){
        try {
            String req="DELETE FROM Reservationdeal WHERE id=?";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,x);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void PayerScore(Reservationdeal rd){
        try {
            
            if ((rd.getNbr()*rd.getDealId().getScore())<=rd.getUserId().getScore())
            {
            String req="Update Reservationdeal SET payer=? WHERE id=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setBoolean(1,true);
            ps.setInt(2,rd.getId());
            ps.executeUpdate();
            
            String req1="Update Fos_user SET score=? WHERE id=?";
            PreparedStatement ps1= DataSource.getInstance().getCnx().prepareStatement(req1);
            ps1.setInt(1,(rd.getUserId().getScore()-(rd.getNbr()*rd.getDealId().getScore())));
            ps1.setInt(2,rd.getUserId().getId());
            ps1.executeUpdate();
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationdealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
