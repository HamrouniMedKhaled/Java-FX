/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.util;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class DataSource {

    String url = "jdbc:mysql://localhost/bonplan";
    String login = "root";
    String pwd = "";
    Connection cnx;
    private static DataSource instance;

    private DataSource() {

        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("la connexion est établie");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;

    }

    public Connection getCnx() {
        return cnx;
    }
    

}
