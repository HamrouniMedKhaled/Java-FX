package edu.bonplan.services;

import edu.bonplan.entities.Adresse;
import edu.bonplan.entities.Deal;
import edu.bonplan.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdresseService {

    public int  addAdresse(Adresse adresse) {

        try {
            String req="insert into adresse (pays,ville,rue,codepostal) values (?,?,?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, adresse.getPays());
            ps.setString(2, adresse.getVille() );
            ps.setString(3,adresse.getRue());
            ps.setInt(4, adresse.getCodepostal());

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

    public Adresse getAdresseWhereId(int id) {
        try {
            System.out.println("req adresse"+id);
            String req="SELECT * FROM adresse WHERE id="+id;
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs=ps.executeQuery(req);
            Adresse adresse=new Adresse();

            if(rs.next())
            {adresse.setId(rs.getInt(1));
                adresse.setPays(rs.getString(2));
                adresse.setVille(rs.getString(3));
                adresse.setRue(rs.getString(4));

                adresse.setCodepostal(rs.getInt(5));
            }
            return adresse;

        } catch (SQLException ex) {
            Logger.getLogger(DealServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
