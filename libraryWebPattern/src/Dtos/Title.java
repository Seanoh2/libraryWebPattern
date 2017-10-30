/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

import java.util.Objects;

/**
 *
 * @author Ayesha
 */
public class Title {

    private int titleID;
    private String novelName;
    private String author;
    private int stock;
    private int onLoan;
    private String titleDescription;

    /**
     * Used to create title objects pulled from the database.<p>
     * Main constructor<p>
     * This is used to allow for easier controller of data when called from db.
     * 
     * @param titleID This is used to identify a title in the database. Primary key.
     * @param novelName This is the Titles name, Mainly used for user to find title.
     * @param author This is used to identify who created the Title. Allows for users to also find other titles made by author.
     * @param stock This is used to keep track of how many titles are currently stocked
     * @param onLoan This is used to keep track of how many books are currently out on loan to users.
     * @param titleDescription This is used to keep a small description of what the title is about.
     */
    public Title(int titleID, String novelName, String author, int stock, int onLoan, String titleDescription) {
        this.titleID = titleID;
        this.novelName = novelName;
        this.author = author;
        this.stock = stock;
        this.onLoan = onLoan;
        this.titleDescription = titleDescription;
    }
    
        /**
     * Used to create title objects pulled from the database.<p>
     * This is used to create objects not in database, as they do not contain a titleID which is given when being added to database.<p>
     * Only use when creating a title object that isn't currently in the database.<p>
     * This is used to allow for easier controller of data when called from db.
     * 
     * @param novelName This is the Titles name, Mainly used for user to find title.
     * @param author This is used to identify who created the Title. Allows for users to also find other titles made by author.
     * @param stock This is used to keep track of how many titles are currently stocked
     * @param onLoan This is used to keep track of how many books are currently out on loan to users.
     * @param titleDescription This is used to keep a small description of what the title is about.
     */

    public Title(String novelName, String author, int stock, int onLoan, String titleDescription) {
        this.novelName = novelName;
        this.author = author;
        this.stock = stock;
        this.onLoan = onLoan;
        this.titleDescription = titleDescription;
    }

    /**
     * Default Constructor
     * No info/params.
     */
    public Title() {

    }

    public int getTitleID() {
        return titleID;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOnLoan() {
        return onLoan;
    }

    public void setOnLoan(int onLoan) {
        this.onLoan = onLoan;
    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public void setTitleDescription(String titleDescription) {
        this.titleDescription = titleDescription;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.novelName);
        hash = 29 * hash + Objects.hashCode(this.author);
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
        final Title other = (Title) obj;
        if (!Objects.equals(this.novelName, other.novelName)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Title{" + "novelName=" + novelName + ", author=" + author + ", stock=" + stock + ", onLoan=" + onLoan + ", titleDescription=" + titleDescription + '}';
    }

}
