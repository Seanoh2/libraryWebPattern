/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Dtos.User;
import java.util.ArrayList;

/**
 *
 * @author Sean
 */
public interface UserDAOInterface {

    /**
     * This will allow the user to call users by their first name and last name
     * Both variables must be exactly right, Case sensitive
     * @param firstName Parameter for finding user.
     * @param lastName Parameter for finding user.
     * @return ArrayList of relevant User objects.
     */
    public ArrayList<User> selectUserByUsername(String firstName, String lastName);


    /**
     * This will allow the user to call users by their name<p>
     * This will check first and last name, if the substring is inside.<p>
     * Case sensitive.
     * @param name Used to find if user first or last name contain substring.
     * @return ArrayList of relevant User objects.
     */
    public ArrayList<User> selectUserContainingName(String name);

  
    /**
     * This will call a user by their ID<p>
     * This will allow for specific Users to be pulled or for other methods to utilize if needed.<p>
     * @param userID ID of user.
     * @return User object of user with matching user.
     */
    public User findUserByID(int userID);

    /**
     * This will add a user into the system<p>
     * Admin user is imported to check if they are a admin before adding user<p>
     * Method rejected if admin object isn't admin.
     * @param user User object that is being added to database.
     * @return
     */
    public boolean addUser(User user);
    
    /**
     * This will allow for a lookup of user in database.
     * Only using single user object as email needs to be unique.
     * @param email Used to find user in database
     * @return User object with info if found.
     */
    public User getUserByEmail(String email);
    
    /**
     * This allows the user to login to the system with their email and password.<p>
     * Email and Password are case sensitive.
     * @param email Used to identify who is logging in.
     * @param password Used to confirm client is this user.
     * @return boolean result to if it was successful.
     */
    public int login(String email, String password);
    
}
