/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Title;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import interfaces.TitleDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ayesha
 */
public class TitleDAO extends DAO implements TitleDAOInterface {
//
//    public TitleDAO(String libraryDatabase) {
//        super(libraryDatabase);
//    }

    @Override

    /**
     * This method will return all titles in the Db. ArrayList of Titles is used
     * to return the titles Uses simple query Select * (all) from titles
     */
    public ArrayList<Title> viewAllTitles() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Title> titles = new ArrayList();

        try {
            con = getConnection();

            String query = "SELECT * from titles";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                Title c = new Title(rs.getInt("titleID"), rs.getString("novelName"), rs.getString("author"), rs.getInt("stock"), rs.getInt("onLoan"), rs.getString("titleDescription"));
                titles.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the viewAllTitles() method");
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
                System.out.println("Exception occured in the finally section in the viewAllTitles() method");
            }
        }

        return titles;
    }

    @Override

    /**
     * Add title method adds a new book in the lib/db Only Admins can add a book
     * Insert query is used to insert into the titles in db True will be
     * returned if title is added False will be returned if title couldn't be
     * added
     *
     */
    public boolean addTitle(Title t) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;

        try {
            conn = getConnection();
            String query = "INSERT INTO titles VALUES (NULL, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getNovelName());
            ps.setString(2, t.getAuthor());
            ps.setInt(3, t.getStock());
            ps.setInt(4, t.getOnLoan());
            ps.setString(5, t.getTitleDescription());
            rs = ps.executeUpdate();
            if (rs > 0) {
                System.out.println("Title has been added ");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the addTitle() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addTitle() method");
                e.getMessage();
            }
        }
        return false;
    }
    
    @Override
    public boolean updateTitle(int id, Title title) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        Title titleTemp = null;
        Boolean result = null;

        try {
            conn = getConnection();
            String query = "UPDATE titles SET novelName=?, author=?, stock=?, onLoan=?, titleDescription=? WHERE titleID=?";
            ps = conn.prepareStatement(query);
            String novelName = title.getNovelName();
            String author = title.getAuthor();
            int stock = title.getStock();
            int onLoan = title.getOnLoan();
            String titleDescription = title.getTitleDescription();

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, novelName);
            ps.setString(2, author);
            ps.setInt(3, stock);
            ps.setInt(4, onLoan);
            ps.setString(5, titleDescription);
            ps.setInt(6, id);

            // Execute the query
            rs = ps.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Constraint Exception occurred: " + e.getMessage());
            // Set the rowsAffected to -1, this can be used as a flag for the display section
            rs = -1;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } // Now that the program has completed its database access component, 
        // close the open access points (resultset, preparedstatement, connection)
        // Remember to close them in the OPPOSITE ORDER to how they were opened
        // Opening order: Connection -> PreparedStatement -> ResultSet
        // Closing order: ResultSet -> PreparedStatement -> Connection
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the updateTitle() method");
            }
        }

        if (rs > 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    /**
     * DeleteTitleById deletes the book in lib/db Only Admins can delete a book
     * Delete query is used to delete title from db Title will be deleted by
     * searching Title id as its unique True will be returned if title is delete
     * False will be returned if title couldn't be deleted
     *
     */
    @Override
    public boolean deleteTitleById(int titleId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            String query = "DELETE FROM titles WHERE titleID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, titleId);
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Title has been deleted successfully");
                return true;

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteTitleById() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the deleteTitleById() method");
                e.getMessage();
            }
        }
        return false;
    }

    /**
     * This method allows the user to search for a title by searching Author
     * User will enter a authors name All Titles belonging to the author will be
     * returned Query is used to show all titles belonging to the Author
     */
    @Override
    public ArrayList<Title> searchByAuthor(String author) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Title> titles = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * from titles WHERE author = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, author);
            rs = ps.executeQuery();

            while (rs.next()) {

                Title c = new Title(rs.getInt("titleID"), rs.getString("novelName"), rs.getString("author"), rs.getInt("stock"), rs.getInt("onLoan"), rs.getString("titleDescription"));
                titles.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the searchByAuthor() method");
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
                System.out.println("Exception occured in the finally section in the searchByAuthor() method");
            }
        }

        return titles;
    }

    /**
     * This method allows the user to search for a Title by searching titles
     * name User will enter a Titles name All Titles with the name entered will
     * be returned Query is used to show all titles with the name entered
     */
    @Override
    public Title searchByNovelName(String novelName) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title t = null;
        try {
            con = getConnection();

            String query = "Select * from titles where novelName = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, novelName);
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new Title(rs.getInt("titleID"), rs.getString("novelName"), rs.getString("author"), rs.getInt("stock"), rs.getInt("onLoan"), rs.getString("titleDescription"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the searchByNovelName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the searchByNovelName() method: " + e.getMessage());
            }
        }
        return t;

    }

    /**
     * This method allows the user to search for a title by searching titles Id
     * User will enter a Title id All Titles belonging to the Id will be
     * returned(Just one - Each title has unique id) Query is used to show all
     * titles belonging to the id
     */
    @Override
    public Title searchByID(int id) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title t = null;
        try {
            con = getConnection();

            String query = "Select * from titles where titleID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new Title(rs.getInt("titleID"), rs.getString("novelName"), rs.getString("author"), rs.getInt("stock"), rs.getInt("onLoan"), rs.getString("titleDescription"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the searchByID() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the searchByID() method: " + e.getMessage());
            }
        }
        return t;

    }


}
