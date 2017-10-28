/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class UserDAOTest {
    
    public UserDAOTest() {
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
     * Test of selectUserByUsername method, of class UserDAO.
     */
    @Test
    public void testSelectUserByUsername() {
        System.out.println("selectUserByUsername");
        String firstName = "";
        String lastName = "";
        UserDAO instance = new UserDAO();
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.selectUserByUsername(firstName, lastName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectUserContainingName method, of class UserDAO.
     */
    @Test
    public void testSelectUserContainingName() {
        System.out.println("selectUserContainingName");
        String name = "";
        UserDAO instance = new UserDAO();
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.selectUserContainingName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByID method, of class UserDAO.
     */
    @Test
    public void testFindUserByID() {
        System.out.println("findUserByID");
        int userID = 0;
        UserDAO instance = new UserDAO();
        User expResult = null;
        User result = instance.findUserByID(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class UserDAO.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User u = null;
        UserDAO instance = new UserDAO();
        boolean expResult = false;
        boolean result = instance.addUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class UserDAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "";
        String password = "";
        UserDAO instance = new UserDAO();
        int expResult = 0;
        int result = instance.login(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByEmail method, of class UserDAO.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "";
        UserDAO instance = new UserDAO();
        User expResult = null;
        User result = instance.getUserByEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
