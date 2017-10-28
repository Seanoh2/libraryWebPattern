/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

import java.util.Objects;

/**
 *
 * @author Sean
 */
public class User {
    private int userID;
    private String email; 
    private String password; 
    private String firstName; 
    private String lastName; 
    private String country;                    
    private String addressLine1; 
    private String addressLine2; 
    private int isAdmin; 

    /**
     * This Constructor will be user for general usage.<p>
     * When creating an object outside of adding a user, use this constructor.<p>
     * Only tablerow not saved is userJoined row.
     *
     * @param userID ID of user in database, auto assigned when added.
     * @param email Email of user, used for logging in, acting as username.
     * @param password Password of user, used to ensure user info is secure.
     * @param firstName First name of user, used to recording data of user to display.
     * @param lastName Last name of user, used to recording data of user to display.
     * @param country Country of user, used to recording data of user to display. Can also be used to localise language if needed.
     * @param addressLine1 Address of user, used incase letter/info needs to be sent.
     * @param addressLine2 Address of user, used incase letter/info needs to be sent.
     * @param isAdmin Check is user is an admin. 1 will mean yes, 0 will mean no.
     */
    public User(int userID, String email, String password, String firstName, String lastName, String country, String addressLine1, String addressLine2, int isAdmin) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.isAdmin = isAdmin;
    }
    

    /**
     * This constructor will be user for adding a user to the database.
     * Needed because user doesn't receive userID until they are added.
     * Table rows not saved/created: userID,userJoined.
     *
     * @param email Email of user, used for logging in, acting as username.
     * @param password Password of user, used to ensure user info is secure.
     * @param firstName First name of user, used to recording data of user to display.
     * @param lastName Last name of user, used to recording data of user to display.
     * @param country Country of user, used to recording data of user to display. Can also be used to localise language if needed.
     * @param addressLine1 Address of user, used incase letter/info needs to be sent.
     * @param addressLine2 Address of user, used incase letter/info needs to be sent.
     * @param isAdmin Check is user is an admin. 1 will mean yes, 0 will mean no.
     */
    public User(String email, String password, String firstName, String lastName, String country, String addressLine1, String addressLine2, int isAdmin) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.isAdmin = isAdmin;
    }
    

    /**
     * Default constructor
     *  No info/params entered.
     *
     */
    public User() {
        
    }

    
    //Getter and Setters for each variable.
    //Normally wouldnt have a setter for userID but needed when creating new user objects from pulled database info.
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    
    //toString of all user Data
    //Based on constructor without ID to ensure no errors when printing various objects.
    @Override
    public String toString() {
        return "User{" + "email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", isAdmin=" + isAdmin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.userID;
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userID != other.userID) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
    
}
