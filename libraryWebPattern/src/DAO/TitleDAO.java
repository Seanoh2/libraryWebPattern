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
 * @author Sean
 */
public class TitleDAO extends DAO implements TitleDAOInterface {

    /**
     * This will return an ArrayList of Titles by their description.<p>
     * Method will check for substring inside titleDescription row.<p>
     * Case Sensitive
     * @param description This will allow for title to be found based on user input.
     * @return ArrayList of relevant Titles.
     */
    @Override
    public ArrayList<Title> getTitlesByDescription(String description) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title title = null;
        ArrayList<Title> titles = new ArrayList();

        try {
            conn = getConnection();
            String query = "SELECT * FROM titles WHERE titleDescription LIKE ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, "%" + description + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                title = new Title();
                // Get the pieces of a customer from the resultset
                title.setNovelName(rs.getString("novelName"));
                title.setAuthor(rs.getString("author"));
                title.setStock(rs.getInt("stock"));
                title.setOnLoan(rs.getInt("onLoan"));
                title.setTitleDescription(rs.getString("titleDescription"));
                titles.add(title);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getTitlesByDescription() method");
            e.printStackTrace();
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
                System.out.println("Exception occured in the finally section in the getTitlesByDescription() method");
            }
        }

        return titles;
    }

    /**
     * This will return an ArrayList of Titles by their Author.<p>
     * Method will check for exact name, not substring.<p>
     * Case Sensitive.
     *
     * @param description This will allow for title to be found based on user
     * input.
     * @return ArrayList of relevant Titles.
     */
    @Override
    public ArrayList<Title> getTitlesByAuthor(String author) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title title = null;
        ArrayList<Title> titles = new ArrayList();

        try {
            conn = getConnection();
            String query = "SELECT * FROM titles WHERE author = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, author);
            rs = ps.executeQuery();

            while (rs.next()) {
                title = new Title();
                // Get the pieces of a customer from the resultset
                title.setNovelName(rs.getString("novelName"));
                title.setAuthor(rs.getString("author"));
                title.setStock(rs.getInt("stock"));
                title.setOnLoan(rs.getInt("onLoan"));
                title.setTitleDescription(rs.getString("titleDescription"));
                titles.add(title);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getTitlesByAuthor() method");
            e.printStackTrace();
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
                System.out.println("Exception occured in the finally section in the getTitlesByAuthor() method");
            }
        }

        return titles;
    }

    /**
     * This will return Substring of Titles by their name.<p>
     * This will check for exact name, not substring.<p>
     * Case Sensitive.
     *
     * @param name This will allow for user to find title by Author
     * @return ArrayList of relevant Titles.
     */
    @Override
    public ArrayList<Title> getTitlesByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title title = null;
        ArrayList<Title> titles = new ArrayList();

        try {
            conn = getConnection();
            String query = "SELECT * FROM titles WHERE novelName = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                title = new Title();
                // Get the pieces of a customer from the resultset
                title.setNovelName(rs.getString("novelName"));
                title.setAuthor(rs.getString("author"));
                title.setStock(rs.getInt("stock"));
                title.setOnLoan(rs.getInt("onLoan"));
                title.setTitleDescription(rs.getString("titleDescription"));
                titles.add(title);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getTitlesByName() method");
            e.printStackTrace();
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
                System.out.println("Exception occured in the finally section in the getTitlesByName() method");
            }
        }

        return titles;
    }

    /**
     * This will return a title from the Database based on id. This will allow
     * for specific title to be pulled or for other methods to utilize if
     * needed.
     *
     * @param id
     * @return Title object based on ID.
     */
    @Override
    public Title getTitleByID(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title title = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM titles WHERE titleID = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                title = new Title(rs.getInt("titleID"), rs.getString("novelName"), rs.getString("author"), rs.getInt("stock"), rs.getInt("onLoan"), rs.getString("titleDescription"));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getTitlesByName() method");
            e.printStackTrace();
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
                System.out.println("Exception occured in the finally section in the getTitlesByName() method");
            }
        }

        return title;
    }

    /**
     * This will update an existing title in the database.
     *
     * @param title is the updated data.
     * @param id used to find title.
     * @return Confirmation if update was successful.
     */
    @Override
    public boolean addTitle(Title title) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        Title titleTemp = null;
        Boolean result = null;

        try {
            conn = getConnection();
            String query = "INSERT INTO titles VALUES(NULL,?,?,?,?,?)";
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
                System.out.println("Exception occured in the finally section in the addTitle() method");
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
     * Used to return all titles in database. Can be used to display or to find
     * info from client.
     *
     * @return ArrayList of all titles in db.
     */
    @Override
    public ArrayList<Title> getAllTitles() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title title = null;
        ArrayList<Title> titles = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * FROM titles";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                title = new Title();
                title.setTitleID(rs.getInt("titleID"));
                title.setNovelName(rs.getString("novelName"));
                title.setAuthor(rs.getString("author"));
                title.setStock(rs.getInt("stock"));
                title.setOnLoan(rs.getInt("onLoan"));
                title.setTitleDescription(rs.getString("titleDescription"));
                titles.add(title);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getTitlesByName() method");
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
                System.out.println("Exception occured in the finally section in the getTitlesByName() method");
            }
        }

        return titles;

    }

    /**
     * This will Delete an existing title in the database.
     *
     * @param id used to find title to delete.
     * @return Confirmation if delete was successful.
     */
    public boolean removeTitle(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        Title temp = null;
        Boolean result = null;

        try {
            conn = getConnection();
            String query = "delete from titles where titleID = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, id);

            rs = ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the removeTitle() method");
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
     * This will update an existing title in the database.
     *
     * @param title is the updated data.
     * @param id used to find title.
     * @return Confirmation if update was successful.
     */
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
}
