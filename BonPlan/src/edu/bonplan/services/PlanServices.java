/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Categorie;
import edu.bonplan.entities.Deal;
import edu.bonplan.entities.FosUser;
import edu.bonplan.entities.Plan;
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
 * @author darkz
 */
public class PlanServices {
    
    
    public void AjouterPlan(Plan p) {
        
        try {
            String req1="insert into Image (id,url,alt) values (?,?,?)";
            PreparedStatement ps1= DataSource.getInstance().getCnx().prepareStatement(req1);
            ps1.setInt(1,p.getImageId().getId());
            ps1.setString(2,p.getImageId().getUrl() );
            ps1.setString(3,p.getImageId().getAlt() );
            ps1.executeUpdate();
            String req="insert into Plan (image_id,categorie_id,user_id,titre,score,date_ajout,reportnumber,reported,active,description) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,p.getImageId().getId());
            ps.setInt(2,p.getCategorieId().getId());
            ps.setInt(3,Vars.current_user.getId());
            ps.setString(4,p.getTitre());
            ps.setInt(5,p.getScore());
            ps.setDate(6,(Date)p.getDateAjout() );
            ps.setInt(7,p.getReportnumber());
            ps.setBoolean(8,p.getReported());
            ps.setBoolean(9, p.getActive() );
            ps.setString(10, p.getDescription());
            ps.executeUpdate();
            
            
            
            Vars.current_user.setScore(Vars.current_user.getScore()+20);
            req="Update Fos_User SET score=? WHERE id=?";
            ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,Vars.current_user.getScore());
            ps.setInt(2,Vars.current_user.getId());
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public Plan AfficherPlan(int x) {
        Plan p = new Plan();
        
        try {
            String req="SELECT * FROM Plan where id = ?";
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1,x);
            ResultSet rs=s.executeQuery();
            UserServices u = new UserServices();
            CategorieService i =new CategorieService();
            ImageServices img=new ImageServices();
            while(rs.next())
            {
                
                
            p.setId(rs.getInt("id"));
            p.setCategorieId(i.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(u.AfficherUser((int)rs.getInt("user_id")));
            p.setImageId(img.AfficherImage((int)rs.getInt("image_id")));
            p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("Description"));
            p.setScore(rs.getInt("Score"));
            p.setReportnumber(rs.getInt("reportnumber"));
            p.setActive(rs.getBoolean("active"));
            p.setReported(rs.getBoolean("reported"));
            p.setDateAjout(rs.getDate("date_ajout"));
            //p.setImageId(img.AfficherImage((Integer) rs.getInt("image_id")));
            
            
            
            
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return p;
    }
     
     public List<Plan> AfficherPlans() {
             List<Plan> maliste = new ArrayList<Plan>();
        try {
            String req="SELECT * FROM Plan where active=1 and user_id!="+Vars.current_user.getId()+" order by date_ajout";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            CategorieService i =new CategorieService();
            ImageServices img=new ImageServices();
            UserServices us= new UserServices();
            while(rs.next())
            {
            Plan p = new Plan();
            p.setId(rs.getInt("id"));
            p.setCategorieId(i.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(us.AfficherUser((int)rs.getInt("user_id")));
            p.setImageId(img.AfficherImage((int)rs.getInt("image_id")));
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
     
     
     
     public List<Plan> AfficherMesPlans() {
        List<Plan> maliste = new ArrayList<Plan>();
        try {
            String req="SELECT * FROM Plan WHERE user_id="+Vars.current_user.getId();
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            CategorieService i =new CategorieService();
            ImageServices img=new ImageServices();
            while(rs.next())
            {
            Plan p = new Plan();
            p.setId(rs.getInt("id"));
            p.setCategorieId(i.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(Vars.current_user);
            p.setImageId(img.AfficherImage((int)rs.getInt("image_id")));
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
     
     
      public void ModifierPlan(Plan p) {
        try {
            String req="Update Plan SET description=?,titre=? ,categorie_id=? where id=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1,p.getDescription());
            ps.setString(2,p.getTitre());
            
            ps.setInt(3,p.getCategorieId().getId());
            ps.setInt(4,p.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void SupprimerPlan(int x){
        try {
            String req="DELETE FROM commentaire WHERE plan_id=? ";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,x);
            ps.executeUpdate();
            req="DELETE FROM Plan WHERE id=? ";
            PreparedStatement ps2=DataSource.getInstance().getCnx().prepareStatement(req);
            ps2.setInt(1,x);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Plan> RechercheScorePlan(){
        List<Plan> maliste = new ArrayList<Plan>();
        UserServices us = new UserServices();
        try {
            String req="SELECT * FROM Plan where active=1 and user_id!='"+Vars.current_user.getId()+"' order by score";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            CategorieService i =new CategorieService();
            ImageServices img=new ImageServices();
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Plan p = new Plan();
            p.setId(rs.getInt("id"));
            p.setCategorieId(i.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(us.AfficherUser((int)rs.getInt("user_id")));
            p.setImageId(img.AfficherImage((int)rs.getInt("image_id")));
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
      
    public List<Plan> RechercheCategoriePlan(String c){
        List<Plan> maliste = new ArrayList<Plan>();
        
        try {
            CategorieService cs = new CategorieService();
            Categorie cate = cs.getCategorie(c);
            UserServices u = new UserServices();
            int cat = cate.getId();
            String req="SELECT * FROM Plan where active=1 and categorie_id='"+cat+"' and user_id!="+Vars.current_user.getId()+"";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            ImageServices img=new ImageServices();
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Plan p = new Plan();
            p.setId(rs.getInt("id"));
            p.setCategorieId(cate);
            p.setUserId(u.AfficherUser(rs.getInt("user_id")));
            p.setImageId(img.AfficherImage((int)rs.getInt("image_id")));
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
    
    
    public List<Plan> RechercheNomPlan(String c){
        List<Plan> maliste = new ArrayList<Plan>();
        try {
            String req="SELECT * FROM Plan where titre LIKE'%"+c+"%'";
            UserServices u = new UserServices();
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs=s.executeQuery();
            CategorieService i =new CategorieService();
            ImageServices img=new ImageServices();
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Plan p = new Plan();
            p.setId(rs.getInt("id"));
            p.setCategorieId(i.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(u.AfficherUser((int)rs.getInt("user_id")));
            p.setImageId(img.AfficherImage((int)rs.getInt("image_id")));
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
    
    
    public void SignalerPlan(int x) throws SQLException{
        Plan p = new Plan();
        try {
            String req="SELECT * FROM Plan where id = ?";
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1,x);
            ResultSet rs=s.executeQuery();
            UserServices u = new UserServices();
            CategorieService i =new CategorieService();
            ImageServices img=new ImageServices();
            while(rs.next())
            {
                
                
            p.setId(rs.getInt("id"));
            p.setCategorieId(i.AfficherCategorie((int)rs.getInt("categorie_id")));
            p.setUserId(u.AfficherUser((int)rs.getInt("user_id")));
            p.setImageId(img.AfficherImage((int)rs.getInt("image_id")));
            p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("Description"));
            p.setScore(rs.getInt("Score"));
            p.setReportnumber(rs.getInt("reportnumber")+1);
            p.setActive(rs.getBoolean("active"));
            p.setReported(rs.getBoolean("reported"));
            p.setDateAjout(rs.getDate("date_ajout"));
            req="Update Plan SET reportnumber=? WHERE id=?";
                s=DataSource.getInstance().getCnx().prepareStatement(req);
                s.setInt(1,p.getReportnumber());
                s.setInt(1,p.getId());
                s.executeUpdate();
                
                
            
            if (p.getReportnumber()>= 30){
                req="Update Plan SET active=false WHERE id=?";
                s=DataSource.getInstance().getCnx().prepareStatement(req);
                s.setInt(1,x);
                s.executeUpdate();
                FosUser user = u.AfficherUser(p.getUserId().getId());
                
                req="Update fos_user SET report=? WHERE id=?";
                s=DataSource.getInstance().getCnx().prepareStatement(req);
                s.setInt(1,user.getReport()+1);
                s.setInt(2,user.getId());
                s.executeUpdate();
                
            }
            
            
            
            
            
            
            
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    

}
        
    }
    


