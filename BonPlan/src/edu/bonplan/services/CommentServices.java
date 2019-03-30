/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Badge;
import edu.bonplan.entities.BadgeUnlock;
import edu.bonplan.entities.Comment;
import edu.bonplan.entities.FosUser;
import edu.bonplan.util.DataSource;
import edu.bonplan.util.Vars;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author morrta
 */
public class CommentServices {
    //ajout d'un commentaire
    public void addComment(Comment c ){
        String req = "insert into comment(content,user_id,deal_id) values(?,?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            
            ps.setString(1, c.getContent());
            ps.setInt(2, c.getUserId().getId());
            ps.setInt(3, c.getDealId().getId());
            ps.executeUpdate();
            System.out.println("khaledd");
       
        } catch (SQLException ex) {
            Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //supprimer le commentaire par Id
    public void removeCommentById(int id ){
    
        String req = "delete from comment where id=?";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
        
    }
    //récupérer la liste de tous les commentaires (utiles dans l'interface admin)
    public List<Comment> getAll(){
        List<Comment> commentList = new ArrayList<>();
        try {
            String req = "SELECT * FROM comment ";
            Statement s = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs=s.executeQuery(req);
            
            while(rs.next()){
            Comment c = new Comment();
            
            c.setId(rs.getInt("id"));
            c.setContent(rs.getString("content"));
            commentList.add(c);
            }
            
        } catch (SQLException ex) {
             Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentList;
    }
    //récupérer les commentaires poster dans deal
    public List<Comment> getAllForDeal(int did){
        List<Comment> commentList = new ArrayList<>();
           UserServices e = new UserServices();
        try {
            String req = "select * from comment where deal_id=?";
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, did);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Comment comment = new Comment();
                comment.setId(resultat.getInt("id"));
                comment.setContent(resultat.getString("content"));
                comment.setUserId(e.AfficherUser((Integer) resultat.getInt("user_id")));
                commentList.add(comment);
             
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
        return commentList;
    
    }
    //retourner la liste des commentaires qu'un utilisateur a poster 
    public List<Comment> CountForUser(int uid){
        List<Comment> listeComment = new ArrayList<Comment>();
        
        String req = "SELECT * FROM comment WHERE user_id=?";

        try {

            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, uid);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Comment comment = new Comment();
                
                comment.setId(resultat.getInt("id"));
                comment.setContent(resultat.getString("content"));
                
                listeComment.add(comment);
             
                
            }
            
            
        }
        catch(SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
            return null;
        }
    return listeComment;
    }
    //récupérer tous les badges de la base de données 
    public List<Badge> getAllBadges(){
        
        List<Badge> badgeList = new ArrayList<>();
        try {
            String req ="select * from badge ";
            Statement s = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs=s.executeQuery(req);
            
            while(rs.next()){
            Badge b = new Badge();
            b.setId(rs.getInt("id"));
            b.setName(rs.getString("name"));
            b.setActionName(rs.getString("action_name"));
            b.setActionCount(rs.getInt("action_count"));
            badgeList.add(b);
            }
            
        } catch (SQLException ex) {
             Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return badgeList;
    }
    
    //Ajout del'action de débloquage dans la base de données 
    public void  addUnlock(BadgeUnlock u){
        
        String req = "insert into badge_unlock(badge_id,user_id) values(?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            
            ps.setInt(1, u.getBadgeId().getId());
            ps.setInt(2, u.getUserId().getId());
            
            ps.executeUpdate();
            Vars.unlock_newMarker = 1;
            
            System.out.println(Vars.unlock_newMarker);
            
            
       
        } catch (SQLException ex) {
            Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    //débloquage des badges 
    public void checkAndUnlock(FosUser user){
        List<Badge> badges = getAllBadges();
        BadgeUnlock unlock = new BadgeUnlock();
        //System.out.println(badges.toString());
        
        for (Badge badge : badges) {
            int action_count = badge.getActionCount();
            int nb_comments = CountForUser(user.getId()).size();
            
            if (nb_comments == action_count) {
                //BadgeUnlock unlock = new BadgeUnlock();
                unlock.setBadgeId(badge);
                unlock.setUserId(user);
                addUnlock(unlock);
                break;
                
                //cleanUnlock();

            }
            Vars.unlock_newMarker = 0;
            System.out.println("ggg"+Vars.unlock_newMarker);
            
           
        }
        
        
//        addUnlock(unlock);
//        cleanUnlock();
        
        
        
        
    }
    //retourne les badges débloquer par l'utilisateur
    public List<Badge> findUlockedFor(int uid){
    
        List<Badge> userBadges = new ArrayList<>();
        try {
            String req = "select * from badge b inner join badge_unlock u where (b.id=u.badge_id) and (u.user_id=?)";
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, uid);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Badge badge = new Badge();
                
                badge.setId(resultat.getInt("id"));
                badge.setName(resultat.getString("name"));
                badge.setActionCount(resultat.getInt("action_count"));
                userBadges.add(badge);
             
                
            }
            
            
        } catch (SQLException ex) {
        Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userBadges;
    
    }
//remove repeateed unlocks à appeler lors du débloquage des badges:(not used)
    public void cleanUnlock(){
        
        try {
            String req="delete t1 from badge_unlock as t1, badge_unlock as t2 where t1.id > t2.id and t1.badge_id = t2.badge_id and t1.user_id=t2.user_id";
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.executeUpdate();
             
        } catch (SQLException ex) {
            Logger.getLogger(CommentServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   
        
        
        
    
}
