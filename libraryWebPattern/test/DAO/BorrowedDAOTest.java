/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Borrowed;
import Dtos.Title;
import Dtos.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sean
 */
public class BorrowedDAOTest {
    
    public BorrowedDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBorrowedByUserID method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByUserID() {
        System.out.println("getBorrowedByUserID");
        int userID = 4;
        BorrowedDAO instance = new BorrowedDAO();
        ArrayList<Borrowed> expResult = new ArrayList();
        
        Borrowed temp = new Borrowed();
        expResult.add(temp);
        
        ArrayList<Borrowed> result = instance.getBorrowedByUserID(userID);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getBorrowedByTitleID method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByTitleID() {
        System.out.println("getBorrowedByTitleID");
        int titleID = 4;
        BorrowedDAO instance = new BorrowedDAO();
        ArrayList<Borrowed> expResult = new ArrayList();
   
        Borrowed temp = new Borrowed();
        expResult.add(temp);
       
        ArrayList<Borrowed> result = instance.getBorrowedByTitleID(titleID);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getBorrowedByStatus method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByStatus() {
        System.out.println("getBorrowedByStatus");
        int status = 1;
        BorrowedDAO instance = new BorrowedDAO();
        ArrayList<Borrowed> expResult = new ArrayList();
        ArrayList<Borrowed> result = new ArrayList();
        result = instance.getBorrowedByStatus(status);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getStatusByUserID method, of class BorrowedDAO.
     */
    @Test
    public void testGetStatusByUserID() {
        System.out.println("getStatusByUserID");
        int userID = 0;
        BorrowedDAO instance = new BorrowedDAO();
        ArrayList<Borrowed> expResult = new ArrayList();
        ArrayList<Borrowed> result = instance.getStatusByUserID(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateStatus method, of class BorrowedDAO.
     */
    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        int borrowedID = 1;
        int newStatus = 1;
        BorrowedDAO instance = new BorrowedDAO();
        boolean expResult = true;
        boolean result = instance.updateStatus(borrowedID, newStatus);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBorrowedByID method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByID() {
        System.out.println("getBorrowedByID");
        int borrowedID = 1;
        BorrowedDAO instance = new BorrowedDAO();
        UserDAO userInstance = new UserDAO();
        TitleDAO titleInstance = new TitleDAO();
        
        Title title = titleInstance.searchByID(1);
        User user = userInstance.findUserByID(4);
        
        Borrowed expResult = new Borrowed(1,user,title,10,0);
        Borrowed result = instance.getBorrowedByID(borrowedID);
        assertEquals(expResult.getTitle(), result.getTitle());
    }

    /**
     * Test of addBorrowed method, of class BorrowedDAO.
     */
    @Test
    public void testAddBorrowed() {
        System.out.println("addBorrowed");
        BorrowedDAO instance = new BorrowedDAO();
        UserDAO userInstance = new UserDAO();
        TitleDAO titleInstance = new TitleDAO();
    
        Title title = titleInstance.searchByID(1);
        User user = userInstance.findUserByID(4);
        Borrowed temp = new Borrowed(user,title,10,0);
        
        boolean expResult = true;
        boolean result = instance.addBorrowed(temp);
        assertEquals(expResult, result);
    }
    
}
