/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.TitleGenre;
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
public class TitleGenreDAOTest {
    
    public TitleGenreDAOTest() {
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
     * Test of getGenreByTitleID method, of class TitleGenreDAO.
     */
    @Test
    public void testGetGenreByTitleID() {
        System.out.println("getGenreByTitleID");
        int titleID = 0;
        TitleGenreDAO instance = new TitleGenreDAO();
        ArrayList<TitleGenre> expResult = new ArrayList();
        ArrayList<TitleGenre> result = instance.getGenreByTitleID(titleID);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
