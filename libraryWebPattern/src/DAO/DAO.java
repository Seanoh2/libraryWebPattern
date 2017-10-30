/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Seanoh
 */
public class DAO {
//    private String libraryDatabase;
//    
//    public DAO(String libraryDatabase)
//    {
//        this.libraryDatabase = libraryDatabase;
//    }
    
    public Connection getConnection() {
        // Create variables to hold database details
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/libraryDatabase";
        String username = "root";
        String password = "";

        // Create variables used to interact with database 
        // We need them created here so we can close them in the finally block
        Connection conn = null;

        try {
            // Load the database driver
            Class.forName(driver);

            // Get a connection to the database
            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }

    public void closeConnection(Connection conn) {
        try{
           if(conn != null) {
               conn.close();
               conn = null;
           }
        } catch(SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
        }
    }
}
