/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Borrowed;
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
    }

    /**
     * Test of addBorrowed method, of class BorrowedDAO.
     */
    @Test
    public void testAddBorrowed() {
        System.out.println("addBorrowed");
        Borrowed borrowed = null;
        User Admin = null;
        BorrowedDAO instance = new BorrowedDAO();
        boolean expResult = false;
        boolean result = instance.addBorrowed(borrowed, Admin);
        assertEquals(expResult, result);
    }
    
}
