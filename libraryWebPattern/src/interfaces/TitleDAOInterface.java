/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Dtos.Title;
import Dtos.User;
import java.util.ArrayList;

/**
 *
 * @author Ayesha
 */
public interface TitleDAOInterface {

  
    /**
     * Used to return all titles in database.
     * @return ArrayList of all titles in db.
     */
    public ArrayList<Title> viewAllTitles();

    /**
     * Admins are only allowed to add a new title
     * @param title the new title added
     * @return True if added
     */
    public boolean addTitle(Title title);
    /**
     * Only admins can delete a title
     * @param titleId will be used to delete a title as its unique
     * @return true if deleted
     */
    public boolean deleteTitleById(int titleId);

    /**
     * This will update an existing title in the database.
     * @param title used to be added to database.
     * @return Confirmation if update was successful.
     */
    public boolean updateTitle(int id, Title title);
    
    /**
     * Users can search for a book by searching the authors name
     * @param author is the author user searched for
     * @return the Title
     */
    public ArrayList<Title> searchByAuthor(String author);
    
    /**
     *  Users can search for a title by the novelName
     * @param novelName is the what the user searched for
     * @return Title
     */
    public Title searchByNovelName(String novelName);
    
    /**
     * users can search a title by id 
     * @param id is the what the user searched for
     * @return Title
     */
    public Title searchByID(int id);
}