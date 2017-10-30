/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Genre;
import interfaces.GenreDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sami
 */
public class GenreDAO extends DAO implements GenreDAOInterface {

//    public GenreDAO(String libraryDatabase) {
//        super(libraryDatabase);
//    }

    /**
     * This will return a Genre object by their ID.<p>
     * Single Genre object.
     * @param genreID This will be used to find what genre is needed.
     * @return A Genre object from database.
     */
    @Override
    public Genre getGenreByID(int genreID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Genre g = new Genre();

        try {
            conn = getConnection();
            String query = "SELECT * FROM genre WHERE genreID = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, genreID);
            rs = ps.executeQuery();

            if (rs.next()) {
                g = new Genre(rs.getInt("genreID"), rs.getString("genre"));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getGenreByID() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getGenreByID() method: " + e.getMessage());
            }
        }
        return g;
    }

}
