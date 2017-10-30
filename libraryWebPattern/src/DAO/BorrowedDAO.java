/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Borrowed;
import Dtos.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import interfaces.BorrowedDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sean
 */
public class BorrowedDAO extends DAO implements BorrowedDAOInterface {

//    public BorrowedDAO(String libraryDatabase) {
//        super(libraryDatabase);
//    }

    
    /**
     * This will return a borrowed ArrayList by their userID.<p>
     * User must be logged in to do this.<p>
     * Method will be rejected if they are not.
     * @param userID This will be used to identify user.
     * @return ArrayList of Borrowed
     */
    @Override
    public ArrayList<Borrowed> getBorrowedByUserID(int userID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Borrowed borrowed = null;
        UserDAO userdao = new UserDAO();
        TitleDAO titledao = new TitleDAO();
        ArrayList<Borrowed> borrowedList = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * FROM borrowed WHERE userID = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                borrowed = new Borrowed();
                // Get the pieces of a customer from the resultset
                borrowed.setBorrowedID(rs.getInt("BorrowedID"));
                borrowed.setUser(userdao.findUserByID(rs.getInt("userID")));
                borrowed.setTitle(titledao.searchByID(rs.getInt("titleID")));
                borrowed.setDaysBorrowed(rs.getInt("daysBorrowed"));
                borrowed.setStatus(rs.getInt("status"));
                borrowedList.add(borrowed);

            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getBorrowedByUserID() method");
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
                System.out.println("Exception occured in the finally section in the getBorrowedByUserID() method");
            }
        }

        return borrowedList;
    }

    /**
     * This will return a borrowed ArrayList by their titleID<p>
     * User must be logged in to do this.<p>
     * Methods will be rejected if they are not.
     * 
     * @param titleID This will be used to identify what Title to find.
     * @return ArrayList of Borrowed
     */
    @Override
    public ArrayList<Borrowed> getBorrowedByTitleID(int titleID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Borrowed borrowed = null;
        UserDAO userdao = new UserDAO();
        TitleDAO titledao = new TitleDAO();
        ArrayList<Borrowed> borrowedList = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * FROM borrowed WHERE titleID = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, titleID);
            rs = ps.executeQuery();

            while (rs.next()) {
                borrowed = new Borrowed();
                // Get the pieces of a customer from the resultset
                borrowed.setBorrowedID(rs.getInt("BorrowedID"));
                borrowed.setUser(userdao.findUserByID(rs.getInt("userID")));
                borrowed.setTitle(titledao.searchByID(rs.getInt("titleID")));
                borrowed.setDaysBorrowed(rs.getInt("daysBorrowed"));
                borrowed.setStatus(rs.getInt("status"));
                borrowedList.add(borrowed);

            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getBorrowedByTitleID() method");
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
                System.out.println("Exception occured in the finally section in the getBorrowedByTitleID() method");
            }
        }

        return borrowedList;
    }

    /**
     * This will return a borrowed ArrayList by their titleID<p>
     * Status 1 means it is returned.<p>
     * Status 0 means it isn't returned.<p>
     * User must be logged in to do this.<p>
     * Method will be rejected if they are not.
     * @param status This will be used to find which loans have finished.
     * @return ArrayList of Borrowed
     */
    @Override
    public ArrayList<Borrowed> getBorrowedByStatus(int status) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Borrowed borrowed = null;
        UserDAO userdao = new UserDAO();
        TitleDAO titledao = new TitleDAO();
        ArrayList<Borrowed> borrowedList = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * FROM borrowed WHERE status = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, status);
            rs = ps.executeQuery();

            while (rs.next()) {
                borrowed = new Borrowed();
                // Get the pieces of a customer from the resultset
                borrowed.setBorrowedID(rs.getInt("BorrowedID"));
                borrowed.setUser(userdao.findUserByID(rs.getInt("userID")));
                borrowed.setTitle(titledao.searchByID(rs.getInt("titleID")));
                borrowed.setDaysBorrowed(rs.getInt("daysBorrowed"));
                borrowed.setStatus(rs.getInt("status"));
                borrowedList.add(borrowed);

            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getBorrowedByStatus() method");
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
                System.out.println("Exception occured in the finally section in the getBorrowedByStatus() method");
            }
        }

        return borrowedList;    
    }
    
    @Override
    public ArrayList<Borrowed> getStatusByUserID(int userID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Borrowed borrowed = null;
        UserDAO userdao = new UserDAO();
        TitleDAO titledao = new TitleDAO();
        ArrayList<Borrowed> borrowedList = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * FROM borrowed WHERE userID = ? AND status = 0";
            ps = con.prepareStatement(query);

            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                borrowed = new Borrowed();
                // Get the pieces of a customer from the resultset
                borrowed.setBorrowedID(rs.getInt("BorrowedID"));
                borrowed.setUser(userdao.findUserByID(rs.getInt("userID")));
                borrowed.setTitle(titledao.searchByID(rs.getInt("titleID")));
                borrowed.setDaysBorrowed(rs.getInt("daysBorrowed"));
                borrowed.setStatus(rs.getInt("status"));
                borrowedList.add(borrowed);

            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getStatusByUserID() method");
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
                System.out.println("Exception occured in the finally section in the getStatusByUserID() method");
            }
        }

        return borrowedList;    
    }
    
    @Override
    public boolean updateStatus(int borrowedID, int newStatus) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Borrowed borrowed = null;

        try {
            con = getConnection();
            String query = "UPDATE borrowed SET status = ? WHERE borrowedID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, newStatus);
            ps.setInt(2, borrowedID);
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Status has been updated successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateStatus() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateStatus() method");
                e.getMessage();
            }
        }

        return false;  
    }
    @Override
    public Borrowed getBorrowedByID(int borrowedID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Borrowed borrowed = null;
        UserDAO userdao = new UserDAO();
        TitleDAO titledao = new TitleDAO();

        try {
            con = getConnection();
            String query = "SELECT * FROM borrowed WHERE borrowedID = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, borrowedID);
            rs = ps.executeQuery();

            while (rs.next()) {
                borrowed = new Borrowed();
                // Get the pieces of a customer from the resultset
                borrowed.setBorrowedID(rs.getInt("BorrowedID"));
                borrowed.setUser(userdao.findUserByID(rs.getInt("userID")));
                borrowed.setTitle(titledao.searchByID(rs.getInt("titleID")));
                borrowed.setDaysBorrowed(rs.getInt("daysBorrowed"));
                borrowed.setStatus(rs.getInt("status"));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getBorrowedByID() method");
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
                System.out.println("Exception occured in the finally section in the getBorrowedByID() method");
            }
        }

        return borrowed;  
    }
    /**
     * This will add a borrowed object into the database.<p>
     * Admin user is imported to check if they are a admin before adding borrowed object.<p>
     * Method rejected if User object isn't admin.
     * @param borrowed This will be used to add a new borrowed to database.
     * @return boolean response if it was successful or an issue happened.
     */
    @Override
    public boolean addBorrowed(Borrowed borrowed) {
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;
        Boolean result = null;

        try {
            con = getConnection();
            //Adding new borrowed entry
            String query = "INSERT INTO borrowed VALUES(NULL,?,?,?,?)";
            ps = con.prepareStatement(query);
            int userID = borrowed.getUser().getUserID();
            int titleID = borrowed.getTitle().getTitleID();
            int daysBorrowed = borrowed.getDaysBorrowed();
            int status = borrowed.getStatus();

            ps.setInt(1,userID);
            ps.setInt(2,titleID);
            ps.setInt(3,daysBorrowed);
            ps.setInt(4,status);
            
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
                if (con != null) {
                    closeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the addBorrowed() method");
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
