/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.FosUser;
import edu.bonplan.entities.Plan;
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
 * @author darkz
 */
public class AdminPlanServices {
    
    
    public List<Plan> AfficherPlans() {
        List<Plan> maliste = new ArrayList<Plan>();
        UserServices uu =new UserServices();
        ImageServices i=new ImageServices();
        CategorieService c = new CategorieService();
        try {
            String req="SELECT * FROM Plan";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Plan p = new Plan();
            p.setId(rs.getInt("id"));
            p.setCategorieId(c.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(uu.AfficherUser((int)rs.getInt("user_id")));
            p.setImageId(i.AfficherImage((int)rs.getInt("image_id")));
            p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("Description"));
            p.setScore(rs.getInt("Score"));
            p.setReportnumber(rs.getInt("reportnumber"));
            p.setActive(rs.getBoolean("active"));
            p.setReported(rs.getBoolean("reported"));
            p.setDateAjout(rs.getDate("date_ajout"));
       
            
                maliste.add(p);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    
    
    public List<Plan> AfficherReported() {
        List<Plan> maliste = new ArrayList<Plan>();
        UserServices uu =new UserServices();
        ImageServices i=new ImageServices();
        CategorieService c = new CategorieService();
        try {
            String req="SELECT * FROM Plan WHERE reported=true ORDERBY reportnumber";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Plan p = new Plan();
            p.setId(rs.getInt("id"));
            p.setCategorieId(c.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(uu.AfficherUser((int)rs.getInt("user_id")));
            p.setImageId(i.AfficherImage((int)rs.getInt("image_id")));
            p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("Description"));
            p.setScore(rs.getInt("Score"));
            p.setReportnumber(rs.getInt("reportnumber"));
            p.setActive(rs.getBoolean("active"));
            p.setReported(rs.getBoolean("reported"));
            p.setDateAjout(rs.getDate("date_ajout"));
       
            
                maliste.add(p);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    
    public void DesactiverPlan(Plan p){
        try {
            
            String req="Update Plan SET active=? WHERE id=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setBoolean(1,false);
            ps.setInt(2,p.getId());
            ps.executeUpdate();
            
            UserServices u = new UserServices();
            FosUser user = u.AfficherUser(p.getUserId().getId());
            user.setScore(user.getScore()-20);
            req="Update fos_user SET score=? WHERE id=?";
            ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,user.getScore());
            ps.setInt(2,user.getId());
            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationdealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void AciverPlan(Plan p){
        try {
            
            String req="Update Plan SET active=? WHERE id=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setBoolean(1,true);
            ps.setInt(2,p.getId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationdealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Plan> AfficherPlansEnAttente() {
        List<Plan> maliste = new ArrayList<Plan>();
        UserServices uu =new UserServices();
        ImageServices i=new ImageServices();
        CategorieService c = new CategorieService();
        try {
            String req="SELECT * FROM Plan Where active=false and reported=false and reportnumber=0";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Plan p = new Plan();
            p.setId(rs.getInt("id"));
            p.setCategorieId(c.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(uu.AfficherUser((int)rs.getInt("user_id")));
            p.setImageId(i.AfficherImage((int)rs.getInt("image_id")));
            p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("Description"));
            p.setScore(rs.getInt("Score"));
            p.setReportnumber(rs.getInt("reportnumber"));
            p.setActive(rs.getBoolean("active"));
            p.setReported(rs.getBoolean("reported"));
            p.setDateAjout(rs.getDate("date_ajout"));
       
            
                maliste.add(p);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    
}
