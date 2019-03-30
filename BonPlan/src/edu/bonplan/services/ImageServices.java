/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Image;
import edu.bonplan.util.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamrouni
 */
public class ImageServices {
            
            public Image AfficherImage(int x) throws SQLException {
            Image i = new Image();
            String req="SELECT * FROM Image where id = ?";
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1,x);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
              i.setId(rs.getInt("id"));
              i.setAlt(rs.getString("alt"));
              i.setUrl(rs.getString("url"));
            }
            return i;
            }
            public int insert(Image img){
                try{
                String req="insert into image (url,alt) values (?,?)";
                PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, img.getUrl());
                ps.setString(2, img.getAlt() );

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                int generatedKey = 0;
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                }
                return generatedKey;
            } catch (SQLException ex) {
        Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
    }
        return 0;
            }
    
}
