/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Borrowed;
import Dtos.Genre;
import Dtos.TitleGenre;
import interfaces.TitleGenreDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Seanoh
 */
public class TitleGenreDAO extends DAO implements TitleGenreDAOInterface {
//
//    public TitleGenreDAO(String libraryDatabase) {
//        super(libraryDatabase);
//    }
    
    /**
     * This will return a list of TitleGenre objects by the titleID.<p>
     * TitleGenre objects store Title and Genre objects.
     * @param titleID Used to find relevant genres.
     * @return ArrayList of TitleGenre objects.
     */
    @Override
    public ArrayList<TitleGenre> getGenreByTitleID(int titleID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TitleDAO titleDAO = new TitleDAO();
        GenreDAO genreDAO = new GenreDAO();
        TitleGenre titleGenre = new TitleGenre();
        ArrayList<TitleGenre> genreList = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * FROM titleGenre WHERE titleID = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, titleID);
            rs = ps.executeQuery();

            while (rs.next()) {
                titleGenre.setGenre(genreDAO.getGenreByID(rs.getInt("genreID")));
                titleGenre.setTitle(titleDAO.searchByID(rs.getInt("titleID")));
                
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getGenreByTitleID() method");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the getGenreByTitleID() method");
            }
        }

        return genreList;
    }

 
}
