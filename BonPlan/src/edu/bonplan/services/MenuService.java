/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.services;

import edu.bonplan.entities.Menu;
import edu.bonplan.util.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MenuService {
    
    public ArrayList <Menu> getAllMenu(){
        ArrayList <Menu> maliste = new ArrayList<Menu>();
        String req = "SELECT * FROM menu";
       
        try{
             Statement s=DataSource.getInstance().getCnx().createStatement();
             ResultSet rs=s.executeQuery(req);     
            while (rs.next()){

                Menu menu = new Menu();
                menu.setId(rs.getInt("id"));
                menu.setContenu(rs.getString("contenu"));
                menu.setPrix(rs.getDouble("prix"));
                EnseigneService es=new EnseigneService();
                menu.setEnseigneId( es.getEnseigneById(rs.getInt("enseigne_id")));
                System.out.println(menu.getEnseigneId().getNom());
               //
                maliste.add(menu);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return maliste;
    }
    
    public Menu getMenuByid(int id){
        Menu menu = new Menu();
        String req = "select * from menu where id=?";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                menu.setId(rs.getInt("id"));
                menu.setContenu(rs.getString("contenu"));
                menu.setPrix(rs.getDouble("prix"));
             // menu.setEnseigneId(rs.getInt("enseigneId"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        
        return menu;
    }
    
    public void insertMenu ( Menu menu){
        String req="insert into menu (contenu,prix,enseigne_id) values(?,?,?) ";
        try{
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, menu.getContenu());
            ps.setDouble(2, menu.getPrix());
            ps.setInt(3, menu.getEnseigneId().getId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateMenu (Menu menu , int id){
        String req="update menu set contenu=?, prix=?,enseigne_id=? WHERE id=?";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, menu.getContenu());
            ps.setDouble(2, menu.getPrix());
            ps.setInt(3, menu.getEnseigneId().getId());
            ps.setInt(4,id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void delete (int id){
       String req="delete from menu where id=?";
       try{
           PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
           ps.setInt(1, id);
           ps.executeUpdate();
       }catch (SQLException e){
           e.printStackTrace();
       }
    }
    
}
