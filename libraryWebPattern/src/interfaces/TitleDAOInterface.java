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
     Admin can only change the Title name
     * @param titleID is used to search the book
     * @param newNovelName will be the updated title name
     * @return True if updated
     */
    public boolean updateTitle(int titleID, String newNovelName);

    /**
     * Admin can only change the Author name
     * @param titleID is used to search the book
     * @param newAuthorName is the new name of author for the book
     * @return true if updated
     */
    public boolean updateAuthor(int titleID, String newAuthorName);

    /**
     *Admin can only change the title genre
     * @param titleID is used to search the book
     * @param newGenre is updated
     * @return
     */
    public boolean updateGenre(int titleID, String newGenre);

    /**
     * Admin can only change the description
     * @param titleID is used to search the book
     * @param newDescription is the updated description of the book
     * @return true if updated
     */
    public boolean updateDescription (int titleID, String newDescription);
    
    /**
     * Admin can only change the stock level of the book
     * @param titleID is used to search the book
     * @param newStock is the new stock of the book
     * @return true if updated
     */
    public boolean updateStockOfBook (int titleID, int newStock);
    
    /**
     * Users can search for a book by searching the authors name
     * @param author is the author user searched for
     * @return the Title
     */
    public Title searchByAuthor(String author);

    /**
     * users can search a books by searching the genre
     * @param genre is genre user searched for
     * @return Title
     */
    
    public ArrayList<Title> searchByGenre(String genre);

    
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
    
    public ArrayList<Title> getAllTitles();
    
    public boolean updateOnLoan(int titleID, int newOnLoan);


}