/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Categorie;
import edu.bonplan.entities.FosUser;
import edu.bonplan.util.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darkz
 */
public class UserServices {
    public static FosUser user;
    
    public FosUser AfficherUser(int x) throws SQLException {
            FosUser u = new FosUser();
            String req="SELECT * FROM fos_user where id = ?";
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1,x);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
              u.setId(rs.getInt("id"));
              u.setUsername(rs.getString("username"));
              u.setScore(rs.getInt("score"));
              u.setEmail(rs.getString("email"));
              u.setRoles(rs.getString("roles"));
              u.setTelephone(rs.getString("telephone"));
                System.out.println(u.getEmail());
              
            }
            return u;
            }
    
    
    public FosUser login(String name) {
        FosUser u = new FosUser();

        
        String req = "SELECT * FROM `fos_user` WHERE username=?";

        try {
            

            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, name);

            ResultSet r = ps.executeQuery();

            if (r.next()) {
                u.setId(r.getInt("id"));
                u.setUsername(r.getString("username"));
                u.setEmail(r.getString("email"));
                u.setPassword(r.getString("password"));
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(FosUser.class.getName()).log(Level.SEVERE, null, ex);

        }

        return u;

    }
    
    
    
    public String inscription(FosUser user) {
String req = "INSERT INTO fos_user"
		+ "(username, username_canonical, email, email_canonical, enabled, password, score, report ,roles) VALUES"
		+ "(?,?,?,?,?,?,?,?,?)";
        try {
            
            

            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getUsernameCanonical());
                                
            ps.setString(3, user.getEmail());
                ps.setString(4, user.getEmailCanonical());
                            ps.setInt(5, 1);

            ps.setString(6, user.getPassword());
          ps.setInt(7, 0);
          ps.setInt(8, 0);
          ps.setString(9, user.getRoles());
          
            boolean r =ps.execute();
            return "done";
          
        } catch (SQLException ex) {
            System.out.println("catch");

            System.out.println(ex);
            user.setId(0);
            return ex.toString();

        }

    }
    
    
}
