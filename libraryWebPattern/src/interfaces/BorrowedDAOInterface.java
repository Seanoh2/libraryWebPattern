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
    
    public ArrayList<Borrowed> getStatusByUserID(int userID);
    
    public boolean updateStatus(int borrowedID, int newStatus);
    
    public Borrowed getBorrowedByID(int borrowedID);
}
