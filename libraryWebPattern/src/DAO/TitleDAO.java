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
            String query = "INSERT INTO titles VALUES (NULL, ?, ?, ?, ?, ?, NULL)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, t.getTitleID());
            ps.setString(2, t.getNovelName());
            ps.setString(3, t.getAuthor());
            ps.setInt(4, t.getStock());
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
     * updateTitle updates the the Title name in lib/db Only Admins can Update
     * Title name Update query is used to Update by Setting the novelName by
     * titleID Title name will be Updated by searching Title id as its unique A
     * new Title name option will be given to the admin True will be returned if
     * title is Update False will be returned if title couldn't be Updated
     *
     */
    @Override
    public boolean updateTitle(int titleID, String newNovelName) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE titles SET novelName = ? WHERE titleID = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, titleID);
            ps.setString(2, newNovelName);

            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Title has been updated successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateTitle() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateTitle() method");
                e.getMessage();
            }
        }

        return false;
    }

    /**
     * UpdateAuthor updates the the Authors name in lib/db Only Admins can
     * Update Authors name Update query is used to Update by Setting the Author
     * by titleID Author name will be Updated by searching Title id as its
     * unique A new Author name option will be given to the admin True will be
     * returned if Author is Update False will be returned if Author couldn't be
     * Updated
     *
     */
    @Override
    public boolean updateAuthor(int titleID, String newAuthorName) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE titles SET author = ? WHERE titleID = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, titleID);
            ps.setString(2, newAuthorName);

            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Author has been updated successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateAuthor() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateAuthor() method");
                e.getMessage();
            }
        }

        return false;
    }

    /**
     * updateGenre updates the the Genre in the lib/db Only Admins can Update
     * Genre Update query is used to Update by Setting the Genre by genreID
     * Genre name will be Updated by searching Genre Id as its unique A new
     * Genre name option will be given to the admin True will be returned if
     * genre is Update False will be returned if genre couldn't be Updated
     *
     * @param genreID
     */
    @Override
    public boolean updateGenre(int genreID, String newGenre) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE genre SET genre = ? WHERE genreID = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, genreID);
            ps.setString(2, newGenre);

            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Genre has been updated successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateGenre() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateGenre() method");
                e.getMessage();
            }
        }

        return false;
    }

    /**
     * updateDescription updates the the Titles Description in lib/db Only
     * Admins can Update Titles Description Update query is used to Update by
     * Setting the titlesDescription by titleID Titles Description will be
     * Updated by searching Titles id as its unique A new Title name option will
     * be given to the admin True will be returned if Titles Description is
     * Update False will be returned if Titles Description couldn't be Updated
     *
     */
    @Override
    public boolean updateDescription(int titleID, String newDescription) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE titles SET titleDescription = ? WHERE titleID = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, titleID);
            ps.setString(2, newDescription);

            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Description has been updated successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateDescription() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateDescription() method");
                e.getMessage();
            }
        }

        return false;
    }

    /**
     * updateStockOfBook updates the the Stock of books in lib/db Only Admins
     * can Update The Stock amount Update query is used to Update by Setting the
     * Stock by titleID Stock will be Updated by searching Titles id as its
     * unique A new Stock amount option will be given to the admin True will be
     * returned if Stock amount is Update False will be returned if Stock
     * couldn't be Updated
     *
     */
    @Override
    public boolean updateStockOfBook(int titleID, int newStock) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE titles SET stock = ? WHERE titleID = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, titleID);
            ps.setInt(2, newStock);

            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Stock has been updated successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateStockOfBook() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateStockOfBook() method");
                e.getMessage();
            }
        }

        return false;
    }

    /**
     * This is used to update the database of a borrowed books status.
     *
     * @param titleID Used to identify what title is being updated.
     * @param newOnLoan New values of OnLoan, usually 0 or 1.
     * @return
     */
    @Override
    public boolean updateOnLoan(int titleID, int newOnLoan) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE titles SET onLoan = ? WHERE titleID = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, titleID);
            ps.setInt(2, newOnLoan);

            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Stock has been updated successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateOnLoan() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateOnLoan() method");
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

    /**
     * This is used to return an arrayList of all titles in the database.<p>
     * This will mainly be used to display all titles in database.
     *
     * @return ArrayList of all titles in DB.d
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

}
