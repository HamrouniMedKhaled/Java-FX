/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Adresse;
import edu.bonplan.entities.Categorie;
import edu.bonplan.entities.Deal;
import edu.bonplan.entities.Enseigne;
import edu.bonplan.entities.FosUser;
import edu.bonplan.entities.Image;
import edu.bonplan.util.DataSource;
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
public class EnseigneService {
    public Enseigne AfficherEnseigne(int x) throws SQLException {
            CategorieService c=new CategorieService();
            Enseigne i = new Enseigne(1);
            UserServices u =new UserServices();
            String req="SELECT * FROM Enseigne where id = ?";
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1,x);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
              i.setId(rs.getInt("id"));
              i.setCategorieId(c.AfficherCategorie((Integer) rs.getInt("categorie_id")));
              i.setCapacite(rs.getInt("capacite"));
              i.setNom(rs.getString("nom"));
              i.setUserId(u.AfficherUser((Integer) rs.getInt("user_id")));
                
              
            }
            return i;
        }
public void ModifierCapaciteEnseigne(Enseigne e ,int x) {
        try {
            String req="Update Enseigne SET capacite=? where id=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,e.getCapacite()-x);
            ps.setInt(2,e.getId());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
 public List<Enseigne> getallEnseigne(){
        ArrayList<Enseigne> le = new ArrayList<Enseigne>();
        String req = "select * from enseigne";
        try{
            
           Statement s=DataSource.getInstance().getCnx().createStatement();
             ResultSet rs=s.executeQuery(req);     
            while (rs.next()){
                Enseigne en = new Enseigne();
                en.setId(rs.getInt("id"));
                en.setNom(rs.getString("nom"));
                en.setDescription(rs.getString("description"));
                en.setActive(rs.getBoolean("active"));
                en.setCapacite(rs.getInt("capacite"));
                le.add(en);
            }  
        }catch (SQLException e){
            System.out.println("erreur get all enseigne");
            e.printStackTrace();
        }
        
        return le;
    }

    public Enseigne getEnseigneById(int id){
        Enseigne en= new Enseigne();
        String req="select * from enseigne where id=?";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                en.setNom(rs.getString("nom"));
                en.setDescription(rs.getString("description"));
                en.setActive(rs.getBoolean("active"));
                en.setCapacite(rs.getInt("capacite"));
                AdresseService as=new AdresseService();
                int idadresse=rs.getInt("adresse_id");
                System.out.println(idadresse+"idadresse");
                Adresse adresse=as.getAdresseWhereId(idadresse);
                en.setAdresseId(adresse);
                System.out.println(adresse.getPays()+"aazdedadazd");

                en.setId(rs.getInt("id"));
                CategorieService cs = new CategorieService();

                Categorie categorie=cs.AfficherCategorie(rs.getInt("categorie_id"));
                en.setCategorieId(categorie);
                System.out.println(categorie.getNom()+"categories");
                en.setImageId(new Image(rs.getInt("image_id")));
                en.setUserId(new FosUser(rs.getInt("user_id")));

            }
        }catch(SQLException e){
            System.out.println("probeme get enseigne by id");
            e.printStackTrace();
        }
        return en;
    }

    public Enseigne getEnseigneByNom(String nom){
        Enseigne en= new Enseigne();
        en.setNom(nom);
        String req="select * from enseigne where nom = '"+nom+"'";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                System.out.println("nom"+rs.getString("nom")+" "+rs.getString("description"));
                    en.setDescription(rs.getString("description"));
                    en.setActive(rs.getBoolean("active"));
                    en.setCapacite(rs.getInt("capacite"));
                    AdresseService as=new AdresseService();
                    int idadresse=rs.getInt("adresse_id");
                    System.out.println(idadresse+"idadresse");
                    Adresse adresse=as.getAdresseWhereId(idadresse);
                    en.setAdresseId(adresse);
                    System.out.println(adresse.getPays()+"aazdedadazd");

                    en.setId(rs.getInt("id"));
                    CategorieService cs = new CategorieService();

                    Categorie categorie=cs.AfficherCategorie(rs.getInt("categorie_id"));
                    en.setCategorieId(categorie);
                    System.out.println(categorie.getNom()+"categories");
                    en.setImageId(new Image(rs.getInt("image_id")));
                    en.setUserId(new FosUser(rs.getInt("user_id")));



            }
            else {
                System.out.println("no find");
            }
        }catch(SQLException e){
            System.out.println("probeme get enseigne by id");
            e.printStackTrace();
        }
        return en;
    }


    public void insert (Enseigne e){
      String req="insert into enseigne (nom,description,active,capacite,image_id,adresse_id,user_id,categorie_id) values(?,?,?,?,?,?,?,?)";
      try{
           PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getDescription());
            ps.setBoolean(3, e.getActive());
            ps.setInt(4, e.getCapacite());
            ps.setInt(5,e.getImageId().getId());
            ps.setInt(6, e.getAdresseId().getId());
            ps.setInt(7, e.getUserId().getId());
            ps.setInt(8,e.getCategorieId().getId());
          System.out.println(e.getCategorieId().getId());
            ps.executeUpdate();
          
      }catch(SQLException er){
          System.out.println("erreur dans la methode d'ajout");
          er.printStackTrace();
      }
    }
    public void delete (int id){
        String req= "delete from enseigne where id=?";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
           ps.setInt(1, id);
           ps.executeUpdate();
        }catch(SQLException er){
            System.out.println("delete erreur enseigne");
            er.printStackTrace();
        }
        
    }
    public void update (Enseigne e , int id){
       String req="update enseigne set nom=?,description=?,active=?,capacite=?,adresse_id=? where id=?";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getDescription());
            ps.setBoolean(3, e.getActive());
            ps.setInt(4, e.getCapacite());
//            ps.setInt(5,e.getImageId().getId());
            ps.setInt(5, e.getAdresseId().getId());
       //     ps.setInt(7, e.getUserId().getId());
            ps.setInt(6,id);
            ps.executeUpdate();
        }catch(SQLException er){
            System.out.println("erreur update");
            er.printStackTrace();
         }
    }
}
