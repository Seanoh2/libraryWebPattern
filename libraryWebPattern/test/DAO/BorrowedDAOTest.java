/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Borrowed;
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
        int userID = 0;
        BorrowedDAO instance = new BorrowedDAO();
        ArrayList<Borrowed> expResult = null;
        ArrayList<Borrowed> result = instance.getBorrowedByUserID(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBorrowedByTitleID method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByTitleID() {
        System.out.println("getBorrowedByTitleID");
        int titleID = 0;
        BorrowedDAO instance = new BorrowedDAO();
        ArrayList<Borrowed> expResult = null;
        ArrayList<Borrowed> result = instance.getBorrowedByTitleID(titleID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBorrowedByStatus method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByStatus() {
        System.out.println("getBorrowedByStatus");
        int status = 0;
        BorrowedDAO instance = new BorrowedDAO();
        ArrayList<Borrowed> expResult = null;
        ArrayList<Borrowed> result = instance.getBorrowedByStatus(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatusByUserID method, of class BorrowedDAO.
     */
    @Test
    public void testGetStatusByUserID() {
        System.out.println("getStatusByUserID");
        int userID = 0;
        BorrowedDAO instance = new BorrowedDAO();
        ArrayList<Borrowed> expResult = null;
        ArrayList<Borrowed> result = instance.getStatusByUserID(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStatus method, of class BorrowedDAO.
     */
    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        int borrowedID = 0;
        int newStatus = 0;
        BorrowedDAO instance = new BorrowedDAO();
        boolean expResult = false;
        boolean result = instance.updateStatus(borrowedID, newStatus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBorrowedByID method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByID() {
        System.out.println("getBorrowedByID");
        int borrowedID = 0;
        BorrowedDAO instance = new BorrowedDAO();
        Borrowed expResult = null;
        Borrowed result = instance.getBorrowedByID(borrowedID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBorrowed method, of class BorrowedDAO.
     */
    @Test
    public void testAddBorrowed() {
        System.out.println("addBorrowed");
        Borrowed borrowed = null;
        BorrowedDAO instance = new BorrowedDAO();
        boolean expResult = false;
        boolean result = instance.addBorrowed(borrowed);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
