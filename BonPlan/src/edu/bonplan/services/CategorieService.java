/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Categorie;
import edu.bonplan.util.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hamrouni
 */
public class CategorieService {
    public Categorie AfficherCategorie(int x) throws SQLException {
            Categorie i = new Categorie();
            String req="SELECT * FROM Categorie where id = ?";
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1,x);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
              i.setId(rs.getInt("id"));
              i.setNom(rs.getString("nom"));
              
            }
            return i;
            }
    public List<Categorie> AllCategories() throws SQLException {
           
            String req="SELECT * FROM Categorie";
            List<Categorie> maliste = new ArrayList<Categorie>();
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
               Categorie i = new Categorie(); 
              i.setId(rs.getInt("id"));
              i.setNom(rs.getString("nom"));
              maliste.add(i);
              
            }
            return maliste;
            }
    public ArrayList<Categorie> getAll()
            {
                ArrayList<Categorie> categories=new ArrayList<>();
                String req="SELECT * FROM categorie ";
                try {
                    PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
                    ResultSet rs=s.executeQuery();
                    while(rs.next())
                    {
                       categories.add(new Categorie(rs.getInt(1),rs.getString(2)));

                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }


                return categories;
            }
    public Categorie getCategorie(String c) throws SQLException {
            Categorie i = new Categorie();
            String req="SELECT * FROM Categorie where nom = ?";
            PreparedStatement s=DataSource.getInstance().getCnx().prepareStatement(req);
            s.setString(1,c);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
              i.setId(rs.getInt("id"));
              i.setNom(rs.getString("nom"));
              
            }
            return i;
            }
}
