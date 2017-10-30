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
     * Test of updateTitle method, of class TitleDAO.
     * Testing valid title id = 1
     * Giving it new name
     * Exp Result true.
     */
    @Test
    public void testUpdateTitle() {
        System.out.println("updateTitle");
        //Valid id from db
        int titleID = 1;
        //New name for title
        String newNovelName = "All The Kings men";
        TitleDAO instance = new TitleDAO();
        //Updates
        boolean expResult = true;
        boolean result = instance.updateTitle(titleID, newNovelName);
        assertEquals(expResult, result);
      
    }
    
    /**
     * Test of UpdateTitleInvalidID method
     * Testing invalid title id = -9
     * Giving it new name
     * Exp Result false.
     */
    
       @Test
    public void testUpdateTitleInvalidID() {
        System.out.println("UpdateTitleInvalidID");
        //Invalid id from db
        int titleID = -9;
        //new name for title
        String newNovelName = "All The Kings men";
        TitleDAO instance = new TitleDAO();
        //Doesnt updates
        boolean expResult = false;
        boolean result = instance.updateTitle(titleID, newNovelName);
        assertEquals(expResult, result);
      
    }

   /**
     * Test of updateAuthor method, of class TitleDAO.
     * Testing valid title id = 3
     * Giving it new author
     * Exp Result true.
     */
    @Test
    public void testUpdateAuthor() {
        System.out.println("updateAuthor");
        //Valid id from db
        int titleID = 3;
        //New name of author
        String newAuthorName = "George RR.Martin";
        TitleDAO instance = new TitleDAO();
        //Updates
        boolean expResult = true;
        boolean result = instance.updateAuthor(titleID, newAuthorName);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of UpdateAuthorInvalidId method
     * Testing invalid title id = -3
     * Giving it new name
     * Exp Result false.
     */
    
        @Test
    public void testUpdateAuthorInvalidId() {
        System.out.println("UpdateAuthorInvalidId");
        //Invalid id from db
        int titleID = -3;
        //New author name
        String newAuthorName = "George RR.Martin";
        TitleDAO instance = new TitleDAO();
        //Doesnt updates
        boolean expResult = false;
        boolean result = instance.updateAuthor(titleID, newAuthorName);
        assertEquals(expResult, result);
    }

   /**
     * Test of updateTitle method, of class TitleDAO.
     * Testing valid genre id = 19
     * Giving it new genre
     * Exp Result true.
     */
    @Test
    public void testUpdateGenre() {
        System.out.println("updateGenre");
        //Valid genre id
        int genreID = 19;
        //New genre name
        String newGenre = "Fantasy";
        TitleDAO instance = new TitleDAO();
        //Updates
        boolean expResult = true;
        boolean result = instance.updateGenre(genreID, newGenre);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of UpdateGenreInvalidId method
     * Testing invalid title id = -19
     * Giving it new genre
     * Exp Result false.
     */
    @Test
    public void testUpdateGenreInvalidId() {
        System.out.println("UpdateGenreInvalidId");
        //Invalid genreId from db
        int genreID = -19;
        //New Genre name
        String newGenre = "Fantasy";
        TitleDAO instance = new TitleDAO();
        //Doesnt updates.
        boolean expResult = false;
        boolean result = instance.updateGenre(genreID, newGenre);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of updateDescription method, of class TitleDAO.
     * Testing for valid title Id 4
     * Updating the Description 
     * Exp result is true
     */
    @Test
    public void testUpdateDescription() {
        System.out.println("updateDescription");
        //valid Id of title from db
        int titleID = 4;
        //New Descripition for the title
        String newDescription = "An American Tragedy was the inspiration for the film A Place in the Sun, winner of six Academy Awards, starring Elizabeth Taylor and Montgomery Clift.";
        TitleDAO instance = new TitleDAO();
        boolean expResult = true;
        boolean result = instance.updateDescription(titleID, newDescription);
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of UpdateDescriptionInvalidId method
     * Testing for invalid title Id -4
     * Updating the Description 
     * Exp result is false because of invalid id
     */
    @Test
    public void testUpdateDescriptionInvalidId() {
        System.out.println("UpdateDescriptionInvalidId");
        //invalud Id of title from db
        int titleID = -4;
        //New Descripition for the title
        String newDescription = "An American Tragedy was the inspiration for the film A Place in the Sun, winner of six Academy Awards, starring Elizabeth Taylor and Montgomery Clift.";
        TitleDAO instance = new TitleDAO();
        boolean expResult = false;
        boolean result = instance.updateDescription(titleID, newDescription);
        assertEquals(expResult, result);
       
    }


    /**
     * Test of updateStockOfBook method, of class TitleDAO.
     * Testing for the id 2
     * Updating it by 40
     * ExpResult is True because valid id
     */
    @Test
    public void testUpdateStockOfBook() {
        System.out.println("updateStockOfBook");
        //Valid id from db
        int titleID = 2;
        //New Stock amount
        int newStock = 40;
        TitleDAO instance = new TitleDAO();
        //Stock will be updated Hence True.
        boolean expResult = true; 
        boolean result = instance.updateStockOfBook(titleID, newStock);
        assertEquals(expResult, result);
    }
 /**
     * Test of UpdateStockOfBookWrongId method
     * Testing for the id -2
     * Updating it by 40
     * ExpResult is False because invalid id
     */
      public void testUpdateStockOfBookWrongId() {
        System.out.println("UpdateStockOfBookWrongId");
        //Invalid title ID from db
        int titleID = -2;
        //new Stock amount
        int newStock = 40;
        TitleDAO instance = new TitleDAO();
        //Expected result is false due to id not being in db (Nothing to update)
        boolean expResult = false; 
        boolean result = instance.updateStockOfBook(titleID, newStock);
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
        Title expResult =  new Title (3,"American Pastoral","Philip Roth",2,0,"In American Pastoral, Philip Roth gives us a novel...");
        Title result = instance.searchByAuthor(author);
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
        Title expResult =  null;
        Title result = instance.searchByAuthor(author);
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
