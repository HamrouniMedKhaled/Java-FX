/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Enseigne;
import edu.bonplan.entities.Evenement;
import edu.bonplan.util.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class EvenementService {
        public List<Evenement> getallEvenement(){
        List<Evenement> le = new ArrayList<Evenement>();
           String req= "select * from evenement";
           try{
             Statement s=DataSource.getInstance().getCnx().createStatement();
             ResultSet rs=s.executeQuery(req);     
            while (rs.next()){
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDescription(rs.getString("description"));
                e.setDate(rs.getDate("date"));
                Enseigne en = new Enseigne(rs.getInt("enseigne_id"));
                e.setEnseigneId(en);
                le.add(e);
            }
               
           }catch(SQLException err){
               System.out.println("erreur getall envenement");
           }
        return le;
    }
    
    public Evenement getEvenementById(int id)
    {
        Evenement e= new Evenement();
            String req = "select * from evenement where id=?";
            try{
                PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
            while (rs.next()){

                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                ImageServices imageServices=new ImageServices();
                e.setImageId(imageServices.AfficherImage(rs.getInt("image_id")));
                EnseigneService enseigneService=new EnseigneService();
                e.setEnseigneId(enseigneService.getEnseigneById(rs.getInt("enseigne_id")));
                System.out.println(e.getEnseigneId().getNom());
                e.setDescription(rs.getString("description"));
                e.setDate(rs.getDate("date"));
            }
                
            }catch(SQLException err){
                System.out.println("erreur dans la methode getevenementby id");
                err.printStackTrace();
            }
            
        return e;
    }
    public void insert (Evenement e){
        String req = "insert into evenement (nom,description,date,image_id,enseigne_id) values(?,?,?,?,?)";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getDescription());
            ps.setDate(3, (Date) e.getDate());
            ps.setInt(4, e.getImageId().getId());
            ps.setInt(5, e.getEnseigneId().getId());
            ps.executeUpdate();
        }catch(SQLException err){
            err.printStackTrace();
            System.out.println("erreur dans la methode insert");
        }
    }
    public void delete (int id){
        String req="delete from evenement where id=?";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
           ps.setInt(1, id);
           ps.executeUpdate();
        }catch(SQLException er){
            System.out.println("delete erreur enseigne");
            er.printStackTrace();
        }
    }
    public void update (Evenement e , int id){
        String req="update evenement set nom=?,description=?,date=?,enseigne_id=? where id=?";
       try{System.out.println("hii"+id);
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getDescription());
            ps.setDate(3, (Date) e.getDate());
          //  ps.setInt(4, e.getImageId().getId());
            ps.setInt(4, e.getEnseigneId().getId());
            ps.setInt(5, id);
            ps.executeUpdate();
        }catch(SQLException err){
            err.printStackTrace();
            System.out.println("erreur dans la methode insert");
        }
    }

    public Evenement getEventByNom(String search) {

        Evenement e= new Evenement();
        String req = "select * from evenement where nom='"+search+"'";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                ImageServices imageServices=new ImageServices();
                e.setImageId(imageServices.AfficherImage(rs.getInt("image_id")));
                EnseigneService enseigneService=new EnseigneService();
                e.setEnseigneId(enseigneService.getEnseigneById(rs.getInt("enseigne_id")));
                System.out.println(e.getEnseigneId().getNom());
                e.setDescription(rs.getString("description"));
                e.setDate(rs.getDate("date"));
            }

        }catch(SQLException err){
            System.out.println("erreur dans la methode getevenementby id");
            err.printStackTrace();
        }

        return e;
    }
}
