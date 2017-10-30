/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Dtos.Borrowed;
import Dtos.User;
import java.util.ArrayList;

/**
 *
 * @author Sean
 */
public interface BorrowedDAOInterface {


    /**
     * This will return a borrowed ArrayList by their userID.<p>
     * User must be logged in to do this.<p>
     * Method will be rejected if they are not.
     * @param userID This will be used to identify user.
     * @return ArrayList of Borrowed
     */
    public ArrayList<Borrowed> getBorrowedByUserID(int userID);
    
    /**
     * This will return a borrowed ArrayList by their titleID<p>
     * User must be logged in to do this.<p>
     * Methods will be rejected if they are not.
     * 
     * @param titleID This will be used to identify what Title to find.
     * @return ArrayList of Borrowed
     */
    public ArrayList<Borrowed> getBorrowedByTitleID(int titleID);
 

    /**
     * This will return a borrowed ArrayList by their titleID<p>
     * Status 1 means it is returned.<p>
     * Status 0 means it isn't returned.<p>
     * User must be logged in to do this.<p>
     * Method will be rejected if they are not.
     * @param status This will be used to find which loans have finished.
     * @return ArrayList of Borrowed
     */
    public ArrayList<Borrowed> getBorrowedByStatus(int status);


    /**
     * This will add a borrowed object into the database.<p>
     * Admin user is imported to check if they are a admin before adding borrowed object.<p>
     * Method rejected if User object isn't admin.
     * @param borrowed This will be used to add a new borrowed to database.
     * @return boolean response if it was successful or an issue happened.
     */
    public boolean addBorrowed(Borrowed borrowed);
    
    /**
     * This is used to return all borrowed currently on loan based on userID.<p>
     * This will mainly be used to display to the user what books they currently have on loan.<p>
     * @param userID This is used find what borrowed is related to them.
     * @return ArrayList of all borrowed by them.
     */
    public ArrayList<Borrowed> getStatusByUserID(int userID);
    
    /**
     * This is used to update the status of a borrowed title when they are finished.
     * This will mainly be used to update the DB when they are finished with their title.
     * @param borrowedID This will be used to identify what borrowed they need to change
     * @param newStatus New value of status, usually 0 or 1 to indicate if it is gone or not.
     * @return Boolean response with true meaning is was updated properly.
     */
    public boolean updateStatus(int borrowedID, int newStatus);
    
    /**
     * Used to return a specific borrowed based on its ID.
     * This is mainly used to pull data from a specific borrowed.
     * @param borrowedID Used to identify what borrowed they are looking for
     * @return Borrowed variable with all details on it.
     */
    public Borrowed getBorrowedByID(int borrowedID);
}
