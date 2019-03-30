  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.util.DataSource;
import edu.bonplan.entities.Deal;
import edu.bonplan.entities.Enseigne;
import edu.bonplan.entities.FosUser;
import edu.bonplan.entities.Image;
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
public class DealServices {
    public void AjouterDeal(Deal d, int x) {
        
        try {
            String req1="insert into Image (id,url,alt) values (?,?,?)";
            PreparedStatement ps1= DataSource.getInstance().getCnx().prepareStatement(req1);
            ps1.setInt(1, d.getImageId().getId());
            ps1.setString(2,d.getImageId().getUrl());
            ps1.setString(3,d.getImageId().getAlt() );
            
            ps1.executeUpdate();
            
            String req="insert into Deal (image_id,enseigne_id,nom,score,prix,tred,visite,datedebut,datefin,active,description) values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,d.getImageId().getId());
            ps.setInt(2,x );
            ps.setString(3,d.getNom() );
            ps.setInt(4,d.getScore() );
            ps.setDouble(5,d.getPrix() );
            ps.setInt(6,d.getTred() );
            ps.setInt(7,d.getVisite() );
            ps.setDate(8,(Date)d.getDatedebut());
            ps.setDate(9,(Date)d.getDatefin());
            ps.setBoolean(10, true );
            ps.setString(11, d.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Deal AfficherDeal(int x) {
        ImageServices i=new ImageServices();
        EnseigneService e = new EnseigneService();
        Deal d = new Deal();
        try {
            String req="SELECT * FROM Deal where id = ?";
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1,x);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
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
            d.setImageId(i.AfficherImage((Integer) rs.getInt("image_id")));
            String req1="Update Deal SET visite = ? where id = ?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req1);
            ps.setInt(1,d.getVisite()+1);
            ps.setInt(2,d.getId());
            ps.executeUpdate();
            
            
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return d;
    }
    public List<Deal> AfficherDeals(FosUser u) {
        List<Deal> maliste = new ArrayList<Deal>();
        UserServices uu=new UserServices();
        ImageServices i=new ImageServices();
        EnseigneService e = new EnseigneService();
        try {
            String req="SELECT d.* FROM Deal d join enseigne e  where d.enseigne_id=e.id and e.user_id!="+u.getId();
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
    public List<Deal> AfficherMesDeals(FosUser u) {
        List<Deal> maliste = new ArrayList<Deal>();
        UserServices uu=new UserServices();
        ImageServices i=new ImageServices();
        EnseigneService e = new EnseigneService();
        try {
            String req="SELECT d.* FROM Deal d join enseigne e  where d.active=1 and d.enseigne_id=e.id and e.user_id="+u.getId();
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
    public void ModifierDeal(Deal d) {
        try {

            String req="Update Deal SET nom=?,score=?,prix=?,tred=?,datedebut=?,datefin=?,description=? where id=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1,d.getNom());
            ps.setInt(2,d.getScore());
            ps.setDouble(3,d.getPrix() );
            ps.setInt(4,d.getTred() );
            ps.setDate(5,(Date)d.getDatedebut());
            ps.setDate(6,(Date) d.getDatefin());
            ps.setString(7, d.getDescription());
            ps.setInt(8,d.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void SupprimerDeal(int x){
        try {
            String req="DELETE FROM Deal WHERE id=?";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,x);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Deal> RechercheScoreDeal(FosUser u){
        List<Deal> maliste = new ArrayList<Deal>();
        UserServices uu=new UserServices();
        ImageServices i=new ImageServices();
        EnseigneService e = new EnseigneService();
        try {
            String req="SELECT d.* FROM Deal d join enseigne e join fos_user u  where d.active=1 and d.enseigne_id=e.id and e.user_id=u.id  and d.score<=u.score and e.user_id!="+u.getId();
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
    public List<Deal> RechercheCategorieDeal(FosUser u, String c){
        List<Deal> maliste = new ArrayList<Deal>();
        UserServices uu=new UserServices();
        ImageServices i=new ImageServices();
        EnseigneService e = new EnseigneService();
        try {
            String req="SELECT d.* FROM Deal d join enseigne e join categorie cat join fos_user u  where d.active=1 and e.categorie_id=cat.id and d.enseigne_id=e.id and e.user_id=u.id and e.user_id!="+u.getId()+" and cat.nom='"+c+"'";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
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
    public List<Deal> RechercheVillesDeal(FosUser u, String c){
        List<Deal> maliste = new ArrayList<Deal>();
        UserServices uu=new UserServices();
        ImageServices i=new ImageServices();
        EnseigneService e = new EnseigneService();
        try {
            
            String req="SELECT d.* FROM Deal d join enseigne e join adresse a join fos_user u  where d.active=1 and e.adresse_id=a.id and d.enseigne_id=e.id and e.user_id=u.id and e.user_id!="+u.getId()+" and a.ville Like '%"+c+"%'";
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
}
