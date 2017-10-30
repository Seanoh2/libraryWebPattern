/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

/**
 * Borrowed object are a combination of user and Title objects.
 * This allowed a user to be tied to a title.
 * Relevant data to this link is also stored here, such as how long it has been borrowed and if it was returned.
 * @author Sean
 */
public class Borrowed {
    private int BorrowedID;
    private User user;
    private Title title;
    private int daysBorrowed;
    private int status;

    /**
     * Used to create Borrowed Objects
     * Borrowed objects allow for ease of access of what title were borrowed and who borrowed it.
     * @param borrowedID Place in the database.
     * @param user Used to store user data of who borrowed a title.
     * @param title Used to store title data of what title was borrowed.
     * @param daysBorrowed Used to track how long it was borrowed.
     * @param status Used to keep a record of if it was returned.
     */
    public Borrowed(int borrowedID, User user, Title title, int daysBorrowed, int status) {
        this.BorrowedID = borrowedID;
        this.user = user;
        this.title = title;
        this.daysBorrowed = daysBorrowed;
        this.status = status;
    }
    public Borrowed(User user, Title title, int daysBorrowed, int status) {
        this.user = user;
        this.title = title;
        this.daysBorrowed = daysBorrowed;
        this.status = status;
    }
    
    /**
     * Default Constructor.
     * No info/params needed.
     */
    public Borrowed() {
        
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public int getDaysBorrowed() {
        return daysBorrowed;
    }

    public void setDaysBorrowed(int daysBorrowed) {
        this.daysBorrowed = daysBorrowed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBorrowedID() {
        return BorrowedID;
    }

    public void setBorrowedID(int BorrowedID) {
        this.BorrowedID = BorrowedID;
    }

    
    @Override
    public String toString() {
        return "borrowed{" + "user=" + user + ", title=" + title + ", daysBorrowed=" + daysBorrowed + ", status=" + status + '}';
    }
    
    
}
