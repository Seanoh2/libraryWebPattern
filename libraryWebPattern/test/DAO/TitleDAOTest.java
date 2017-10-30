/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Title;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ayesha
 */
public class TitleDAOTest {
    /**
     * Test of viewAllTitles method, of class TitleDAO.
     */
    @Test
    public void testViewAllTitles() {
        System.out.println("viewAllTitles");
        TitleDAO instance = new TitleDAO();
        ArrayList<Title> expResult = null;
        ArrayList<Title> result = instance.viewAllTitles();
        assertEquals(expResult, result);
 
    }

    /**
     * Test of addTitle method, of class TitleDAO.
     */
    @Test
    public void testAddTitle() {
        System.out.println("addTitle");
        Title t = null;
        TitleDAO instance = new TitleDAO();
        boolean expResult = true;
        boolean result = instance.addTitle(t);
    }

    /**
     * Test of deleteTitleById method, of class TitleDAO.
     * Testing valid id from db = 5
     * ExpResult is deleting the the title (True);
     */
    @Test
    public void testDeleteTitleById() {
        System.out.println("deleteTitleById");
        //Valid id of title from db
        int titleId = 5;
        TitleDAO instance = new TitleDAO();
        //Deletes the title
        boolean expResult = true;
        boolean result = instance.deleteTitleById(titleId);
        assertEquals(expResult, result);
    }
    /**
     * Test of DeleteTitleByIdInvalidID method
     * Testing an invalid id from db
     * ExpResult False
     */
    @Test
    public void testDeleteTitleByIdInvalidID() {
        System.out.println("DeleteTitleByIdInvalidID");
        //Invalid id from db
        int titleId = -10;
        TitleDAO instance = new TitleDAO();
        //Doesnt deletes
        boolean expResult = false;
        boolean result = instance.deleteTitleById(titleId);
        assertEquals(expResult, result);
    }
      
    /**
     * Test of searchByAuthor method, of class TitleDAO.
     *Testing from Valid authors name = Philip Roth
     * ExpResult Book that matches the authors name
     */
    @Test
    public void testSearchByAuthor() {
        System.out.println("searchByAuthor");
        //Valid author from db.
        String author = "Philip Roth";
        TitleDAO instance = new TitleDAO();
        //Title from db with Same author
        Title temp = new Title (3,"American Pastoral","Philip Roth",2,0,"In American Pastoral, Philip Roth gives us a novel...");
        ArrayList<Title> expResult =  new ArrayList();
        expResult.add(temp);
        ArrayList<Title> result = instance.searchByAuthor(author);
        assertEquals(expResult, result);
       
    }
    
        /**
     * Test of SearchByAuthorInvalidAutho method
     *Testing from inValid authors name = Philiip Roth
     * ExpResult null.
     */
    @Test
    public void testSearchByAuthorInvalidAuthor() {
        System.out.println("SearchByAuthorInvalidAutho");
        //InValid author from db.
        String author = "Philiip Roth";
        TitleDAO instance = new TitleDAO();
        //No book found for such author in db
        ArrayList<Title> expResult = null;
        ArrayList<Title> result = instance.searchByAuthor(author);
        assertEquals(expResult, result);
       
    }


    
    /**
     * Test of searchByNovelName method, of class TitleDAO.
     * Testing from Valid name = Animal Farm
     * ExpResult title from db with novel Name= Animal Farm.
     */
    @Test
    public void testSearchByNovelName() {
        System.out.println("searchByNovelName");
        //Valid Novel name from the db
        String novelName = "Animal Farm";
        TitleDAO instance = new TitleDAO();
        //Book with same name from db
        Title expResult = new Title (5, "Animal Farm", "George Orwell",50,0,"As ferociously fresh as it was more than a half ce...");
        Title result = instance.searchByNovelName(novelName);
        assertEquals(expResult, result);
        
    }
    
        /**
     * Test of SearchByNovelNameInvalidName method, of class TitleDAO.
     * Testing from inValid name = Animal Farmm
     * ExpResult null. No book matches
     */
    @Test
    public void testSearchByNovelNameInvalidName() {
        System.out.println("SearchByNovelNameInvalidName");
        //InValid Novel name from the db
        String novelName = "Animal Farmm";
        TitleDAO instance = new TitleDAO();
        //No book matches such name
        Title expResult = null;
        Title result = instance.searchByNovelName(novelName);
        assertEquals(expResult, result);
        
    }


    /**
     * Test of searchByID method, of class TitleDAO.
     * Testing for an valid id.
     * ExpResult title from db with id = 8.
     */
    @Test
    public void testSearchByID() {
        System.out.println("searchByID");
        //Valid Id from database
        int id = 8;
        TitleDAO instance = new TitleDAO();
        //Title from Database
        Title expResult = new Title (8,"The Assistant", "Camille Perri",25,0,"Tina Fontana is the hapless but brazen thirty-year...");
        Title result = instance.searchByID(id);
        assertEquals(expResult, result);
    }
    
    
     /**
     * Test of SearchByIDInvalidID method, of class TitleDAO.
     * Testing for an invalid id.
     * ExpResult null, id not present in db
     */
       @Test
    public void testSearchByIDInvalidID() {
        System.out.println("SearchByIDInvalidID");
        //Invalid Id from database
        int id = -8;
        TitleDAO instance = new TitleDAO();
        //No book matches
        Title expResult = null;
        Title result = instance.searchByID(id);
        assertEquals(expResult, result);
    }
    
    
}
